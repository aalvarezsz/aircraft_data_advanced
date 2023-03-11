package fr.estia.pandora.readers.file;

import java.util.HashMap;
import java.util.Map;

import fr.estia.pandora.model.Record;

public class RecordParser {
	private Map<String, Integer> parameterColumn;
	private String flightOrigin;
	private int engineAmount;
	
	public RecordParser(String header, String flightOrigin, int engineAmount) {
		this.parameterColumn = new HashMap<String, Integer>();
		this.flightOrigin = flightOrigin;
		this.engineAmount = engineAmount;
		
		String[] headerTitle  = header.split(",");
		for (int columnIndex = 0; columnIndex < headerTitle.length; columnIndex++) {
			String parameter = headerTitle[columnIndex];
			parameterColumn.put(parameter, columnIndex);			
		}
	}

	public Record parse(String data) {
		Record record = null ; 
        String[] values = data.split(",");
        
        if( values.length > 0 ) {
        	record = new Record();
        	
        	if(this.flightOrigin.equals("US")) {
        		for(Map.Entry<String, Integer> parameter: parameterColumn.entrySet()) {
        			int index = parameter.getValue();
        			
        			switch(parameter.getKey()) {
	        			case "timestamp": record.setTimestamp(Double.parseDouble(values[index])); break;
	        			case "longitude": record.setLongitude(Double.parseDouble(values[index])); break;
	        			case "latitude": record.setLatitude(Double.parseDouble(values[index])); break;
	        			case "altitude": record.setAltitude(Double.parseDouble(values[index]) / 3.281); break;
	        			
	        			case "roll": record.setRoll(Float.parseFloat(values[index])); break;
	        			case "pitch": record.setPitch(Float.parseFloat(values[index])); break;
	        			case "yaw": record.setYaw(Float.parseFloat(values[index])); break;
	        			case "heading": record.setHeading(Float.parseFloat(values[index])); break;
	        			case "u": record.setU(Float.parseFloat(values[index])); break;
	        			case "v": record.setV(Float.parseFloat(values[index])); break;
	        			case "air_speed": record.setAir_speed(Double.parseDouble(values[index])); break;
	        			
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
	        			case "timestamp": record.setTimestamp(Double.parseDouble(values[index])); break;
	        			case "longitude": record.setLongitude(Double.parseDouble(values[index])); break;
	        			case "latitude": record.setLatitude(Double.parseDouble(values[index])); break;
	        			case "altitude": record.setAltitude(Double.parseDouble(values[index])); break;
	        			
	        			case "roll": record.setRoll(Float.parseFloat(values[index])); break;
	        			case "pitch": record.setPitch(Float.parseFloat(values[index])); break;
	        			case "yaw": record.setYaw(Float.parseFloat(values[index])); break;
	        			case "heading": record.setHeading(Float.parseFloat(values[index])); break;
	        			case "u": record.setU(Float.parseFloat(values[index])); break;
	        			case "v": record.setV(Float.parseFloat(values[index])); break;
	        			case "air_speed": record.setAir_speed(Double.parseDouble(values[index])  ); break;
	        			
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