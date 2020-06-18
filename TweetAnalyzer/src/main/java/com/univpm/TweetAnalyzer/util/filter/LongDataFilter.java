package com.univpm.TweetAnalyzer.util.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa Classe Astratta è Estesa da Tutte le Classi le cui Istanze sono Filtri che Operano
 * mediante Confronti Numerici tra il Valore del Filtro e il Valore di un Campo di un Tweet.
 */

public abstract class LongDataFilter extends BasicFilter{
	protected String[] filterValue;
	
	public LongDataFilter(Map<String, Map<Integer, Data>> tempData, String filterType, ArrayList<String> filterValue) {
		super(tempData, filterType);
		
		//Il Valore del Filtro Applicato NON può avere dimensione Maggiore di 2.
		this.filterValue = new String[2];
		
		Iterator<String> iterator = filterValue.iterator();
		for(int i=0; i<filterValue.size() && iterator.hasNext(); i++)
			this.filterValue[i] = iterator.next();
	}

	@Override
	public Map<String, Map<Integer, Data>> filtrate()
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException {
		
		//Questa Map serve per contenere i Dati da Restituire una volta che sono stati Filtrati.
		Map<String, Map<Integer, Data>> filteredData = new HashMap<String, Map<Integer,Data>>();
		
		try {
			
		    long longFilterValue = Long.parseLong(this.filterValue[1]);
		    
		    //Questo Controllo serve a Verificare che l'Utente NON abbia inserito
		    //un Valore Numerico inferiore a Zero, poiché NON avrebbe senso.
		    if(longFilterValue < 0)
		    	throw new IllegalFilterValueException();
		    
		    //Questa Map serve per Contenere i Dati che hanno "Passato" la Rassegna di un Filtro.
			Map<Integer, Data> filteredDataValue = new HashMap<Integer, Data>();
			
			//Questa Map viene Utilizzata nel caso in cui il Filtro contiene entrambi gli Operatori di confronto.
			Map<Integer, Data> tempFilteredDataValue = new HashMap<Integer, Data>();
			Iterator<Map.Entry<String, Map<Integer, Data>>> iterator = tempData.entrySet().iterator();
			
			while(iterator.hasNext()) {
				Map.Entry<String, Map<Integer, Data>> pointedMap = iterator.next();
				String filteredDataKey = pointedMap.getKey();
				HashMap<Integer, Data> tempMap = (HashMap<Integer, Data>) pointedMap.getValue();
				
				switch(filterValue[0]) {
				case ">":
					filteredDataValue = filterDataValueWithMajorOperator(tempMap, longFilterValue);
					break;
					case "<":
						filteredDataValue = filterDataValueWithMinorOperator(tempMap, longFilterValue);
						break;
						default :
							long leftLongFilterValue = Long.parseLong(this.filterValue[0]);
							if(leftLongFilterValue < 0)
						    	throw new IllegalFilterValueException();
							
							tempFilteredDataValue = filterDataValueWithMajorOperator(tempMap, leftLongFilterValue);
							filteredDataValue = filterDataValueWithMinorOperator(tempFilteredDataValue, longFilterValue);
							break;
				}
				
				filteredData.put(filteredDataKey, filteredDataValue);
				
				filteredDataKey = null;
				tempFilteredDataValue = new HashMap<Integer, Data>();
				filteredDataValue = new HashMap<Integer, Data>();
			
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

	/**
	 * Questa è il Metodo che si occupa di Selezionare solo i Campi di un Dato, Maggiori di un
	 * certo Numero stabilito dall'Utente (contenuto in lonFilterValue).
	 * @param tempMap Contiene i Dati del Tweet Attualmente in Rassegna.
	 * @param longFilterValue Contiene il Valore di Confronto inserito dall'Utente.
	 * @return Una Map contenente o meno il nuovo Tweet.
	 */
	
	protected abstract Map<Integer, Data> filterDataValueWithMajorOperator(
			Map<Integer, Data> tempMap, long longFilterValue);
	
	/**
	 * Questa è il Metodo che si occupa di Selezionare solo i Campi di un Dato, Minori di un
	 * certo Numero stabilito dall'Utente (contenuto in lonFilterValue).
	 * @param tempMap Contiene i Dati del Tweet Attualmente in Rassegna.
	 * @param longFilterValue Contiene il Valore di Confronto inserito dall'Utente.
	 * @return Una Map contenente o meno il nuovo Tweet.
	 */
	
	protected abstract Map<Integer, Data> filterDataValueWithMinorOperator(
			Map<Integer, Data> tempMap, long longFilterValue);

}
