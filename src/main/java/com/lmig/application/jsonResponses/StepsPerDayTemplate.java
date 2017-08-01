package com.lmig.application.jsonResponses;

import java.util.Date;

public class StepsPerDayTemplate {

	private Date dateTime;
	private int value;

	public StepsPerDayTemplate() {
		
	}
	
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
