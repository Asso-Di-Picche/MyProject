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
import com.univpm.TweetAnalyzer.service.DateParsingService;

/**
 * Questa è la Classe le cui Istanze sono Filtri che vengono Applicati in base ad una
 * Fascia Oraria inserita dall'Utente.
 */

public class MultipleDateFilter extends BasicFilter{
	
	private SingleDateFilter[] filterDate;
	
	public MultipleDateFilter(Map<String, Map<Integer, Data>> tempData, String filterType, ArrayList<String> filterValue)
			throws IllegalFilterValueException {
		super(tempData, filterType);

		this.filterDate = new SingleDateFilter[2];
		Time[] filterValues = new Time[2];
		ArrayList<String> filterValueArrayList = new ObjectMapper().convertValue(filterValue, ArrayList.class);
		Iterator<String> iterator = filterValueArrayList.iterator();
		
		//L'Attributo di Classe filterDate viene riempito con le due Date inserite dall'Utente,
		//che costituiscono la Fascia Oraria rispetto alla quale Filtrare i Dati.
		for(int i = 0; iterator.hasNext(); i++) {
			filterValues[i] = (DateParsingService.filterValueDateParsing(iterator.next()));
			this.filterDate[i] = (new SingleDateFilter(tempData, filterType, filterValues[i]));
		}
		
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate() throws IllegalTimeException {
		
		//Questa Map serve per contenere i Dati da Restituire una volta che sono stati Filtrati.
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		
		HashMap<Integer, Data> filteredAfter = new HashMap<Integer, Data>();
		HashMap<Integer, Data> filteredBefore = new HashMap<Integer, Data>();
		Time cmpTime = null;
		
		Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
			
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			for(Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				
				//Questa Variabile Contiene la Data rispetto alla quale fare il Confronto
				//man mano che vengono Ciclati i Tweet.
				cmpTime = DateParsingService.cmpTimeIstance(entry);
				
				//Qui viene fatta una prima Scrematura dei Tweet postati Dopo una certa Data.
				filterDate[0].afterDate(
						filteredAfter, entry, filterDate[0].getFilterValue(), cmpTime);
			}
			
			for(Map.Entry<Integer, Data> entry : filteredAfter.entrySet()) {
				
				//Questa Variabile Contiene la Data rispetto alla quale fare il Confronto
				//man mano che vengono Ciclati i Tweet.
				cmpTime = DateParsingService.cmpTimeIstance(entry);
				
				//Qui viene fatta un'ultima Scrematura dei Tweet postati Prima di una certa Data.
				filterDate[1].beforeDate(
						filteredBefore, entry, filterDate[1].getFilterValue(), cmpTime);
			}

			filteredData.put(filteredDataKey, filteredBefore);
			filteredDataKey = null;
			filteredAfter = new HashMap<Integer, Data>();
			filteredBefore = new HashMap<Integer, Data>();
			
		}
		
		return filteredData;
	}

}