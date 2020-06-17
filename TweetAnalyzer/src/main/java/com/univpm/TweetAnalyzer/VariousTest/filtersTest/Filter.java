package com.univpm.TweetAnalyzer.VariousTest.filtersTest;

import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa Interfaccia viene Implementata da Tutte le Classi le cui Istanze sono Filtri.
 */

public interface Filter {
	
	/**
	 * Questo Metodo serve per Selezionare i Dati in base al Filtro che è al momento in Applicazione.
	 * @return Una Map contenente i Dati Filtrati.
	 * @throws IllegalTimeException Se un'eventuale Data del Filtro è stata inserita in un modo NON consentito.
	 * @throws IllegalFilterValueException Se viene inserito un Filtro con Campo Value NON riconosciuto.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 */
	
	public abstract Map<String, Map<Integer, Data>> filtrate()
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException;

}
