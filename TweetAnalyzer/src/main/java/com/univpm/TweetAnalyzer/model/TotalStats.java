package com.univpm.TweetAnalyzer.model;

import java.util.List;

/**
 * Classe che istanzia Oggetti contenenti Statistiche, svolte su
 * tutti i Tweet scaricati.
 */

public class TotalStats {

    private long totRetweets, totLikes;
    public List<SingleStats> Hashtags;

    public TotalStats() {
	}

    public TotalStats(long totRetweets, long totLikes, List<SingleStats> Hashtags) {
        this.totRetweets = totRetweets;
        this.totLikes = totLikes;
        this.Hashtags = Hashtags;
    }

	public void setHashtags(List<SingleStats> Hashtags) {
		this.Hashtags = Hashtags;
	}

	public long getTotRetweets() {
        return totRetweets;
    }

    public void setTotRetweets(long totRetweets) {
        this.totRetweets = totRetweets;
    }

    public long getTotLikes() {
        return totLikes;
    }

    public void setTotLikes(long totLikes) {
        this.totLikes = totLikes;
    }
}
