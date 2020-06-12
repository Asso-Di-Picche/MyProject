package com.univpm.TweetAnalyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;

@SpringBootApplication
public class TweetAnalyzerApplication {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		SpringApplication.run(TweetAnalyzerApplication.class, args);
		DatabaseClass database = new DatabaseClass();
	}

}
