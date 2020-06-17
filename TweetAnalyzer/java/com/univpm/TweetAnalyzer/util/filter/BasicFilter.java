package com.univpm.TweetAnalyzer.util.filter;

import java.util.Map;

import com.univpm.TweetAnalyzer.DatabaseClassTest.DatabaseClass;

public abstract class BasicFilter implements Filter{
	
	protected static Map<String, Map> tempData;
	protected static String filterType;

	public BasicFilter(Map<String, Map> tempData, String filterType) {
		
		this.filterType = filterType;
		this.tempData = tempData;
		
	}
	
}