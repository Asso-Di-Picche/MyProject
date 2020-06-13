package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe che istanzia Oggetti di tipo StatisticsNotAppliedException.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatisticsNotAppliedException extends Exception{
	private static final long serialVersionUID = 6L;
	
	public StatisticsNotAppliedException(String Message) {
		super(Message);
	}

}
