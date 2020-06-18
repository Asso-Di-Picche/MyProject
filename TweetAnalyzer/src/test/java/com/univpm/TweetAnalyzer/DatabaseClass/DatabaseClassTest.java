package com.univpm.TweetAnalyzer.DatabaseClass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test di un metodo della classe Database, ovvero hashtagObtain.
 * Testa, simulando il metodo, la corretta lettura degli hashtag dal file di testo nel path specificato,
 * confrontandoli con il risultato aspettato.
 */

class DatabaseClassTest {

    private static final String path = "Files/hashtags.txt";
    private static ArrayList<String> hashtag = new ArrayList<String>();
    private static String[] hashArray = {"Prada", "Armani", "Ferragamo", "Gucci", "Versace", "Trussardi", "Valentino", "Zegna", "Dior", "Benetton"};

    /**
     * Il Metodo che Effettua il Test.
     * @throws FileNotFoundException Se non viene trovata la Directory contenente gli hashtags.
     */
    
    @Test
    @DisplayName("Test simulazione caricamento hashtag")
    @Tag("hashtag")
    void hashtagObtainTest() throws FileNotFoundException {

        Scanner in = new Scanner(new BufferedReader(new FileReader(path)));

        while (in.hasNext()) {
            hashtag.add(in.next());
        }

        if (in != null) {
            in.close();
        }

        assertIterableEquals(Arrays.asList(hashArray), hashtag, "Tutti uguali");
    }

}