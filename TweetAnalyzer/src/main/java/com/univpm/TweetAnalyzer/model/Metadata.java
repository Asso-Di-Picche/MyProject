package com.univpm.TweetAnalyzer.model;

/**
 * Questa è la classe le cui Istanze rappresentano i Metadati.
 */

public class Metadata {
	
	private String Metadata, WrapperClass;
	
	public Metadata(String Metadata, String WrapperClass) {
		this.Metadata = Metadata;
		this.WrapperClass = WrapperClass;
	}

	public String getMetadata() {
		return Metadata;
	}

	public void setMetadata(String metadata) {
		Metadata = metadata;
	}

	public String getWrapperClass() {
		return WrapperClass;
	}

	public void setWrapperClass(String wrapperClass) {
		WrapperClass = wrapperClass;
	}
	
}
