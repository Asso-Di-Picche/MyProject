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
    private static ArrayList<LinkedHashMap<String, String>> filter = new ArrayList<LinkedHashMap<String,String>>();

    public static LinkedHashMap<String, String> innerLinkedFill(String one, String two) {
        LinkedHashMap<String, String> innerLinkedMap = new LinkedHashMap<String, String>();
        innerLinkedMap.put(one, two);
        return innerLinkedMap;
    }

    @Test
    @DisplayName("Test su filtro di hashtag")
    void hashtagTest() throws DuplicateFilterException, IllegalFilterValueSizeException, IllegalFilterValueException, IllegalFilterKeyException, IllegalTimeException {

        filter.add(innerLinkedFill("#", "Benetton"));
        filter.add(innerLinkedFill("Language", "it"));

        assertEquals(ExpectedData.getExpectedData(), FilterService.filterParsing(filter, actualData));

    }
}