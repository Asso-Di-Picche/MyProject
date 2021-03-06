package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

public class MultipleLanguageFilter extends BasicFilter{
	private ArrayList<String> filterValue;
	
	public MultipleLanguageFilter(Map<String, Map> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException {
		Map<Integer, Data> filteredDataValue = new HashMap<>();
		Iterator<Map.Entry<String, Map>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, Map> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			filteredDataValue = filterDataValue(filteredDataValue, tempMap);
			filteredData.put(filteredDataKey, filteredDataValue);
			filteredDataKey = "";
			filteredDataValue = new HashMap<>();
		}
		
		return filteredData;
	}
	
	public Map<Integer, Data> filterDataValue(Map<Integer, Data> filteredDataValue, Map<Integer, Data> tempMap)
			throws IllegalFilterValueSizeException{
		try {
			
			for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
				for(String s : filterValue) {
					if(s.length() > 2)
						throw new IllegalFilterValueSizeException();
					if(s.equals(field.getValue().getLanguage()))
						filteredDataValue.put(field.getKey(), field.getValue());
				}
			}
			
		}catch(IllegalFilterValueSizeException ex) {
			throw new IllegalFilterValueSizeException(filterValue + " Filter Value is Unrecognizable");
		}
		
		return filteredDataValue;
	}

}
