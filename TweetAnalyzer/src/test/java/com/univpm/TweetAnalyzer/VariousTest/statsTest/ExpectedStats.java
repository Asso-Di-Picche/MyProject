package com.univpm.TweetAnalyzer.VariousTest.statsTest;

import com.univpm.TweetAnalyzer.model.SingleStats;

import java.util.ArrayList;


/**
 * Costruzione del database che ci aspettiamo.
 */
public class ExpectedStats {

    private static ArrayList<SingleStats> hashtags = new ArrayList<SingleStats>();

    /**
     * Inserisco i dati nel database.
     *
     * @return the array list
     */
    public static ArrayList<SingleStats> dataInsertion() {

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

        return hashtags;
    }

    /**
     * Gets hashtags.
     *
     * @return dataInsertion()
     */
    public static ArrayList<SingleStats> getHashtags() {
        return dataInsertion();
    }
}
