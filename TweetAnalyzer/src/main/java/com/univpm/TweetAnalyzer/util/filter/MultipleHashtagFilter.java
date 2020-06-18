package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa è la Classe le cui Istanze sono Filtri da Applicare in base a più Hashtag
 * inseriti dall'Utente.
 */

public class MultipleHashtagFilter extends BasicFilter{
	private ArrayList<String> filterValue;
	
	public MultipleHashtagFilter(Map<String, Map<Integer, Data>> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate()
			throws IllegalFilterValueException {
		
		//Questa Map serve per contenere i Dati da Restituire una volta che sono stati Filtrati.
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		
		try {
			for(Map.Entry<String, Map<Integer, Data>> entry : tempData.entrySet()) {
				Iterator<String> iterator = this.filterValue.iterator();
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
