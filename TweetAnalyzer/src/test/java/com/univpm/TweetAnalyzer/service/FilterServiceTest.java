package com.univpm.TweetAnalyzer.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

class FilterServiceTest {

    private static Map<String, String> filter = new LinkedHashMap<String, String>();
    private Exception IllegalFilterKeyException;

    @Test
    @DisplayName("Verifica se il filtro è giusto")
    @Tag("Filter")
    void passedTest() {
    	filter.put("Gesù","it");
        assertThrows(IllegalFilterKeyException.class, () -> FilterService.filterParsing(filter), "Il filtro immesso non è corretto");
    }
}