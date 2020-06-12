package com.univpm.TweetAnalyzer.service;

import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.SingleStats;
import com.univpm.TweetAnalyzer.model.TotalStats;
import com.univpm.TweetAnalyzer.model.time.Date;
import com.univpm.TweetAnalyzer.model.time.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class StatisticsService {
    private static Map<String, Object> statsMap;
    private static ArrayList<SingleStats> statsList;
    private static ArrayList<SingleStats> tempList;
    private static TotalStats totalStats;
    
    public static double roundDecimals (double number, int decimals) {
        double factor = Math.pow (10, decimals);
        return Math.floor (number * factor + 0.5) / factor;
    }
    
    public static TotalStats doStats(Map<String, Map<Integer, Data>> dataMap) {
    	statsMap = new HashMap<String, Object>();
    	statsList = new ArrayList<SingleStats>();
    	tempList = new ArrayList<SingleStats>();
    	totalStats = new TotalStats();
    	
        try {
        	
        	numberStats(tempList, dataMap);
        	percStats(tempList);
        	dateStats(tempList, dataMap);
        	
        	}catch (Exception e) {

            e.printStackTrace();
        }
        
        return totalStats;

    }
    
    public static void numberStats(ArrayList<SingleStats> tempList, Map<String, Map<Integer, Data>> dataMap){
    	long Likes = 0;
        long Retweets = 0;
        long totalLikes = 0;
        long totalRetweets = 0;
    	
    	for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
    		SingleStats singleStats = null;
    		String Hashtag = entries.getKey();
    		Map<Integer, Data> tempMap = entries.getValue();
        	
            for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
        		singleStats = new SingleStats();
                Likes += entry.getValue().getLikes();
                Retweets += entry.getValue().getRetweets();
                }
            totalLikes += Likes;
            totalRetweets += Retweets;
            
            singleStats.setHashtag(Hashtag);
            singleStats.setLikes(Likes);
            singleStats.setRetweets(Retweets);
            totalStats.setTotLikes(totalLikes);
            totalStats.setTotRetweets(totalRetweets);
            
            tempList.add(singleStats);
            
            Likes = 0;
            Retweets = 0;
            
            totalStats.setHashtags(tempList);
            }
    }
    
    public static void percStats(ArrayList<SingleStats> tempList){
    	long Likes = 0;
    	long Retweets = 0;
    	double PercLikesNum = 0;
    	double PercRetweetsNum = 0;
    	
    	for (SingleStats s : tempList) {
    		
    		Likes = s.getLikes();
    		Retweets = s.getRetweets();
    		
    		PercLikesNum = (double) Likes*100/totalStats.getTotLikes();
    		PercLikesNum = roundDecimals(PercLikesNum, 2);
    		
    		PercRetweetsNum = (double) Retweets*100/totalStats.getTotLikes();
    		PercRetweetsNum = roundDecimals(PercRetweetsNum, 2);
    		
    		s.setPercLikes(PercLikesNum + "%");
    		s.setPercRetweets(PercRetweetsNum + "%");
    		
    		statsList.add(s);
    	}
    	
    }

    public static void dateStats(ArrayList<SingleStats> tempList, Map<String, Map<Integer, Data>> dataMap) {
    	HashSet<Date> cmpDates = new HashSet<Date>();
    	HashSet<Date> tempDates = new HashSet<Date>();
    	for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
    		Map<Integer, Data> tempMap = entries.getValue();
            for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
            	Time cmpTime = DateParsingService.cmpTimeIstance(entry);
                Iterator<Date> it = cmpDates.iterator();
                while(it.hasNext()) {
                	if(it.next().equals(cmpTime.getDate()))
                		cmpDates.add(cmpTime.getDate());
                	}
            	}
            }
    	System.out.println(cmpDates.size());
    	}
    
}
