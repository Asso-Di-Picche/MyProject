package com.univpm.TweetAnalyzer.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.DuplicateFilterException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.exception.StatisticsNotAppliedException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.service.FilterService;
import com.univpm.TweetAnalyzer.service.StatisticsService;

/**
 * Classe che gestisce tutte le Rotte e le Chiamate 
 * permesse all'Utente.
 */

@RestController
public class ControllerClass {

	/**
	 * Risponde sulla Rotta /metadata con Chiamata GET.
	 * @return un ArrayList di Metadati e un Codice di Stato HTTP.
	 */
	
	@RequestMapping(value = "metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata() {
		
		return new ResponseEntity<>(DatabaseClass.getMetadataList(), HttpStatus.OK);
	}
	
	/**
	 * Risponde sulla Rotta /data con Chiamata GET.
	 * @return una HashMap di Dati e un Codice di Stato HTTP.
	 */
	
	@RequestMapping(value = "data", method = RequestMethod.GET)
	public ResponseEntity<Object> getDataMap() {
		
		return new ResponseEntity<>(DatabaseClass.getDataMap(), HttpStatus.OK);
	}
	
	/**
	 * Risponde sulla Rotta /data con Chiamata POST.
	 * @param jsonFilter è il JSON del corrispondente Filtro applicato dall'Utente.
	 * @return una HashMap contenente i Dati Filtrati.
	 * @throws FilterNotFoundException Se vengono inseriti più Filtri dello stesso Tipo.
	 * @throws IllegalFilterValueException Se viene inserito un Filtro con Campo Value NON riconosciuto.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se viene inserito un Filtro con una Chiave NON esistente.
	 */
	
	@RequestMapping(value = "data", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredDataMap(@RequestBody Object jsonFilter)
			throws DuplicateFilterException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException {
		
		return new ResponseEntity<>(FilterService.filterParsing(jsonFilter), HttpStatus.OK);
	}
	
	/**
	 * Risponde sulla Rotta /stats con Chiamata GET.
	 * @return un Oggetto di tipo totalStats, contenente Statistiche sui Dati.
	 * @throws StatisticsNotAppliedException Se ci sono stati Errori nei Calcoli delle Statistiche.
	 */
	
	@RequestMapping(value = "stats", method = RequestMethod.GET)
	public ResponseEntity<Object> getStats() throws StatisticsNotAppliedException{
		
		return new ResponseEntity<>(StatisticsService.doStats(DatabaseClass.getDataMap()), HttpStatus.OK);
	}
	
	/**
	 * Risponde sulla Rotta /stats con Chiamata POST.
	 * @param jsonFilter
	 * @return un Oggetto di tipo totalStats contenente Statistiche sui Dati
	 * @throws DuplicateFilterException Se vengono inseriti più Filtri dello stesso Tipo.
	 * @throws IllegalFilterValueException Se viene inserito un Filtro con Campo Value NON riconosciuto.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se viene inserito un Filtro con una Chiave NON esistente.
     * @throws StatisticsNotAppliedException Se ci sono stati Errori nei Calcoli delle Statistiche.
	 */
	
	@RequestMapping(value = "stats", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredDataStats(@RequestBody Object jsonFilter)
			throws DuplicateFilterException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException, StatisticsNotAppliedException{
		
		Map<String, Map<Integer, Data>> dataMap = FilterService.filterParsing(jsonFilter);
		
		return new ResponseEntity<>(StatisticsService.doStats(dataMap), HttpStatus.OK);
	}
	
}
