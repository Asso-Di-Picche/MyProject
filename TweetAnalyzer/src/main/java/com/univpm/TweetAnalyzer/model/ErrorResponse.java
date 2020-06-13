package com.univpm.TweetAnalyzer.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe le cui Istanze servono a fornire informazioni dettagliate
 * riguardo le eventuali Eccezioni Personalizzate lanciate durante
 * l'esecuzione dell'Applicazione.
 */

public class ErrorResponse {
	private String Message;
	private Map<String, Object> Details = new HashMap<>();
	
	public ErrorResponse(String Message, Map<String, Object> Details) {
		this.Message = Message;
		this.Details = Details;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

	public Map<String, Object> getDetails() {
		return Details;
	}

	public void setDetails(Map<String, Object> Details) {
		this.Details = Details;
	}
	
}
