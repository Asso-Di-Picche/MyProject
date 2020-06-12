package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

public abstract class LongDataFilter extends BasicFilter{
	protected String[] filterValue;
	
	public LongDataFilter(Map<String, Map> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType);
		this.filterValue = new String[2];
		
		Iterator<String> iterator = filterValue.iterator();
		for(int i=0; i<filterValue.size() && iterator.hasNext(); i++)
			this.filterValue[i] = iterator.next();
	}

	@Override
	public Map<String, Map> filtrate(Map filteredData)
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException {
		
		try {
			
		    long longFilterValue = Long.parseLong(this.filterValue[1]);
		    if(longFilterValue < 0)
		    	throw new IllegalFilterValueException();
		    
			Map<Integer, Data> filteredDataValue = new HashMap<>();
			Map<Integer, Data> tempFilteredDataValue = new HashMap<>();
			Iterator<Map.Entry<String, Map>> iterator = tempData.entrySet().iterator();
			
			while(iterator.hasNext()) {
				Map.Entry<String, Map> pointedMap = iterator.next();
				String filteredDataKey = pointedMap.getKey();
				HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
				
				switch(filterValue[0]) {
				case ">":
					filteredDataValue = filterDataValueWithMajorOperator(
							filteredDataValue, tempMap, longFilterValue);
					break;
					case "<":
						filteredDataValue = filterDataValueWithMinorOperator(
								filteredDataValue, tempMap, longFilterValue);
						break;
						default :
							long leftLongFilterValue = Long.parseLong(this.filterValue[0]);
							if(leftLongFilterValue < 0)
						    	throw new IllegalFilterValueException();
							
							tempFilteredDataValue = filterDataValueWithMajorOperator(
									tempFilteredDataValue, tempMap, leftLongFilterValue);
							filteredDataValue = filterDataValueWithMinorOperator(
									filteredDataValue, tempFilteredDataValue, longFilterValue);
							break;
				}
				
				filteredData.put(filteredDataKey, filteredDataValue);
				
				filteredDataKey = "";
				tempFilteredDataValue = new HashMap<>();
				filteredDataValue = new HashMap<>();
			
			}
		
		}
		catch(IllegalFilterValueException ex){
	    	throw new IllegalFilterValueException("Can Not Use Negative Numbers!");
		}
		catch(Exception ex) {
			throw new IllegalFilterValueException(this.filterValue[0] + 
					" or " + this.filterValue[1] + "  Filter Value is Unrecognizable");
		}
		
		return filteredData;
	}

	protected abstract Map<Integer, Data> filterDataValueWithMajorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue);
	
	protected abstract Map<Integer, Data> filterDataValueWithMinorOperator(Map<Integer, Data> filteredDataValue,
			Map<Integer, Data> tempMap, long longFilterValue);

}
