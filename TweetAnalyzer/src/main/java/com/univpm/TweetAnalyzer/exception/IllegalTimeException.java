package com.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalTimeException extends Exception{
	private static final long serialVersionUID = 5L;
	
	public IllegalTimeException(String Message) {
		super(Message);
	}

}
