package com.univpm.TweetAnalyzer.util.filter;

import com.univpm.TweetAnalyzer.exception.*;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.service.FilterService;
import com.univpm.TweetAnalyzer.service.StatisticsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonFilterTest {

    private static Map<String, Map<Integer, Data>> actualData = DataToTest.getDatasToTest();
    private static ArrayList<LinkedHashMap<String, Object>> filter = new ArrayList<LinkedHashMap<String,Object>>();

    public static LinkedHashMap<String, Object> innerLinkedFill(String key, Object val) {
        LinkedHashMap<String, Object> innerLinkedMap = new LinkedHashMap<String, Object>();
        innerLinkedMap.put(key, val);
        return innerLinkedMap;
    }

    @Test
    @DisplayName("Test su filtro di hashtag")
    void hashtagTest() throws DuplicateFilterException, IllegalFilterValueSizeException, IllegalFilterValueException, IllegalFilterKeyException, IllegalTimeException {
    	ArrayList<String> val = new ArrayList<String>();

    	val.add("12/06/2020 10:00");
    	val.add("16/06/2020 11:00");
        filter.add(innerLinkedFill("Language", "it"));
        filter.add(innerLinkedFill("Between", val));

        assertEquals(ExpectedData.getExpectedData(), FilterService.filterParsing(filter, actualData));

    }
}