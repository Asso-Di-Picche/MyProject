package com.univpm.TweetAnalyzer.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotalStats)) return false;
        TotalStats that = (TotalStats) o;
        return totRetweets == that.totRetweets &&
                totLikes == that.totLikes &&
                Hashtags.equals(that.Hashtags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totRetweets, totLikes, Hashtags);
    }
}
