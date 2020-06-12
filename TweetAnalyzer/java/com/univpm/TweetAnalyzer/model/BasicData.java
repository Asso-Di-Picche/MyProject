package com.univpm.TweetAnalyzer.model;

public class BasicData {
	
	private long Retweets, Likes;
	
	public BasicData(long Retweets, long Likes) {
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
	
}
