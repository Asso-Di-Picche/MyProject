package com.univpm.TweetAnalyzer.model.time;

import com.univpm.TweetAnalyzer.exception.IllegalTimeException;

/**
 * Classe i cui Oggetti contengono un dato Giorno, Mese ed Anno,
 * un'Ora e un Minuto.
 */

public class Time {
	
	private Date date;
	private Hours hours;
	
	public Time(Date date, Hours hours) {
		super();
		this.date = date;
		this.hours = hours;
	}
	
	/**
	 * Questo Metodo serve a confrontare un Oggetto di tipo Time con un altro di Riferimento.
	 * @param cmpTime è un Oggetto di tipo Time.
	 * @return true Solamente se cmpTime contiene un Tempo successivo a quello di Riferimento.
	 * @throws IllegalTimeException Se gli Oggetti confrontati NON hanno un senso dal punto di vista temporale.
	 */
	
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
	
	/**
	 * Questo Metodo serve a Verificare che un Oggetto di tipo Time ha senso.
	 * @param cmpTime È un Oggetto di tipo Time.
	 * @throws IllegalTimeException Viene lanciata se cmpTime NON ha senso.
	 */
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((hours == null) ? 0 : hours.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hours == null) {
			if (other.hours != null)
				return false;
		} else if (!hours.equals(other.hours))
			return false;
		return true;
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
