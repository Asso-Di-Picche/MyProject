package com.univpm.TweetAnalyzer.VariousTest.filtersTest;

import com.univpm.TweetAnalyzer.VariousTest.ActualData;
import com.univpm.TweetAnalyzer.exception.*;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.service.FilterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test sul metodo filterParsing() della classe FilterService.
 * E' stato costruito un filtro più complesso, composto dal linguaggio usato e dall'intervallo  di tempo.
 *
 * Viene poi applicato al database fittizio (Actual Data), per filtrare quest'ultimo e confrontare,
 * tramite assertEquals, il risultato rispetto al database aspettato (ExpectedData)
 */

class FilterTest {

    private static Map<String, Map<Integer, Data>> actualData = ActualData.getDatasToTest();
    private static ArrayList<LinkedHashMap<String, Object>> filter = new ArrayList<LinkedHashMap<String,Object>>();

    /**
     * Riempie la LinkedHashMap interna all'ArrayList
     * che vanno a comporre il filtro.
     *
     * @param key la key del filtro
     * @param val il value del filtro
     * @return the linked hash map interna
     */
    public static LinkedHashMap<String, Object> innerLinkedFill(String key, Object val) {
        LinkedHashMap<String, Object> innerLinkedMap = new LinkedHashMap<String, Object>();
        innerLinkedMap.put(key, val);
        return innerLinkedMap;
    }

    /**
     * Il Metodo che Effettua il Test.
     *
	 * @throws DuplicateFilterException Se vengono inseriti più Filtri dello stesso Tipo.
	 * @throws IllegalFilterValueException Se viene inserito un Filtro con Campo Value NON riconosciuto.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se viene inserito un Filtro con una Chiave NON esistente.
     */
    
    @Test
    @DisplayName("Test su filtro composto")
    void complexFilterTest() throws DuplicateFilterException, IllegalFilterValueSizeException, IllegalFilterValueException, IllegalFilterKeyException, IllegalTimeException {
    	ArrayList<String> val = new ArrayList<String>();

    	val.add("12/06/2020 10:00");
    	val.add("16/06/2020 11:00");
        filter.add(innerLinkedFill("Language", "it"));
        filter.add(innerLinkedFill("Between", val));

        assertEquals(ExpectedData.getExpectedData(), FilterService.filterParsing(filter, actualData));

    }
}