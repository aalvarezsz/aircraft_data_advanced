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
	        // get values separated by coma (csv file...)
        String[] values = data.split(",");
        if( values.length > 0 ) {
        	record = new Record() ;
        	record.setTimestamp( (int) Float.parseFloat(values[0]));
        }
		return record;
	}

}
