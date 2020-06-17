package com.univpm.TweetAnalyzer.statsTest;

import com.univpm.TweetAnalyzer.exception.StatisticsNotAppliedException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.SingleStats;
import com.univpm.TweetAnalyzer.service.StatisticsService;
import com.univpm.TweetAnalyzer.util.filter.DataToTest;
import com.univpm.TweetAnalyzer.model.TotalStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test della classe StatisticService.
 *
 * Viene testato il metodo doStats(), a cui viene passato lo stesso database usato
 * nel test sui filtri (DataToTest). Tramite un assertEquals, viene confrontato con un altro database costruito per l'occasione,
 * ovvero dei dati che ci aspettiamo dal metodo doStats().
 */
class StatisticsServiceTest {

    private static ArrayList<SingleStats> hashtags = new ArrayList<SingleStats>();
    private static Map<String, Map<Integer, Data>> actualData = DataToTest.getDatasToTest();
    private static TotalStats totStas = new TotalStats(5500, 6000, hashtags);

    /**
     *  Costruzione del database che ci aspettiamo.
     */
    @BeforeEach
    void setUp() {

        SingleStats prada = new SingleStats(
                "Prada", "35.0%", "10.91%", 2100, 600, 1.0);
        hashtags.add(prada);

        SingleStats gucci = new SingleStats(
                "Gucci", "15.0%", "16.36%", 900, 900, 1.2);
        hashtags.add(gucci);

        SingleStats benetton = new SingleStats(
                "Benetton", "25.0%", "34.55%", 1500, 1900, 1.17);
        hashtags.add(benetton);

        SingleStats zegna = new SingleStats(
                "Zegna", "10.0%", "14.55%", 600, 800, 1.15);
        hashtags.add(zegna);

        SingleStats trussardi = new SingleStats(
                "Trussardi", "6.67%", "10.91%", 400, 600, 1.13);
        hashtags.add(trussardi);

        SingleStats valentino = new SingleStats(
                "Valentino", "8.33%", "12.73%", 500, 700, 1.11 );
        hashtags.add(valentino);
    }

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