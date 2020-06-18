package com.univpm.TweetAnalyzer.Service;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.service.FilterService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * AssertThrows test sull'applicazione di un filtro sbagliato. Passando al metodo filterParsing(filter)
 * un "filter" con una chiave errata (non esistente), viene lanciata l'eccezione personalizzata IllegalFilterKeyException.
 */

class ProperFilterTest {

    private static Map<String, String> filter = new LinkedHashMap<String, String>();

    /**
     * Il Metodo che Effettua il Test.
     */
    
    @Test
    @DisplayName("Verifica filtro passato ed eccezione")
    @Tag("Filter")
    void properFilterTest() {

    	filter.put("Pigna","it");
        assertThrows(IllegalFilterKeyException.class, () -> FilterService.filterParsing(filter, DatabaseClass.getDataMap()));
    }
}