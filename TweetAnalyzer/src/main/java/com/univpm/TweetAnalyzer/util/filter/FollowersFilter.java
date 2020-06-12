package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

public class FollowersFilter extends LongDataFilter {

	public FollowersFilter(Map<String, Map<Integer, Data>> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType, filterValue);
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMajorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue) {
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getFollowers() > longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMinorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue) {
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getFollowers() < longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}
	
}
