package com.univpm.TweetAnalyzer.VariousTest.filtersTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa è la Classe che istanzia Filtri da Applicare in base ai Followers di un Tweet. 
 */

public class FollowersFilter extends LongDataFilter {

	public FollowersFilter(Map<String, Map<Integer, Data>> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType, filterValue);
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMajorOperator(Map<Integer, Data> tempMap, long longFilterValue) {
		Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getFollowers() > longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}

	@Override
	public Map<Integer, Data> filterDataValueWithMinorOperator(Map<Integer, Data> tempMap, long longFilterValue) {
		Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
		
		for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
			if(field.getValue().getFollowers() < longFilterValue)
				filteredDataValue.put(field.getKey(), field.getValue());
			}
		
		return filteredDataValue;
	}
	
}
