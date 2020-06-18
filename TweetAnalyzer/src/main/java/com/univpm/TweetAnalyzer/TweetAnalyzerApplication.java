package com.univpm.TweetAnalyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;

/**
 * Questa è la Classe di Entry Point dell'Applicazione.
 */

@SpringBootApplication
public class TweetAnalyzerApplication {
	
	/**
	 * Subito dopo l'avvio dell'Applicazione viene costruito un Database
	 * con i Dati contenuti nell'API di Twitter.
	 */
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		SpringApplication.run(TweetAnalyzerApplication.class, args);
		DatabaseClass database = new DatabaseClass();
		
	}

}
