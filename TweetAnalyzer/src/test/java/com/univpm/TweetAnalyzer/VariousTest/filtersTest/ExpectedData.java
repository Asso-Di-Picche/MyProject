package com.univpm.TweetAnalyzer.VariousTest.filtersTest;

import com.univpm.TweetAnalyzer.model.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Questa è la Classe che contiene il Risultato Atteso dal Test sui Filtri.
 */

public class ExpectedData {

    private static Map<String, Map<Integer, Data>> expectedData = new HashMap<String, Map<Integer, Data>>();

    public static Map<String, Map<Integer, Data>> getExpectedData() {
        return expectedDataInsertion();
    }
    
    /**
     * In questo Metodo viene riempita la Map che si ritiene sarà il risultato
     * del Test sui Filtri.
     */
    
    public static Map<String, Map<Integer, Data>> expectedDataInsertion() {

    	Map<Integer, Data> innerMap = new HashMap<Integer, Data>();
    	
    	innerMap.put(2, new Data(
    			"it", "test", "Tue Jun 15 09:00:00 +0000 2020", 123, 900, 200, 700));
    	innerMap.put(3, new Data(
    			"it", "test", "Tue Jun 14 08:00:00 +0000 2020", 123, 800, 300, 600));
    	
        expectedData.put("Prada", innerMap);
        expectedData.put("Prada", innerMap);
        
        Map<Integer, Data> emptyMap = new HashMap<Integer, Data>();
    	
        expectedData.put("Gucci", emptyMap);
        expectedData.put("Benetton", emptyMap);
        expectedData.put("Zegna", emptyMap);
        expectedData.put("Trussardi", emptyMap);
        expectedData.put("Valentino", emptyMap);
        
        return expectedData;
    }

}
