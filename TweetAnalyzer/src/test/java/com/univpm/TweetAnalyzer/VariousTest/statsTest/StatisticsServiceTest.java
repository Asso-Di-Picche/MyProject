package com.univpm.TweetAnalyzer.VariousTest.statsTest;

import com.univpm.TweetAnalyzer.exception.StatisticsNotAppliedException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.ServiceTest.StatisticsService;
import com.univpm.TweetAnalyzer.VariousTest.ActualData;
import com.univpm.TweetAnalyzer.model.TotalStats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test della classe StatisticService.
 *
 * Viene testato il metodo doStats(), a cui viene passato lo stesso database usato
 * nel test sui filtri (ActualData).
 *
 * Tramite un assertEquals, viene confrontato con un altro database costruito per l'occasione,
 * ovvero dei dati che ci aspettiamo dal metodo doStats() (ExpectedStats).
 */
class StatisticsServiceTest {

    private static Map<String, Map<Integer, Data>> actualData = ActualData.getDatasToTest();
    private static TotalStats totStas = new TotalStats(5500, 6000, ExpectedStats.getHashtags());


    /**
     * @throws StatisticsNotAppliedException viene lanciata se non è stata fatta la statistica sui dati
     */

    @Test
    @DisplayName("Test correttezza statistiche")
    @Tag("stats")
    void statsTest() throws StatisticsNotAppliedException {

        assertEquals(totStas, StatisticsService.doStats(actualData));
    }
}