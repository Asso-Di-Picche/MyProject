package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe che istanzia Oggetti di tipo IllegalFilterValueSizeException.
 */

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class IllegalFilterValueSizeException  extends Exception{
	private static final long serialVersionUID = 4L;
	
	public IllegalFilterValueSizeException() {
	}
	
	public IllegalFilterValueSizeException(String Message) {
		super(Message);
	}
	
}
