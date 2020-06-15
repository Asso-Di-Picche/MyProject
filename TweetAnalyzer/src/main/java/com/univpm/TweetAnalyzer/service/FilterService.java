package com.univpm.TweetAnalyzer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univpm.TweetAnalyzer.bin.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.DuplicateFilterException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;
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

/**
 * Questa è la Classe che si occupa della Gestione dei Filtri JSON.
 */

public class FilterService {
	
	//Questa è la Variabile che conterrà il Filtro una volta Parsato.
	private static Filter filter;
	
	//Questa è la Map contenente Dati Filtrati man mano che viene applicato uno o più Filtri.
	private static Map<String, Map<Integer, Data>> filteredData;
	
	/**
	 * Questa è il Metodo che si occupa di effettuare il Parsing di un filtro JSON.
	 * @param jsonFilter È l'Istanza del Filtro JSON inserito dall'Utente.
	 * @return Una Map contenente i Dati Filtrati.
	 * @throws DuplicateFilterException Se il Filtro è composto da più Filtri contenenti la stessa Chiave.
	 * @throws IllegalFilterValueException Se uno dei Valori inserito dall'Utente nel Filtro è Errato.
	 * @throws IllegalTimeException Se un'eventuale Data del Filtro è stata inserita in un modo NON consentito.
	 * @throws IllegalFilterValueSizeException Se uno dei Valori inserito dall'Utente nel Filtro è di dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se una delle Chiavi inserite dall'Utente nel Filtro è Errata.
	 */
	
	public static Map<String, Map<Integer, Data>> filterParsing(Object jsonFilter)
			throws DuplicateFilterException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException{
		
		//Questa Variabile rappresenta la Map da Filtrare.
		Map<String, Map<Integer, Data>> tempData = DatabaseClass.getDataMap();
		
		//Questa Variabile dovrà contenere il Filtro Parsato.
		HashMap<String, Object> filterMap = new HashMap<String, Object>();
		String filterKey = null;
		Object filterValue = null;
				
		try {
			
			//Questo Controllo serve per Verificare se l'Utente ha inserito o meno un'Array di Filtri nel JSON.
			if(jsonFilter instanceof ArrayList) {
				ArrayList<Object> filterList = new ObjectMapper().convertValue(jsonFilter, ArrayList.class);
				
				for(Object o : filterList) {
					
					//Questa HashMap viene utilizzata per Parsare ciascun Filtro costituente il JSON.
					HashMap<String, Object> tempMap = new ObjectMapper().convertValue(o, HashMap.class);
						
					Iterator<Map.Entry<String, Object>> entries = tempMap.entrySet().iterator();
					Map.Entry<String, Object> entry = entries.next();
					
					filterMap.put(entry.getKey(), entry.getValue());
					
				}
				
				//Questo Controllo sfrutta il fatto che le Map NON possono contenere Duplicati
				//per verificare se effetivamente è necessario lanciare la corrispondente Eccezione.
				if(filterMap.size() < filterList.size())
					throw new DuplicateFilterException();

				Iterator<Map.Entry<String, Object>> entries = filterMap.entrySet().iterator();
				
				//Ad ogni Ciclo viene popolata la Map filteredData mediante l'applicazione
				//di Filtri, fino al punto in cui NON ci sono più Filtri da Applicare.
				while(entries.hasNext()) {
					
					Map.Entry<String, Object> entry = entries.next();
					
					filterKey = entry.getKey();
					filterValue = entry.getValue();
					

					filteredData = new HashMap<String, Map<Integer, Data>>();
					filteredData = filterRun(tempData, filterKey, filterValue);
					tempData = new HashMap<String, Map<Integer, Data>>();
					tempData = filteredData;
				}
				
			}
			
			//Questo Else viene eseguito dall'Applicazione se l'Utente ha deciso di
			//Applicare un Unico Filtro
			else {
				filterMap = new ObjectMapper().convertValue(jsonFilter, HashMap.class);
				
				Iterator<Map.Entry<String, Object>> entries = filterMap.entrySet().iterator();
				Map.Entry<String, Object> entry = entries.next();
				
				filterKey = entry.getKey();
				filterValue = entry.getValue();
				
				filteredData = filterRun(tempData, filterKey, filterValue);
			}
		}
		catch(DuplicateFilterException ex) {
			throw new DuplicateFilterException("Filters Can Not Have Duplicates!");
		}
		
		return filteredData;
		
	}
	
	/**
	 * Questo Metodo viene utilizzato per Inizializzare un Filtro mediante un Controllo sul Nome,
	 * per poi in seguito Applicarlo mediante il concetto di Ereditarietà.
	 * @param tempData Contiene i Dati dei Tweet eventualmente "Sopravvissuti" ad una Precedente rassegna,
	 *                 e che sono quindi pronti ad essere sottoposti all'Applicazione di un nuovo Filtro.
	 * @param filterKey Contiene il Nome/Chiave del Filtro da Applicare.
	 * @param filterValue Contiene una Stringa o un ArrayList che costituiscono l'Argomento del Filtro.
	 * @return Una Map di Dati filtrata.
	 * @throws DuplicateFilterException Se il Filtro è composto da più Filtri contenenti la stessa Chiave.
	 * @throws IllegalFilterValueException Se uno dei Valori inserito dall'Utente nel Filtro è Errato.
	 * @throws IllegalTimeException Se un'eventuale Data del Filtro è stata inserita in un modo NON consentito.
	 * @throws IllegalFilterValueSizeException Se uno dei Valori inserito dall'Utente nel Filtro è di dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se una delle Chiavi inserite dall'Utente nel Filtro è Errata.
	 */
	
	public static Map<String, Map<Integer, Data>> filterRun(Map<String, Map<Integer, Data>> tempData, String filterKey, Object filterValue)
			throws DuplicateFilterException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException{
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
		
		filteredData = filter.filtrate();
			
		return filteredData;
	}

}
