package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilterNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public FilterNotFoundException() {
	}
	
	public FilterNotFoundException(String Message) {
		super(Message);
	}

}
