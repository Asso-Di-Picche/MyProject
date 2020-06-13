package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.time.Time;
import com.univpm.TweetAnalyzer.service.DateParsingService;

public class SingleDateFilter extends BasicFilter{
	private Time filterValue;
	
	public SingleDateFilter(Map<String, Map<Integer, Data>> tempData, String filterType, Time filterValue) {
		
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
	public Map<String, Map<Integer, Data>> filtrate(Map<String, Map<Integer, Data>> filteredData)
			throws IllegalTimeException {
		Time cmpTime = null;
		Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
		
		Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			for(Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				cmpTime = DateParsingService.cmpTimeIstance(entry);
				if(BasicFilter.filterType == "After")
					afterDate(filteredDataValue, entry, filterValue, cmpTime);
				else 
					beforeDate(filteredDataValue, entry, filterValue, cmpTime);
			}

			filteredData.put(filteredDataKey, filteredDataValue);
			filteredDataValue = new HashMap<Integer, Data>();
			filteredDataKey = null;
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