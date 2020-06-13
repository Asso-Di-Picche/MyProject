package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe che istanzia Oggetti di tipo IllegalFilterValueException.
 */

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class IllegalFilterValueException extends Exception{
	private static final long serialVersionUID = 3L;

	public IllegalFilterValueException() {
	}
	
	public IllegalFilterValueException(String Message) {
		super(Message);
	}
	
}
