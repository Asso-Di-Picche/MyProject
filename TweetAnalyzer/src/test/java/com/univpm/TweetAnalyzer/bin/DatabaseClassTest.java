package com.univpm.TweetAnalyzer.bin;

import com.univpm.TweetAnalyzer.model.Data;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseClassTest {

    DatabaseClass data;
    private static final String path = "/Users/antoniobaio/Desktop/MyProject/TweetAnalyzer/Files/original_hashtags2.txt";
    private static String in = null;
    private static ArrayList<String> hashtag = new ArrayList<String>();
    private static String[] hash = {"Prada", "Armani",
            "Ferragamo",
            "Gucci",
            "Versace",
            "Trussardi",
            "Valentino",
            "Zegna",
            "Dior",
            "Benetton"};

    @Test
    void hashtagObtainTest() throws FileNotFoundException {

        Scanner in = new Scanner(new BufferedReader(new FileReader(path)));

        while (in.hasNext()) {

            hashtag.add(in.next());
        }
        in.close();

        for (int i = 0; i < hashtag.size(); i++) {

            assertEquals(hash[i], hashtag.get(i));
        }
    }

}