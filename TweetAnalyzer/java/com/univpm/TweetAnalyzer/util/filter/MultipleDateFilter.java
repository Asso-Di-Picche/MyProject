package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.time.Time;
import com.univpm.TweetAnalyzer.ServiceTest.DateParsingService;

public class MultipleDateFilter extends BasicFilter{
	private SingleDateFilter[] filterDate;
	private Time[] filterValue;
	
	public MultipleDateFilter(Map<String, Map> tempData, String filterType, ArrayList<String> filterValue)
			throws IllegalFilterValueException {
		super(tempData, filterType);
		
		Time[] filterValues = new Time[2];
		ArrayList<String> filterValueArrayList = new ObjectMapper().convertValue(filterValue, ArrayList.class);
		Iterator<String> iterator = filterValueArrayList.iterator();
		
		for(int i = 0; iterator.hasNext(); i++)
			filterValues[i] = (DateParsingService.filterValueDateParsing(iterator.next()));
		
		this.filterValue = filterValues;
		this.filterDate = new SingleDateFilter[2];
		
		for(int i=0; i<this.filterValue.length; i++)
			this.filterDate[i] = (new SingleDateFilter(tempData, filterType, this.filterValue[i]));
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalTimeException {
		Time[] cmpTime = new Time[2];
		Map<Integer, Data> filteredAfter = new HashMap<>();
		Map<Integer, Data> filteredBefore = new HashMap<>();
		
		Iterator<Map.Entry<String, Map>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, Map> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			for(Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				cmpTime[0] = DateParsingService.cmpTimeIstance(entry);
				filterDate[0].afterDate(filteredAfter, entry, filterValue[0], cmpTime[0]);
			}
			
			for(Map.Entry<Integer, Data> entry : filteredAfter.entrySet()) {
				cmpTime[1] = DateParsingService.cmpTimeIstance(entry);
				filterDate[1].beforeDate(filteredBefore, entry, filterValue[1], cmpTime[1]);
			}

			filteredData.put(filteredDataKey, filteredBefore);
			filteredDataKey = "";
			filteredAfter = new HashMap<>();
			filteredBefore = new HashMap<>();
			
		}
		
		return filteredData;
	}

}