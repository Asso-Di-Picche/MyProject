package com.univpm.TweetAnalyzer.bin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.univpm.TweetAnalyzer.model.Data;
import com.univpm.TweetAnalyzer.model.Metadata;

/**
 * Questa Classe inizializza un Database mediante gli Hashtag 
 * presenti nella cartella hashtags.txt, modificabile dall'Utente.
 */

public class DatabaseClass {
	
	private static final String hashtagDirectory = "Files/hashtags.txt";
	private static ArrayList<Metadata> metadataList = new ArrayList<Metadata>();
	private static Map<String, Map<Integer, Data>> dataMap = new HashMap<String, Map<Integer, Data>>();
	private static final ArrayList<String> urls = new ArrayList<>();
	
	public DatabaseClass() throws IOException, InterruptedException {
		metadataFill();
		urlsObtain();
		tweetObtain();
	}
	
	/**
	 * @return I Metadati inizializzati nel Costruttore della Classe.
	 */
	
	public static ArrayList<Metadata> getMetadataList() {
		return metadataList;
	}
	
	/**
	 * @return I Dati scaricati dall'API di Twitter.
	 */
	
	public static Map<String, Map<Integer, Data>> getDataMap(){
		return dataMap;
	}


	/**
	 * Gets hashtag directory.
	 *
	 * @return the hashtag directory
	 */
	public static String getHashtagDirectory() {
		return hashtagDirectory;
	}


	/**
	 * Qui vengono inizializzati Tutti i Metadati di Twitter che verranno
	 * presi in esame dall'Applicazione.
	 */

	public void metadataFill() {
		metadataList.add(new Metadata("Hashtag", "String"));
		metadataList.add(new Metadata("Date", "String"));
		metadataList.add(new Metadata("postID", "Long"));
		metadataList.add(new Metadata("Username", "String"));
		metadataList.add(new Metadata("Language", "String"));
		metadataList.add(new Metadata("Followers", "Long"));
		metadataList.add(new Metadata("Retweets", "Long"));
		metadataList.add(new Metadata("Likes", "Long"));
	}
	
	/**
	 * È il metodo che compone l'URL da cui scaricare i Dati mediante
	 * gli Hashtag contenuti nella corrispondente Directory.
	 */
	
	public void urlsObtain() {
		String hashtag;
		String leftUrl = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=%23";
		String rightUrl = "&count=100";
		Scanner in = null;
		
		try {
			in = new Scanner(new BufferedReader(new FileReader(hashtagDirectory)));
			
				while(in.hasNext()) {
					hashtag = in.nextLine();
					if(hashtag.contains(" "))
						throw new Exception();
					urls.add(leftUrl + hashtag + rightUrl);
				}
		}
		catch (FileNotFoundException ex) {
			System.out.println(ex.getClass().getSimpleName() + 
					": Directory " + hashtagDirectory + " Not Found!");
		} catch (Exception ex) {
			System.out.println("WARNING!\nhashtag.txt Folder Can Not Contain Spaces!");
		}
		finally {
			if(in != null)
				in.close();
		}
	}
	
	/**
	 * Qui vengono letti e copiati brutalmente su Stringa i Tweet presenti
	 * in ciascun URL.
	 */
	
	public void tweetObtain() throws IOException, InterruptedException {
		String info = "";
		URLConnection openConnection;
		
		System.out.println("Wait for Downloading...");
		try {
			int counter = (int) (100/(urls.size()));
		for(String url : urls) {
			try {
				openConnection = new URL(url).openConnection();
				openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				InputStream in = openConnection.getInputStream();
		
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
				
				info = buf.readLine();
				in.close();
				System.out.println("Downloading..." + counter + "%");
				counter += 100/urls.size();
				String hashtag = hashtagObtain(url);
				tweetDownload(info, hashtag);
				
			}
			catch (IOException ex) {
				System.out.println(ex.getClass().getSimpleName() + " URL " + url + " Does Not Exist!");
			}
		}
		}
		catch(Exception ex) {
			System.out.println(ex.getClass().getSimpleName() + ": hashtags.txt File is Compromised!");
		}
		System.out.println("Download was Successful!");
	}
	
	/**
	 * Questo metodo serve ad Associare ciascun Hashtag ai corrispondenti Tweet Scaricati. 
	 */
	
	public static String hashtagObtain(String url) throws InterruptedException{
		String hashtag = "";
		Scanner in = null;
		
		try {
			in = new Scanner(new BufferedReader(new FileReader(hashtagDirectory)));
			while(in.hasNext()) {
				hashtag = in.nextLine();
				if(url.contains(hashtag))
					return hashtag;
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println(ex.getClass().getSimpleName() + 
					": Directory " + hashtagDirectory + " Not Found!");
		}
		finally {
			if(in!=null)
				in.close();
		}
		return null;
	}
	
	/**
	 * Questo è il metodo che Memorizza su HashMap i Dati precedentemente
	 * ottenuti mediante URL.
	 * @param info Contiene i Tweet associati ad hashtag
	 * @param hashtag Contiene l'hashtag dei corrispondenti Tweet
	 */
	
	public void tweetDownload(String info, String hashtag) throws InterruptedException {
		Map<Integer, Data> dataHashtag = new HashMap<Integer, Data>();
		JSONObject json;
			try {
				json = (JSONObject) JSONValue.parseWithException(info);
				JSONArray statusesArray = (JSONArray) json.get("statuses");
				
				for(int i=0; i<statusesArray.size(); i++) {
					
					JSONObject statusesPos = (JSONObject) statusesArray.get(i);
					JSONObject metadataPos = (JSONObject) statusesPos.get("metadata");
					JSONObject userPos = (JSONObject) statusesPos.get("user");
					
					dataHashtag.put(i+1, new Data( (String) metadataPos.get("iso_language_code"), (String) userPos.get("name"),
							(String) statusesPos.get("created_at"), (long) statusesPos.get("id"), 
							(long) userPos.get("followers_count"), (long) statusesPos.get("retweet_count"),
							(long) userPos.get("favourites_count")));
					dataMap.put(hashtag, dataHashtag);
					
					}
			}
			catch (ParseException ex) {
				System.out.println(ex.getClass().getSimpleName() + ": Can Not Get Data!");
			}

	}
}
