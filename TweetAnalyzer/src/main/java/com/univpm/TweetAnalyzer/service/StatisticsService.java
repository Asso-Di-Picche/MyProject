package com.univpm.TweetAnalyzer.service;

import com.univpm.TweetAnalyzer.exception.StatisticsNotAppliedException;
import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.SingleStats;
import com.univpm.TweetAnalyzer.model.TotalStats;
import com.univpm.TweetAnalyzer.model.time.Date;
import com.univpm.TweetAnalyzer.model.time.Time;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class StatisticsService {
	private static ArrayList<SingleStats> statsList;
	private static TotalStats totalStats;

	public static double roundDecimals(double number, int decimals) {
		double factor = Math.pow(10, decimals);
		return Math.floor(number * factor + 0.5) / factor;
	}

	public static TotalStats doStats(Map<String, Map<Integer, Data>> dataMap) throws StatisticsNotAppliedException {
		statsList = new ArrayList<SingleStats>();
		totalStats = new TotalStats();

		try {
			numberStats(statsList, dataMap);
			percStats(statsList);
			dateStats(statsList, dataMap);
			totalStats.setHashtags(statsList);

		} catch (Exception e) {

			throw new StatisticsNotAppliedException("Could Not Calculate Statistics");
		}

		return totalStats;

	}

	public static void numberStats(ArrayList<SingleStats> statsList, Map<String, Map<Integer, Data>> dataMap) {
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
				if (entry.getValue() != null) {

					Likes += entry.getValue().getLikes();
					Retweets += entry.getValue().getRetweets();
				}
			}
			
			if (singleStats != null) {
				totalLikes += Likes;
				totalRetweets += Retweets;

				singleStats.setHashtag(Hashtag);
				singleStats.setLikes(Likes);
				singleStats.setRetweets(Retweets);
				totalStats.setTotLikes(totalLikes);
				totalStats.setTotRetweets(totalRetweets);

				statsList.add(singleStats);

				Likes = 0;
				Retweets = 0;
			}
		}
	}

	public static void percStats(ArrayList<SingleStats> statsList) {
		long Likes = 0;
		long Retweets = 0;
		double PercLikesNum = 0;
		double PercRetweetsNum = 0;

		for (SingleStats s : statsList) {
			Likes = s.getLikes();
			Retweets = s.getRetweets();

			PercLikesNum = (double) Likes * 100 / totalStats.getTotLikes();
			PercLikesNum = roundDecimals(PercLikesNum, 2);

			PercRetweetsNum = (double) Retweets * 100 / totalStats.getTotRetweets();
			PercRetweetsNum = roundDecimals(PercRetweetsNum, 2);

			s.setPercLikes(PercLikesNum + "%");
			s.setPercRetweets(PercRetweetsNum + "%");
		}

	}

	public static void dateStats(ArrayList<SingleStats> statsList, Map<String, Map<Integer, Data>> dataMap) {
		HashSet<Date> cmpDates = new HashSet<Date>();
		ArrayList<Integer> counter = new ArrayList<Integer>();
		double postAverage = 0;

		cmpDates = getDates(cmpDates, dataMap);

		for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
			String hashtag = entries.getKey();
			Map<Integer, Data> tempMap = entries.getValue();
			Iterator<Date> it = cmpDates.iterator();

			while (it.hasNext()) {
				int index = 0;
				Date cmpDate = it.next();

				for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
					Time actTime = DateParsingService.cmpTimeIstance(entry);
					Date actDate = actTime.getDate();

					if (cmpDate.equals(actDate))
						index++;
				}

				if (index != 0)
					counter.add(index);

			}

			for (Integer index : counter)
				postAverage += index;
			
			if(counter.size() != 0) {
				postAverage /= counter.size();
				postAverage = roundDecimals(postAverage, 2);
			}

			for (SingleStats s : statsList) {

				if (s.getHashtag().equals(hashtag))
					s.setPostsPerDay(postAverage);

			}
		}
	}

	public static HashSet<Date> getDates(HashSet<Date> cmpDates, Map<String, Map<Integer, Data>> dataMap) {
		HashSet<Date> tempDates = new HashSet<Date>();

		for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
			Map<Integer, Data> tempMap = entries.getValue();

			for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				Time cmpTime = DateParsingService.cmpTimeIstance(entry);
				tempDates.add(cmpTime.getDate());
				Iterator<Date> it = tempDates.iterator();

				while (it.hasNext()) {
					if (!cmpDates.contains(it.next()))
						cmpDates.add(cmpTime.getDate());
				}
			}
		}
		return cmpDates;
	}

}
