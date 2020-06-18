package com.univpm.TweetAnalyzer.model;

/**
 * Questa è la classe le cui Istanze rappresentano i Metadati.
 */

public class Metadata {
	
	private String Metadata, Type;
	
	public Metadata(String Metadata, String Type) {
		this.Metadata = Metadata;
		this.Type = Type;
	}

	public String getMetadata() {
		return Metadata;
	}

	public void setMetadata(String metadata) {
		Metadata = metadata;
	}

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}
	
}
