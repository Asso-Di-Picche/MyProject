package com.univpm.TweetAnalyzer.test;

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
	 * @return Il Database da Testare rimepito con 10 Tweet Fittizi.
	 */
	
	public static Map<String, Map<Integer, Data>> getDatasToTest() {
		return datasInsertion();
	}
	
	/**
	 * Qui Viene Riempita la Map costituente il Database Fittizio.
	 * @return Il Database da Testare.
	 */
	
	public static Map<String, Map<Integer, Data>> datasInsertion() {
		dataTest.put("Prada", innerMapFill(1, new Data(
				"en", "test", "Tue Jun 16 10:00:00 +0000 2020", 123, 1000, 100, 800)));
		dataTest.put("Prada", innerMapFill(2, new Data(
				"it", "test", "Tue Jun 15 9:00:00 +0000 2020", 123, 900, 200, 700)));
		dataTest.put("Prada", innerMapFill(3, new Data(
				"it", "test", "Tue Jun 14 8:00:00 +0000 2020", 123, 800, 300, 600)));
		dataTest.put("Gucci", innerMapFill(4, new Data(
				"en", "test", "Tue Jun 13 7:00:00 +0000 2020", 123, 700, 400, 500)));
		dataTest.put("Gucci", innerMapFill(5, new Data(
				"en", "test", "Tue Jun 12 6:00:00 +0000 2020", 123, 600, 500, 400)));
		dataTest.put("Trussardi", innerMapFill(6, new Data(
				"it", "test", "Tue Jun 11 5:00:00 +0000 2020", 123, 500, 600, 400)));
		dataTest.put("Valentino", innerMapFill(7, new Data(
				"en", "test", "Tue Jun 10 4:00:00 +0000 2020", 123, 400, 700, 500)));
		dataTest.put("Zegna", innerMapFill(8, new Data(
				"it", "test", "Tue Jun 9 3:00:00 +0000 2020", 123, 300, 800, 600)));
		dataTest.put("Dior", innerMapFill(9, new Data(
				"en", "test", "Tue Jun 8 2:00:00 +0000 2020", 123, 200, 900, 700)));
		dataTest.put("Benetton", innerMapFill(10, new Data(
				"it", "test", "Tue Jun 7 1:00:00 +0000 2020", 123, 100, 1000, 800)));
		return dataTest;
	}
	
	/**
	 * Questo Metodo riempe ciascuna Map relativa ad ogni Hashtag utilizzato.
	 * @param index L'identificativo del Tweet nella Map.
	 * @param data I Dati relativi ai Tweet.
	 * @return
	 */
	
	public static Map<Integer, Data> innerMapFill(int index, Data data) {
		Map<Integer, Data> innerMap = new HashMap<Integer, Data>();
		innerMap.put(index, data);
		return innerMap;
	}
	
}
