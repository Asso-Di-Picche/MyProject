package com.univpm.TweetAnalyzer.controller;

import java.nio.channels.NonReadableChannelException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.univpm.TweetAnalyzer.DatabaseClassTest.DatabaseClass;
import com.univpm.TweetAnalyzer.exception.FilterNotFoundException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterKeyException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.exception.IllegalFilterValueSizeException;
import com.univpm.TweetAnalyzer.exception.IllegalTimeException;
import com.univpm.TweetAnalyzer.ServiceTest.FilterService;

@RestController
public class ControllerClass {

	@RequestMapping(value = "metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(DatabaseClass.getMetadataList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "data", method = RequestMethod.GET)
	public ResponseEntity<Object> getDataMap(){
		return new ResponseEntity<>(DatabaseClass.getDataMap(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "data", method = RequestMethod.POST)
	public ResponseEntity<Object> getFilteredDataMap(@RequestBody Object filter)
			throws FilterNotFoundException, IllegalFilterValueException, IllegalTimeException, IllegalFilterValueSizeException, IllegalFilterKeyException{
		return new ResponseEntity<>(FilterService.filterParsing(filter), HttpStatus.OK);
	}
}
