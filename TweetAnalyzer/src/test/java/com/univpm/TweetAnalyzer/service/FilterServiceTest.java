package com.univpm.TweetAnalyzer.service;

import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Test sull'applicazione di un filtro sbagliato. Passando al metodo filterParsing(filter)
 * un "filter" con una chiave errata (non esistente), viene lanciata l'eccezione personalizzata IllegalFilterKeyException.
 */
class FilterServiceTest {

    private static Map<String, String> filter = new LinkedHashMap<String, String>();
    private Exception IllegalFilterKeyException;

    /**
     * Filter test.
     */
    @Test
    @DisplayName("Verifica il filtro passato")
    @Tag("Filter")
    void filterTest() {
    	filter.put("Pigna","it");
        assertThrows(IllegalFilterKeyException.class, () -> FilterService.filterParsing(filter), "La chiave del filtro immesso non è corretta");
    }
}