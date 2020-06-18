package com.univpm.TweetAnalyzer.model;

import java.util.Objects;

/**
 * Classe le cui Istanze servono a contenere le Statistiche,
 * svolte sui Tweet in base ad un Hashtag corrispondente.
 */

public class SingleStats {

    private String Hashtag, PercLikes, PercRetweets;
    private long Likes, Retweets;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SingleStats)) return false;
		SingleStats that = (SingleStats) o;
		return Likes == that.Likes &&
				Retweets == that.Retweets &&
				Double.compare(that.PostsPerDay, PostsPerDay) == 0 &&
				Hashtag.equals(that.Hashtag) &&
				PercLikes.equals(that.PercLikes) &&
				PercRetweets.equals(that.PercRetweets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Hashtag, PercLikes, PercRetweets, Likes, Retweets, PostsPerDay);
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
