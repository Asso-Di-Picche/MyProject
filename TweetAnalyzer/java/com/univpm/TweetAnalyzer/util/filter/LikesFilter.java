package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

public class LikesFilter extends LongDataFilter {

	public LikesFilter(Map<String, Map> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType, filterValue);
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMajorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue) {
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getLikes() > longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMinorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue) {
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getLikes() < longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}
	
}
