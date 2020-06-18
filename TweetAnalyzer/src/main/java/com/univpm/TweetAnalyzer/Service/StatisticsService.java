package com.univpm.TweetAnalyzer.Service;

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

/**
 * Questa Classe si occupa di Calcolare Statistiche su una Map di Dati.
 */

public class StatisticsService {
	
	//Questa Variabile viene utilizzata per contenere tutte le Statistiche relative ai Singoli Hashtag.
	private static ArrayList<SingleStats> statsList;
	
	//Questa Variabile viene utilizzata per contenere tutte le Statistiche sui Dati di una Map.
	private static TotalStats totalStats;

	/**
	 * Questo Metodo serve ad Arrotondare Numeri Decimali ad una determinata Cifra dopo la Virgola.
	 * @param number È il Numero da Arrotondare.
	 * @param decimals È la Cifra Decimale alla quale Arrotondare il Numero.
	 * @return Il Numero Arrotondato.
	 */
	
	public static double roundDecimals(double number, int decimals) {
		double factor = Math.pow(10, decimals);
		return Math.floor(number * factor + 0.5) / factor;
	}

	/**
	 * Questo Metodo serve per Applicare su una Map una serie di Calcoli dai quali Ottenere le Statistiche.
	 * @param dataMap È la Map sulla quale Calcolare le Statistiche.
	 * @return Un Oggetto del Tipo TotalStats.
	 * @throws StatisticsNotAppliedException Viene lanciata se NON è stato possibile Applicare i Calcoli.
	 */
	
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
	
	/**
	 * Questo Metodo Calcola Statistiche sui Likes e sui Retweets, in Valore Numerico,
	 * relativi ad un determinato Hashtag.
	 * @param statsList Viene utilizzata per contenere tutte le Statistiche relative ai Singoli Hashtag.
	 * @param dataMap È la Map sulla quale Calcolare le Statistiche.
	 */
	
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

	/**
	 * Questo Metodo serve per Calcolare le Percentuali di Likes e Retweets dei rispettivi Hashtag
	 * in relazione ai Likes ed ai Retweets Totali.
	 * @param statsList Viene utilizzata per contenere tutte le Statistiche relative ai Singoli Hashtag.
	 */
	
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

	/**
	 * Questo Metodo Calcola la Media dei Posts al Giorno su ciascun Hashtag.
	 * @param statsList Viene utilizzata per contenere tutte le Statistiche relative ai Singoli Hashtag.
	 * @param dataMap È la Map sulla quale Calcolare le Statistiche.
	 */
	
	public static void dateStats(ArrayList<SingleStats> statsList, Map<String, Map<Integer, Data>> dataMap) {
		
		//Questa Variabile serve per Contenere tutti i Giorni in cui sono stati effettuati Posts
		//contenenti uno degli Hashtag Analizzati.
		HashSet<Date> cmpDates = new HashSet<Date>();
		
		//Questa Variabile serve per Contenere il Numero di Posts relativi a ciascun Hashtag.
		ArrayList<Integer> counter = new ArrayList<Integer>();
		
		//Questa Variabile serve per Calcolare la Media di Posts al Giorno per ciascun Hashtag.
		double postAverage = 0;

		cmpDates = getDates(dataMap);

		for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
			String hashtag = entries.getKey();
			Map<Integer, Data> tempMap = entries.getValue();
			
			Iterator<Date> it = cmpDates.iterator();

			while (it.hasNext()) {
				int index = 0;
				Date cmpDate = it.next();

				for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
					
					//Attraverso actTime e actDate si Ottiene una Data, a partire dal
					//corrispondente Campo Date dei Posts in dataMap.
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

	/**
	 * Questo Metodo serve per Ottenere Tutte le differenti Date (Giorno/Mese/Anno)
	 * presenti in dataMap.
	 * @param dataMap È la Map sulla quale Calcolare le Statistiche.
	 * @return Un HashSet delle Date presenti in dataMap. 
	 */
	
	public static HashSet<Date> getDates(Map<String, Map<Integer, Data>> dataMap) {
		
		//Questa Variabile è un HashSet di Appoggio per Evitare Date Duplicate.
		HashSet<Date> tempDates = new HashSet<Date>();
		
		HashSet<Date> cmpDates = new HashSet<Date>();
		
		for (Map.Entry<String, Map<Integer, Data>> entries : dataMap.entrySet()) {
			Map<Integer, Data> tempMap = entries.getValue();

			for (Map.Entry<Integer, Data> entry : tempMap.entrySet()) {
				Time cmpTime = DateParsingService.cmpTimeIstance(entry);
				tempDates.add(cmpTime.getDate());
				Iterator<Date> it = tempDates.iterator();

				while (it.hasNext()) {
					
					//Viene fatto questo Controllo per Evitare di avere Date Duplicate
					//in cmpDates.
					if (!cmpDates.contains(it.next()))
						cmpDates.add(cmpTime.getDate());
				}
			}
		}
		return cmpDates;
	}

}
