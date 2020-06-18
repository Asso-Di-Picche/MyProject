package com.univpm.TweetAnalyzer.VariousTest.StatsFiltersTest;


import com.univpm.TweetAnalyzer.service.FilterService;
import com.univpm.TweetAnalyzer.service.StatisticsService;
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
     * Il Metodo che Effettua il Test.
     *
	 * @throws DuplicateFilterException Se vengono inseriti più Filtri dello stesso Tipo.
	 * @throws IllegalFilterValueException Se viene inserito un Filtro con Campo Value NON riconosciuto.
	 * @throws IllegalTimeException Se viene inserito un Filtro con una Data NON ammessa.
	 * @throws IllegalFilterValueSizeException Se Viene inserito un Filtro con Campo Value di Dimensione NON consentita.
	 * @throws IllegalFilterKeyException Se viene inserito un Filtro con una Chiave NON esistente.
     * @throws StatisticsNotAppliedException Se ci sono stati Errori nei Calcoli delle Statistiche.
     */
    
    @Test
    @DisplayName("Test su filtro + statistiche")
    public void StatsFilterTest() throws DuplicateFilterException, IllegalFilterValueSizeException, IllegalFilterValueException, IllegalFilterKeyException, IllegalTimeException, StatisticsNotAppliedException {

        filter.put("Language", "en");
        Map<String, Map<Integer, Data>> filteredData = FilterService.filterParsing(filter, dataTest);

        assertEquals(totalStats, StatisticsService.doStats(filteredData));
    }
}
