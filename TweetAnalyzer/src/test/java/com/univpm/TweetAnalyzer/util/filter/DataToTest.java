package com.univpm.TweetAnalyzer.util.filter;

import java.util.HashMap;
import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

/**
 * Questa Classe contiene una Map di Dati Fittizi, sui quali poter Applicare eventuali Tests.
 */

public class DataToTest {
	
	//Questo è l'Attributo utilizzato per contenere il Database da Testare.
	private static Map<String, Map<Integer, Data>> dataTest = new HashMap<String, Map<Integer, Data>>();
	
	/**
	 * @return Il Database da Testare riempito con 10 Tweet Fittizi.
	 */
	
	public static Map<String, Map<Integer, Data>> getDatasToTest() {
		return datasInsertion();
	}
	
	/**
	 * Qui Viene Riempita manualmente la Map costituente il Database Fittizio.
	 * @return Il Database da Testare.
	 */

	public static Map<String, Map<Integer, Data>> datasInsertion() {
		
		Map<Integer, Data> innerMap = new HashMap<Integer, Data>();
		
		innerMap.put(1, new Data(
				"en", "test", "Tue Jun 16 10:00:00 +0000 2020", 123, 1000, 100, 800));
		innerMap.put(2, new Data(
				"it", "test", "Tue Jun 15 09:00:00 +0000 2020", 123, 900, 200, 700));
		innerMap.put(3, new Data(
				"it", "test", "Tue Jun 14 08:00:00 +0000 2020", 123, 800, 300, 600));
		
		dataTest.put("Prada", innerMap);
		
		innerMap = new HashMap<Integer, Data>();
		innerMap.put(4, new Data(
				"en", "test", "Tue Jun 13 07:00:00 +0000 2020", 123, 700, 400, 500));
		innerMap.put(5, new Data(
				"en", "test", "Tue Jun 12 06:00:00 +0000 2020", 123, 600, 500, 400));
		
		dataTest.put("Gucci", innerMap);
		
		innerMap = new HashMap<Integer, Data>();
		innerMap.put(6, new Data(
				"it", "test", "Tue Jun 11 05:00:00 +0000 2020", 123, 500, 600, 400));
		
		dataTest.put("Trussardi", innerMap);
		
		innerMap = new HashMap<Integer, Data>();
		innerMap.put(7, new Data(
				"ja", "test", "Tue Jun 10 04:00:00 +0000 2020", 123, 400, 700, 500));
		
		dataTest.put("Valentino", innerMap);
		
		innerMap = new HashMap<Integer, Data>();
		innerMap.put(8, new Data(
				"ja", "test", "Tue Jun 09 03:00:00 +0000 2020", 123, 300, 800, 600));
		
		dataTest.put("Zegna", innerMap);
		
		innerMap = new HashMap<Integer, Data>();
		innerMap.put(9, new Data(
				"it", "test", "Tue Jun 08 02:00:00 +0000 2020", 123, 200, 900, 700));
		innerMap.put(10, new Data(
				"it", "test", "Tue Jun 07 01:00:00 +0000 2020", 123, 100, 1000, 800));
		
		dataTest.put("Benetton", innerMap);
		dataTest.put("Benetton", innerMap);
		
		return dataTest;
	}

}
