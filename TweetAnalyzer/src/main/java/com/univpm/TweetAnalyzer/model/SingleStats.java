package com.univpm.TweetAnalyzer.model;

/**
 * Classe le cui Istanze servono a contenere le Statistiche,
 * svolte sui Tweet in base ad un Hashtag corrispondente.
 */

public class SingleStats {

    private String Hashtag;
    private long Likes, Retweets;
    private String PercLikes, PercRetweets;
    private double PostsPerDay;

	public SingleStats() {
	}
    
    public SingleStats(String Hashtag, String PercLikes, String PercRetweets,
    		long Likes, long Retweets, double PostsPerDay) {
    	
    	this.Hashtag = Hashtag;
    	this.Likes = Likes;
    	this.PercLikes = PercLikes;
    	this.Retweets = Retweets;
    	this.PercRetweets = PercRetweets;
    	this.PostsPerDay = PostsPerDay;
        
    }
    
	public String getHashtag() {
		return Hashtag;
	}

	public void setHashtag(String hashtag) {
		Hashtag = hashtag;
	}

	public double getPostsPerDay() {
		return PostsPerDay;
	}

	public void setPostsPerDay(double postsPerDay) {
		PostsPerDay = postsPerDay;
	}

	public long getLikes() {
		return Likes;
	}

	public void setLikes(long likes) {
		Likes = likes;
	}

	public long getRetweets() {
		return Retweets;
	}

	public void setRetweets(long retweets) {
		Retweets = retweets;
	}

	public String getPercLikes() {
		return PercLikes;
	}

	public void setPercLikes(String percLikes) {
		PercLikes = percLikes;
	}

	public String getPercRetweets() {
		return PercRetweets;
	}

	public void setPercRetweets(String percRetweets) {
		PercRetweets = percRetweets;
	}
    
}
