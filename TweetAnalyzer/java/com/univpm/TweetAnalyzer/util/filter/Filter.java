package com.univpm.TweetAnalyzer.util.filter;

import java.util.Map;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;

public interface Filter {
	
	public abstract Map<String, Map> filtrate(Map filteredData)
			throws IllegalTimeException, IllegalFilterValueException, IllegalFilterValueSizeException;

}
