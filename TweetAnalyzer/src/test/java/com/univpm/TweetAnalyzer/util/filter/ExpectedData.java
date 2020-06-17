package com.univpm.TweetAnalyzer.util.filter;

import com.univpm.TweetAnalyzer.model.Data;

import java.util.HashMap;
import java.util.Map;

public class ExpectedData {

    private static Map<String, Map<Integer, Data>> expectedData = new HashMap<String, Map<Integer, Data>>();

    public static Map<Integer, Data> innerExpectedMapFill(int index, Data data) {
        Map<Integer, Data> innerExpectedMap = new HashMap<Integer, Data>();
        innerExpectedMap.put(index, data);
        return innerExpectedMap;
    }

    public static Map<String, Map<Integer, Data>> expectedDataInsertion() {

        expectedData.put("Benetton", innerExpectedMapFill(9, new Data(
                "it", "test", "Tue Jun 8 2:00:00 +0000 2020", 123, 200, 900, 700)));
        expectedData.put("Benetton", innerExpectedMapFill(10, new Data(
                "it", "test", "Tue Jun 7 1:00:00 +0000 2020", 123, 100, 1000, 800)));
        return expectedData;
    }

    public static Map<String, Map<Integer, Data>> getExpectedData() {
        return expectedDataInsertion();
    }


}
