package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

public class SingleLanguageFilter extends BasicFilter{
	private String filterValue;
	
	public SingleLanguageFilter(Map<String, Map<Integer, Data>> tempData, String filterType, String filterValue) {
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate(Map filteredData)
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException {
		Map<Integer, Data> filteredDataValue = new HashMap<>();
		Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			filteredDataValue = filterDataValue(filteredDataValue, tempMap);
			filteredData.put(filteredDataKey, filteredDataValue);
			filteredDataKey = "";
			filteredDataValue = new HashMap<>();
		}
		
		return filteredData;
	}
	
	public Map<Integer, Data> filterDataValue(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap) throws IllegalFilterValueSizeException{
		
		try {
			if(filterValue.length() > 2)
				throw new IllegalFilterValueSizeException();
			
			for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
				if(this.filterValue.equals(field.getValue().getLanguage()))
					filteredDataValue.put(field.getKey(), field.getValue());
			}
			
	}catch(IllegalFilterValueSizeException ex) {
		throw new IllegalFilterValueSizeException(filterValue + " Filter Value is Unrecognizable");
	}
		
		return filteredDataValue;
	}

}
