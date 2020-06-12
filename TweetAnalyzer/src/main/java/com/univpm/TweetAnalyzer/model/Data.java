package com.univpm.TweetAnalyzer.model;

public class Data{
	
	private String Language, Username, Date;
	private long postID, Followers, Retweets, Likes;
	
	public Data(String Language, String Username, String Date, 
			long postID, long Followers, long Retweets, long Likes) {
		
		this.Date = Date;
		this.postID = postID;
		this.Username = Username;
		this.Language = Language;
		this.Followers = Followers;
		this.Retweets = Retweets;
		this.Likes = Likes;
		
	}

	public long getRetweets() {
		return Retweets;
	}

	public void setRetweets(long retweets) {
		Retweets = retweets;
	}

	public long getLikes() {
		return Likes;
	}

	public void setLikes(long likes) {
		Likes = likes;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public long getPostID() {
		return postID;
	}

	public void setPostID(long postID) {
		this.postID = postID;
	}

	public long getFollowers() {
		return Followers;
	}

	public void setFollowers(long followers) {
		Followers = followers;
	}
	
}
