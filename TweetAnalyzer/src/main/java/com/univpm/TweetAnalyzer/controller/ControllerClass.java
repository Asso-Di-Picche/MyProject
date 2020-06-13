package com.univpm.TweetAnalyzer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univpm.TweetAnalyzer.bin.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.FilterNotFoundException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.exception.StatisticsNotAppliedException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.service.FilterService;
import com.univpm.TweetAnalyzer.service.StatisticsService;

@RestController
public class ControllerClass {

	@RequestMapping(value = "metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata() {
		return new ResponseEntity<>(DatabaseClass.getMetadataList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "data", method = RequestMethod.GET)
	public ResponseEntity<Object> getDataMap() {
		return new ResponseEntity<>(DatabaseClass.getDataMap(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "data", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredDataMap(@RequestBody Object filter)
			throws FilterNotFoundException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException {
		return new ResponseEntity<>(FilterService.filterParsing(filter), HttpStatus.OK);
	}
	
	@RequestMapping(value = "stats", method = RequestMethod.GET)
	public ResponseEntity<Object> getStats() throws StatisticsNotAppliedException{
		return new ResponseEntity<>(StatisticsService.doStats(DatabaseClass.getDataMap()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "stats", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredDataStats(@RequestBody Object filter)
			throws FilterNotFoundException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException, StatisticsNotAppliedException{
		Map<String, Map<Integer, Data>> dataMap = FilterService.filterParsing(filter);
		return new ResponseEntity<>(StatisticsService.doStats(dataMap), HttpStatus.OK);
	}
}
