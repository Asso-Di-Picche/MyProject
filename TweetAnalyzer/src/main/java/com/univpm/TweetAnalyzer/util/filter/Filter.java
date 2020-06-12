package com.univpm.TweetAnalyzer.util.filter;

import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.model.Data;

public interface Filter {
	
	public abstract Map<String, Map<Integer, Data>> filtrate(Map<String, Map<Integer, Data>> filteredData)
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException;

}
