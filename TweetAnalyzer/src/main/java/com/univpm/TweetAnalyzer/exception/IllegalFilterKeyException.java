package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class IllegalFilterKeyException extends Exception{
	private static final long serialVersionUID = 2L;

	public IllegalFilterKeyException() {
		
	}
	
	public IllegalFilterKeyException(String Message) {
		super(Message);
	}
}
