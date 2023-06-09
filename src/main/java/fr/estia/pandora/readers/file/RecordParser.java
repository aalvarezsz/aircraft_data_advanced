package fr.estia.pandora.readers.file;

import java.util.*;

import fr.estia.pandora.model.Record;
import fr.estia.pandora.readers.file.exceptions.FileException;
import fr.estia.pandora.readers.file.exceptions.IncompleteHeaderException;
import fr.estia.pandora.readers.file.exceptions.MissingHeaderException;
import fr.estia.pandora.readers.file.exceptions.OrderingException;

public class RecordParser {
	private Map<String, Integer> parameterColumn;
	private String fileName;
	private String flightOrigin;
	private int engineAmount;

	public RecordParser(String fileName, String header, String flightOrigin, int engineAmount) throws FileException {
		this.parameterColumn = new HashMap<>();
		this.fileName = fileName;
		this.flightOrigin = flightOrigin;
		this.engineAmount = engineAmount;

		List<String> headerTitles = new ArrayList<>(Arrays.asList(
				"timestamp","longitude","latitude","altitude","roll","pitch","yaw","heading",
				"air_speed", "temperature_in","humidity_in","pressure_in","heart_rate","oxygen_mask"
		));

		for(int i = 0; i < engineAmount; i++) headerTitles.add("engine_" + i);
		if(flightOrigin.equals("US")) headerTitles.addAll(Arrays.asList("u", "v"));

		String[] headerTitle  = header.split(",");
		for (int columnIndex = 0; columnIndex < headerTitle.length; columnIndex++) {
			String parameter = headerTitle[columnIndex];
			int paramIndex = headerTitles.indexOf(parameter);

			if(paramIndex >= 0) {
				headerTitles.remove(paramIndex);
				parameterColumn.put(parameter, columnIndex);
			}
		}

		if (headerTitles.size() >= 17 && flightOrigin.equals("US")) throw new MissingHeaderException(fileName);
		else if (headerTitles.size() >= 15 && flightOrigin.equals("RU")) throw new MissingHeaderException(fileName);
		else if (headerTitles.size() > 0) throw new IncompleteHeaderException(fileName, headerTitles);
	}

	public Record parse(String data, double previousTimestamp) throws OrderingException {
		Record record = null;
        String[] values = data.split(",");

        if( values.length > 0 ) {
        	record = new Record();
        	
        	if(this.flightOrigin.equals("US")) {
        		for(Map.Entry<String, Integer> parameter: parameterColumn.entrySet()) {
        			int index = parameter.getValue();

        			switch(parameter.getKey()) {
	        			case "timestamp":
							double currentTimestamp = Double.parseDouble(values[index]);
							if(previousTimestamp <= currentTimestamp) record.setTimestamp(currentTimestamp);
							else throw new OrderingException(fileName);
							break;
	        			case "longitude": record.setLongitude(Double.parseDouble(values[index])); break;
	        			case "latitude": record.setLatitude(Double.parseDouble(values[index])); break;
	        			case "altitude": record.setAltitude(Double.parseDouble(values[index]) / 3.281); break;
	        			
	        			case "roll": record.setRoll(Float.parseFloat(values[index])); break;
	        			case "pitch": record.setPitch(Float.parseFloat(values[index])); break;
	        			case "yaw": record.setYaw(Float.parseFloat(values[index])); break;
	        			case "heading": record.setHeading(Float.parseFloat(values[index])); break;
	        			case "u": record.setU(Float.parseFloat(values[index])); break;
	        			case "v": record.setV(Float.parseFloat(values[index])); break;
	        			case "air_speed": record.setAir_speed(Double.parseDouble(values[index])* 1.852/0.2777778) ; break;// mph to m/s	        			
	        			case "engine_0": record.setEnginePower(Double.parseDouble(values[index]) * this.engineAmount * 754.7); break;
	        			case "temperature_in": record.setTemperature_in(Double.parseDouble(values[index])); break;
	        			case "humidity_in": record.setHumidity_in(Double.parseDouble(values[index])); break;
	        			case "pressure_in": record.setPressure_in(Double.parseDouble(values[index]) * 6894.76); break;
	        			
	        			case "heart_rate": record.setHeart_rate(Double.parseDouble(values[index])); break;
	        			case "oxygen_mask": record.setOxygen_mask(Double.parseDouble(values[index])); break;
        			}
        		}
        	} else {
        		for(Map.Entry<String, Integer> parameter: parameterColumn.entrySet()) {
        			int index = parameter.getValue();

        			switch(parameter.getKey()) {
						case "timestamp":
							double currentTimestamp = Double.parseDouble(values[index]);
							if(previousTimestamp <= currentTimestamp) record.setTimestamp(currentTimestamp);
							else throw new OrderingException(fileName);
							break;
	        			case "longitude": record.setLongitude(Double.parseDouble(values[index])); break;
	        			case "latitude": record.setLatitude(Double.parseDouble(values[index])); break;
	        			case "altitude": record.setAltitude(Double.parseDouble(values[index])); break;
	        			
	        			case "roll": record.setRoll(Float.parseFloat(values[index])); break;
	        			case "pitch": record.setPitch(Float.parseFloat(values[index])); break;
	        			case "yaw": record.setYaw(Float.parseFloat(values[index])); break;
	        			case "heading": record.setHeading(Float.parseFloat(values[index])); break;
	        			case "u": record.setU(Float.parseFloat(values[index])); break;
	        			case "v": record.setV(Float.parseFloat(values[index])); break;
	        			case "air_speed": record.setAir_speed(Double.parseDouble(values[index])  ); break; // already in m/s
	        			
	        			case "engine_0": record.setEnginePower(Double.parseDouble(values[index]) * this.engineAmount); break;
	        			case "temperature_in": record.setTemperature_in(Double.parseDouble(values[index])); break;
	        			case "humidity_in": record.setHumidity_in(Double.parseDouble(values[index])); break;
	        			case "pressure_in": record.setPressure_in(Double.parseDouble(values[index])); break;
	        			
	        			case "heart_rate": record.setHeart_rate(Double.parseDouble(values[index])); break;
	        			case "oxygen_mask": record.setOxygen_mask(Double.parseDouble(values[index])); break;
        			}
        		}
        	}
        }
		return record;
	}

}