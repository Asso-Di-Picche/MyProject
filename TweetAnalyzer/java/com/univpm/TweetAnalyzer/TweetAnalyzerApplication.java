package com.univpm.TweetAnalyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.univpm.TweetAnalyzer.DatabaseClassTest.DatabaseClass;
import com.univpm.TweetAnalyzer.model.BasicData;

@SpringBootApplication
public class TweetAnalyzerApplication {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		SpringApplication.run(TweetAnalyzerApplication.class, args);
		System.out.println("Wait for Downloading...");
		DatabaseClass database = new DatabaseClass();
	}

}
