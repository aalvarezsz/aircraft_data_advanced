package fr.estia.pandora.readers.file;

import java.util.HashMap;
import java.util.Map;

import fr.estia.pandora.model.Record;

public class RecordParser {
	Map<String, Integer> parameterColumn ;
	
	public RecordParser(String header) {
		parameterColumn = new HashMap<String, Integer>() ;
		String[] headerTitle  = header.split(",") ; 
		for (int columnIndex = 0; columnIndex < headerTitle.length; columnIndex++) {
			String parameter = headerTitle[columnIndex];
			parameterColumn.put(parameter, columnIndex) ;			
		}
	}

	public Record parse(String data) {
		Record record = null ; 
        String[] values = data.split(",");
        
        if( values.length > 0 ) {
        	record = new Record();
        	record.setTimestamp(Double.parseDouble(values[0]));
        	record.setLongitude(Double.parseDouble(values[1]));
        	record.setLatitude(Double.parseDouble(values[2]));
        	record.setAltitude(Double.parseDouble(values[3]));
        	
        	record.setRoll(Float.parseFloat(values[4]));
        	record.setPitch(Float.parseFloat(values[5]));
        	record.setYaw(Float.parseFloat(values[6]));
        	record.setHeading(Float.parseFloat(values[7]));
        	
        	record.setAir_speed(Double.parseDouble(values[8]));
        	record.setEngine_0_power(Double.parseDouble(values[9]));
        	
        	if(values.length == 16) {
        		record.setEngine_1_power(Double.parseDouble(values[10]));
        		record.setTemperature_in(Double.parseDouble(values[11]));
        		record.setHumidity_in(Double.parseDouble(values[12]));
        		record.setPressure_in(Double.parseDouble(values[13]));
        		
        		record.setHeart_rate(Double.parseDouble(values[14]));
        		record.setOxygen_mask(Double.parseDouble(values[15]));
        	} else {
        		record.setTemperature_in(Double.parseDouble(values[10]));
        		record.setHumidity_in(Double.parseDouble(values[11]));
        		record.setPressure_in(Double.parseDouble(values[12]));
        		
        		record.setHeart_rate(Double.parseDouble(values[13]));
        		record.setOxygen_mask(Double.parseDouble(values[14]));
        	}
        }
		return record;
	}

}
