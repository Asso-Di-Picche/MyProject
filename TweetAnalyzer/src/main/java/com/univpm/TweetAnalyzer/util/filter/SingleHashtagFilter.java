package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa è la Classe le cui Istanze sono Filtri che vengono Applicati in base ad un Hashtag
 * inserito dall'Utente.
 */

public class SingleHashtagFilter extends BasicFilter{
	private String filterValue;
	
	public SingleHashtagFilter(Map<String, Map<Integer, Data>> tempData, String filterType, String filterValue){
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate()
			throws IllegalFilterValueException {
		
		//Questa Map serve per contenere i Dati da Restituire una volta che sono stati Filtrati.
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		
		try {
			for(Map.Entry<String, Map<Integer, Data>> entry: tempData.entrySet())
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