package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;

public class SingleHashtagFilter extends BasicFilter{
	private String filterValue;
	
	public SingleHashtagFilter(Map<String, Map> tempData, String filterType, String filterValue){
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalFilterValueException {
		try {
			for(Map.Entry<String, Map> entry: tempData.entrySet())
				if(this.filterValue.equals(entry.getKey()))
					filteredData.put(entry.getKey(), entry.getValue());
			
			if(filteredData.isEmpty())
				throw new IllegalFilterValueException();
			
		}catch(IllegalFilterValueException ex) {
			throw new IllegalFilterValueException(filterValue + " Filter Value is Unrecognizable");
		}
		
		return filteredData;
	}

}