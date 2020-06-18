package com.univpm.TweetAnalyzer.Service;

import java.util.Map;
import java.util.Scanner;

import com.univpm.TweetAnalyzer.exception.IllegalFilterValueException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.time.Date;
import com.univpm.TweetAnalyzer.model.time.Hours;
import com.univpm.TweetAnalyzer.model.time.Time;

public class DateParsingService {
	
	public static Time filterValueDateParsing(String filterValue) throws IllegalFilterValueException{
		Scanner in = new Scanner(filterValue);
		String tmpStr = "";
		String subStr = "";
		int Month = 0, Day = 0, Hour = 0, Minute = 0, Year = 0;
		
		try {
			in.useDelimiter("/");
			tmpStr = in.next();
			Day = Integer.parseInt(tmpStr);
			tmpStr = in.next();
			Month = Integer.parseInt(tmpStr);
			
			in.useDelimiter(" ");
			tmpStr = in.next();
			subStr = tmpStr.substring(1);
			Year = Integer.parseInt(subStr);
			
			in.useDelimiter(":");
			tmpStr = in.next();
			subStr = tmpStr.substring(1);
			Hour = Integer.parseInt(subStr);
			
			tmpStr = in.next();
			Minute = Integer.parseInt(tmpStr);
		}
		catch(Exception ex) {
			throw new IllegalFilterValueException(filterValue + " Filter Value is Unrecognizable");
		}
		finally {
			in.close();
		}
		
		return new Time(new Date(Day, Month, Year), new Hours(Hour, Minute));
	}
	

	public static Time cmpTimeIstance(Map.Entry<Integer, Data> entry) {
		String dateString = "";
		String subDateString = "";
		
		dateString = entry.getValue().getDate();
		subDateString = (dateString.substring(4, 13) + " ").concat(
				dateString.substring(14, 16) + " ").concat(
						dateString.substring(26, 30));
		
		return dataDateParsing(subDateString);
	}
	
	public static Time dataDateParsing(String subDateString) {
		Scanner in = new Scanner(subDateString);
		String tempStr = "";
		int Month = 0, Day = 0, Hour = 0, Minute = 0, Year = 0;
		
		while(in.hasNext()) {
			
			tempStr = in.next();
			switch(tempStr) {
			case "Jan": Month = 1;break;
			case "Feb": Month = 2;break;
			case "Mar": Month = 3;break;
			case "Apr": Month = 4;break;
			case "May": Month = 5;break;
			case "Jun": Month = 6;break;
			case "Jul": Month = 7;break;
			case "Aug": Month = 8;break;
			case "Sep": Month = 9;break;
			case "Oct": Month = 10;break;
			case "Nov": Month = 11;break;
			case "Dec": Month = 12;break;
			}
			
			tempStr = in.next();
			Day = Integer.parseInt(tempStr);
			
			tempStr = in.next();
			Hour = Integer.parseInt(tempStr);
			
			tempStr = in.next();
			Minute = Integer.parseInt(tempStr);
			
			tempStr = in.next();
			Year = Integer.parseInt(tempStr);
		}
		in.close();
		
		return new Time(new Date(Day, Month, Year), new Hours(Hour, Minute));
	}
	
}
