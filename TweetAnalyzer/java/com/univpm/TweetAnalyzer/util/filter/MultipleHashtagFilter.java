package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;

public class MultipleHashtagFilter extends BasicFilter{
	private ArrayList<String> filterValue;
	
	public MultipleHashtagFilter(Map<String, Map> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalFilterValueException {
		try {
			for(Map.Entry<String, Map> entry : tempData.entrySet()) {
				Iterator iterator = this.filterValue.iterator();
				while(iterator.hasNext()) {
					if(( iterator.next() ).equals( entry.getKey()) )
						filteredData.put(entry.getKey(), entry.getValue());
				}
			}
			
			if(filteredData.isEmpty())
				throw new IllegalFilterValueException();
			
		}catch(IllegalFilterValueException ex) {
			throw new IllegalFilterValueException(filterValue + " Filter Value is Unrecognizable");
		}
		
		return filteredData;
	}

}
