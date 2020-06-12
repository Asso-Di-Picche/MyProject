package com.univpm.TweetAnalyzer.model.time;

import com.univpm.TweetAnalyzer.exception.IllegalTimeException;

public class Time {
	
	private Date date;
	private Hours hours;
	
	public Time(Date date, Hours hours) {
		super();
		this.date = date;
		this.hours = hours;
	}
	
	public boolean timeCompare(Time cmpTime)
			throws IllegalTimeException {
		timeIsComparable(cmpTime);
		if(cmpTime.date.getYear() > this.date.getYear())
			return true;
		else if(cmpTime.date.getYear() == this.date.getYear())
			if(cmpTime.date.getMonth() > this.date.getMonth())
				return true;
			else if(cmpTime.date.getMonth() == this.date.getMonth())
				if(cmpTime.date.getDay() > this.date.getDay())
					return true;
				else if(cmpTime.date.getDay() == this.date.getDay())
					if(cmpTime.hours.getHour() > this.hours.getHour())
						return true;
					else if(cmpTime.hours.getHour() == this.hours.getHour())
						if(cmpTime.hours.getMinute() >= this.hours.getMinute())
							return true;
		return false;
	}
	
	public void timeIsComparable(Time cmpTime)
			throws IllegalTimeException {
		if(this.date.getMonth() > 12 || cmpTime.date.getMonth() > 12 ||
				this.date.getDay() > 31 || cmpTime.date.getDay() > 31 ||
				this.hours.getHour() > 23 || cmpTime.hours.getHour() > 23 ||
				this.hours.getMinute() > 59 || cmpTime.hours.getMinute() >59 ||
				this.date.getYear() < 2020 || cmpTime.date.getYear() < 2020 ||
				this.date.getMonth() < 0 || cmpTime.date.getMonth() < 0 ||
				this.date.getDay() < 0 || cmpTime.date.getDay() < 0 ||
				this.hours.getHour() < 0 || cmpTime.hours.getHour() < 0 ||
				this.hours.getMinute() < 0 || cmpTime.hours.getMinute() < 0)
			throw new IllegalTimeException("Date Is Not Allowed");
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Hours getHours() {
		return hours;
	}

	public void setHours(Hours hours) {
		this.hours = hours;
	}
	
	
}
