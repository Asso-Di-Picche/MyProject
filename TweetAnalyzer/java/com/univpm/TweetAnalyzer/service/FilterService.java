package com.univpm.TweetAnalyzer.ServiceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univpm.TweetAnalyzer.DatabaseClassTest.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.FilterNotFoundException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.time.Time;
import com.univpm.TweetAnalyzer.util.filter.Filter;
import com.univpm.TweetAnalyzer.util.filter.FollowersFilter;
import com.univpm.TweetAnalyzer.util.filter.LikesFilter;
import com.univpm.TweetAnalyzer.util.filter.MultipleDateFilter;
import com.univpm.TweetAnalyzer.util.filter.MultipleHashtagFilter;
import com.univpm.TweetAnalyzer.util.filter.MultipleLanguageFilter;
import com.univpm.TweetAnalyzer.util.filter.RetweetsFilter;
import com.univpm.TweetAnalyzer.util.filter.SingleDateFilter;
import com.univpm.TweetAnalyzer.util.filter.SingleHashtagFilter;
import com.univpm.TweetAnalyzer.util.filter.SingleLanguageFilter;

public class FilterService {
	private static Filter filter;
	private static Map<String, Map> filteredData;
	
	public static Map<String, Map> filterParsing(Object filter)
			throws FilterNotFoundException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException{
		Map<String, Map> tempData = DatabaseClass.getDataMap();
		filteredData = new HashMap<String, Map>();
		HashMap<String, Object> filterMap = new HashMap<>();
		String filterKey = null;
		Object filterValue = null;
		
		try {
			if(filter instanceof ArrayList) {
				ArrayList<Object> filterList = new ObjectMapper().convertValue(filter, ArrayList.class);
				
				for(Object o : filterList) {
					
					Map<String, Object> tempMap = new ObjectMapper().convertValue(o, HashMap.class);
					Iterator<Map.Entry<String, Object>> entries = tempMap.entrySet().iterator();
					Map.Entry<String, Object> entry = entries.next();
					filterMap.put(entry.getKey(), entry.getValue());
					
				}
				if(filterMap.size() < filterList.size())
					throw new FilterNotFoundException();

				Iterator<Map.Entry<String, Object>> entries = filterMap.entrySet().iterator();
				
				while(entries.hasNext()) {
					
					Map.Entry<String, Object> entry = entries.next();
					
					filterKey = entry.getKey();
					filterValue = entry.getValue();
					

					filteredData = new HashMap<>();
					filteredData = filterRun(tempData, filterKey, filterValue);
					tempData = new HashMap<>();
					tempData = filteredData;
				}
				
			}
			else {
				filterMap = new ObjectMapper().convertValue(filter, HashMap.class);
				
				Iterator<Map.Entry<String, Object>> entries = filterMap.entrySet().iterator();
				Map.Entry<String, Object> entry = entries.next();
				
				filterKey = entry.getKey();
				filterValue = entry.getValue();
				
				filteredData = filterRun(tempData, filterKey, filterValue);
			}
		}
		catch(FilterNotFoundException ex) {
			throw new FilterNotFoundException("Filters Can Not Have Duplicates!");
		}
		
		return filteredData;
		
	}
	
	public static Map<String, Map> filterRun(Map<String, Map> tempData, String filterKey, Object filterValue)
			throws FilterNotFoundException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException{
		try {
			
			if(filterKey == "#") {
				if(filterValue instanceof String)
					filter = new SingleHashtagFilter(tempData, filterKey, (String)filterValue);
				else 
					filter = new MultipleHashtagFilter(tempData, filterKey, (ArrayList<String>)filterValue);
				}
			
			else if(filterKey == "Language") {
				if(filterValue instanceof String)
					filter = new SingleLanguageFilter(tempData, filterKey, (String)filterValue);
				else
					filter = new MultipleLanguageFilter(tempData, filterKey, (ArrayList<String>)filterValue);
			}
			
			else if(filterKey == "Followers")
				filter = new FollowersFilter(tempData, filterKey, (ArrayList<String>)filterValue);
			
			else if(filterKey == "Retweets")
				filter = new RetweetsFilter(tempData, filterKey, (ArrayList<String>)filterValue);
			
			else if(filterKey == "Likes")
				filter = new LikesFilter(tempData, filterKey, (ArrayList<String>)filterValue);
			
			else if(filterKey == "After" || filterKey == "Before")
				filter = new SingleDateFilter(tempData, filterKey,
						(Time)DateParsingService.filterValueDateParsing((String)filterValue));
			
			else if(filterKey == "Between")
				filter = new MultipleDateFilter(tempData, filterKey, (ArrayList<String>)filterValue);
			
			else throw new IllegalFilterKeyException();
			
		}
		catch(Exception ex) {
			if(filterKey!="#"&&filterKey!="After"&&filterKey!="Before"&&filterKey!="Between"&&
					filterKey!="Followers"&&filterKey!="Retweets"&&filterKey!="Likes")
				throw new IllegalFilterKeyException(filterKey + " Is Not An Allowed Key");
			
			else throw new IllegalFilterValueException(filterValue + " Filter Value is Unrecognizable");
			
		}
		
		filter.filtrate(filteredData);
			
		return filteredData;
	}

}
