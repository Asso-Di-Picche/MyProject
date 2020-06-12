package com.univpm.TweetAnalyzer.util.filter;

import java.util.Map;

import com.univpm.TweetAnalyzer.model.Data;

public abstract class BasicFilter implements Filter{
	
	protected static Map<String, Map<Integer, Data>> tempData;
	protected static String filterType;

	public BasicFilter(Map<String, Map<Integer, Data>> tempData, String filterType) {
		
		this.filterType = filterType;
		this.tempData = tempData;
		
	}
	
}