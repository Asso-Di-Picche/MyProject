package com.univpm.TweetAnalyzer.VariousTest.StatsFiltersTest;


import com.univpm.TweetAnalyzer.ServiceTest.FilterService;
import com.univpm.TweetAnalyzer.ServiceTest.StatisticsService;
import com.univpm.TweetAnalyzer.VariousTest.ActualData;
import com.univpm.TweetAnalyzer.exception.*;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.SingleStats;
import com.univpm.TweetAnalyzer.model.TotalStats;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Test stats+filter che simula la richiesta di POST /stats. Viene costruito un filtro,
 * si ricava un database filtrato, si applica il metodo doStats() su questo database,
 * ricavandone le statistiche.
 */
public class StatsFiltersTest {

    private static Map<String, Map<Integer, Data>> dataTest = ActualData.getDatasToTest();
    private static LinkedHashMap<String, Object> filter = new LinkedHashMap<String, Object>();

    private static ArrayList<SingleStats> stats = ExpectedStatsFilters.getExpected();
    private static TotalStats totalStats = new TotalStats(1000, 1700, stats);


    /**
     * Stats filter test.
     *
     * @throws DuplicateFilterException        the duplicate filter exception
     * @throws IllegalFilterValueSizeException the illegal filter value size exception
     * @throws IllegalFilterValueException     the illegal filter value exception
     * @throws IllegalFilterKeyException       the illegal filter key exception
     * @throws IllegalTimeException            the illegal time exception
     * @throws StatisticsNotAppliedException   the statistics not applied exception
     */
    @Test
    @DisplayName("Test su filtro + statistiche")
    public void StatsFilterTest() throws DuplicateFilterException, IllegalFilterValueSizeException, IllegalFilterValueException, IllegalFilterKeyException, IllegalTimeException, StatisticsNotAppliedException {

        filter.put("Language", "en");
        Map<String, Map<Integer, Data>> filteredData = FilterService.filterParsing(filter, dataTest);

        assertEquals(totalStats, StatisticsService.doStats(filteredData));
    }
}
