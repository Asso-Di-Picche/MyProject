package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.time.Time;
import com.univpm.TweetAnalyzer.Service.DateParsingService;

/**
 * Questa è la Classe le cui Istanze sono Filtri che vengono Applicati in base ad una Data
 * inserita dall'Utente.
 */

public class SingleDateFilter extends BasicFilter{
	
	//Questa Variabile contiene la Data inserita dall'Utente.
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
	public Map<String, Map<Integer, Data>> filtrate() throws IllegalTimeException {
		
		//Questa Map serve per contenere i Dati da Restituire una volta che sono stati Filtrati.
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		
		Time cmpTime = null;
		Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
		
		Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
			
			String filteredDataKey = pointedMap.getKey();
			HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
			
			for(Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				
				//Questa Variabile Contiene la Data rispetto alla quale fare il Confronto
				//man mano che vengono Ciclati i Tweet.
				cmpTime = DateParsingService.cmpTimeIstance(entry);
				
				//Qui viene fatto un Controllo in base all'Operatore da Applicare con il Filtro.
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

	/**
	 * Questo Metodo serve per Aggiungere un Tweet ad una Map nel caso in cui
	 * quest'ultimo è stato Postato Dopo una certa Data.
	 * @param filteredDataValue È la Map in cui Aggiungere man mano i Tweet.
	 * @param entry È il Tweet che eventualmente verrà Aggiunto alla Map.
	 * @param refTime È la Data inserita dall'Utente.
	 * @param cmpTime È la Data relativa al Tweet.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 */
	
	public void afterDate(Map<Integer, Data> filteredDataValue,
			Map.Entry<Integer, Data> entry, Time refTime, Time cmpTime)
					throws IllegalTimeException {
		
		if(refTime.timeCompare(cmpTime))
			filteredDataValue.put(entry.getKey(), entry.getValue());

	}
	
	/**
	 * Questo Metodo serve per Aggiungere un Tweet ad una Map nel caso in cui
	 * quest'ultimo è stato Postato Prima di una certa Data.
	 * @param filteredDataValue È la Map in cui Aggiungere man mano i Tweet.
	 * @param entry È il Tweet che eventualmente verrà Aggiunto alla Map.
	 * @param refTime È la Data inserita dall'Utente.
	 * @param cmpTime È la Data relativa al Tweet.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 */
	
	public void beforeDate(Map<Integer, Data> filteredDataValue,
			Map.Entry<Integer, Data> entry, Object refTime, Time cmpTime)
					throws IllegalTimeException {

		if(cmpTime.timeCompare((Time) refTime))
			filteredDataValue.put(entry.getKey(), entry.getValue());
	
	}
	
}