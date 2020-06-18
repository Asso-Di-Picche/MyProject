package com.univpm.TweetAnalyzer.util.filter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.univpm.TweetAnalyzer.DatabaseClass.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.time.Date;
import com.univpm.TweetAnalyzer.model.time.Hours;
import com.univpm.TweetAnalyzer.model.time.Time;
import com.univpm.TweetAnalyzer.Service.DateParsingService;

public class SingleDateFilter extends BasicFilter{
	private Time filterValue;
	
	public SingleDateFilter(Map<String, Map> tempData, String filterType, Time filterValue) {
		
		super(tempData, filterType);
		this.filterValue = filterValue;
		
	}

	public Time getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(Time filterValue) {
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalTimeException {
		Time cmpTime = null;
		Map<Integer, Data> filteredDataValue = new HashMap<>();
		
		Iterator<Map.Entry<String, Map>> iterator = tempData.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Map> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			for(Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				cmpTime = DateParsingService.cmpTimeIstance(entry);
				if(this.filterType == "After")
					afterDate(filteredDataValue, entry, filterValue, cmpTime);
				else 
					beforeDate(filteredDataValue, entry, filterValue, cmpTime);
			}

			filteredData.put(filteredDataKey, filteredDataValue);
		}
		
		return filteredData;
	}

	public void afterDate(Map<Integer, Data> filteredDataValue,
			Map.Entry<Integer, Data> entry, Time refTime, Time cmpTime)
					throws IllegalTimeException {
		
		if(refTime.timeCompare(cmpTime))
			filteredDataValue.put(entry.getKey(), entry.getValue());

	}
	
	public void beforeDate(Map<Integer, Data> filteredDataValue,
			Map.Entry<Integer, Data> entry, Object refTime, Time cmpTime)
					throws IllegalTimeException {

		if(cmpTime.timeCompare((Time) refTime))
			filteredDataValue.put(entry.getKey(), entry.getValue());
	
	}
	
}