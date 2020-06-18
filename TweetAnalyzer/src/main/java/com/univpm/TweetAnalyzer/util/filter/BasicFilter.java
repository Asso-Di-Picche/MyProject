package com.univpm.TweetAnalyzer.util.filter;

import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa Classe Astratta Viene Estesa dalle Classi che Istanziano Filtri che potrebbero
 * eventualmente costituire un Filtro Composto di più Oggetti JSON.
 */

public abstract class BasicFilter implements Filter{
	
	protected static Map<String, Map<Integer, Data>> tempData;
	protected static String filterType;

	/**
	 * @param tempData Contiene i Dati, "Sopravvissuti" alla Rassegna di un Filtro, 
	 *                 costantemente Aggiornati.
	 * @param filterType Contiene il Nome/Chiave del Filtro che è Attualmente in Applicazione.
	 */
	
	public BasicFilter(Map<String, Map<Integer, Data>> tempData, String filterType) {
		
		BasicFilter.filterType = filterType;
		BasicFilter.tempData = tempData;
		
	}
	
}