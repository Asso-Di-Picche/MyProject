package com.univpm.TweetAnalyzer.VariousTest.StatsFiltersTest;

import com.univpm.TweetAnalyzer.model.SingleStats;

import java.util.ArrayList;

/**
 * Questa è la Classe che contiene il Risultato Atteso dal Test sulle Statistiche
 * applicate ad un Database Filtrato.
 */

public class ExpectedStatsFilters {

    private static ArrayList<SingleStats> expected = new ArrayList<SingleStats>();

    /**
     * Il Metodo nel quale viene costruito il Risultato che ci si aspetta
     * in seguito al Test.
     */
    
    public static ArrayList<SingleStats> dataInsertion() {

        SingleStats prada = new SingleStats(
                "Prada", "47.06%", "10.0%", 800, 100, 1.0);
        expected.add(prada);

        SingleStats gucci = new SingleStats(
                "Gucci", "52.94%", "90.0%", 900, 900, 1.33);
        expected.add(gucci);

        return expected;
    }

    public static ArrayList<SingleStats> getExpected() {
        return dataInsertion();
    }
}
