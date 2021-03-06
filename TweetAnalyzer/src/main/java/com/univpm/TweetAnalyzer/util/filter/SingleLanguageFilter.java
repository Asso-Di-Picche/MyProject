package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa è la Classe le cui Istanze sono Filtri che vengono Applicati in base ad una Lingua
 * inserita dall'Utente.
 */

public class SingleLanguageFilter extends BasicFilter{
	private String filterValue;
	
	public SingleLanguageFilter(Map<String, Map<Integer, Data>> tempData, String filterType, String filterValue) {
		super(tempData, filterType);
		this.filterValue = filterValue;
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate()
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException {
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		Map<Integer, Data> filteredDataValue = new HashMap<>();
		Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			filteredDataValue = filterDataValue(tempMap);
			filteredData.put(filteredDataKey, filteredDataValue);
			filteredDataKey = null;
			filteredDataValue = new HashMap<>();
		}
		
		return filteredData;
	}
	
	/**
	 * Questo Metodo serve per Popolare una Map con i Tweet scritti in una determinata Lingua.
	 * @param tempMap È la Map contenente i Tweet "Sopravvissuti" ad una eventuale
	 *                Applicazione di un altro Filtro.
	 * @return Una Map contenente i Tweet scritti nella Lingua specificata dall'Utente.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 */
	
	public Map<Integer, Data> filterDataValue(Map<Integer, Data> tempMap)
			throws IllegalFilterValueSizeException{
		Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
		
		try {
			if(filterValue.length() > 2)
				throw new IllegalFilterValueSizeException();
			
			for(Map.Entry<Integer, Data> field : tempMap.entrySet()) {
				if(this.filterValue.equals(field.getValue().getLanguage()))
					filteredDataValue.put(field.getKey(), field.getValue());
			}
			
	}catch(IllegalFilterValueSizeException ex) {
		throw new IllegalFilterValueSizeException(filterValue + " Filter Value is Unrecognizable");
	}
		
		return filteredDataValue;
	}

}
