package com.univpm.TweetAnalyzer.VariousTest.StatsFiltersTest;

import com.univpm.TweetAnalyzer.model.SingleStats;

import java.util.ArrayList;

public class ExpectedStatsFilters {

    private static ArrayList<SingleStats> expected = new ArrayList<SingleStats>();

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
