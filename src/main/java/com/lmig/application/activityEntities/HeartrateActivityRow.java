package com.lmig.application.activityEntities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeartrateActivityRow {

	private int eventID;
	private int memberID;
	private Date statTimestamp;
	private int stat;
	private String formattedTime;
	
	public String getFormattedTime() {
		return formattedTime;
	}
	public HeartrateActivityRow() {
		
	}
	public HeartrateActivityRow(int eventID, 
			int memberID, 
			Date statTimestamp, 
			int stat) {
		this.eventID = eventID;
		this.memberID = memberID;
		this.statTimestamp = statTimestamp;
		this.stat = stat;
		SimpleDateFormat dt = new SimpleDateFormat("mm-dd-yyyyy hh:mm:ss");
		this.formattedTime = dt.format(this.statTimestamp);
	}
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public Date getStatTimestamp() {
		return statTimestamp;
	}
	public void setStatTimestamp(Date statTimestamp) {
		this.statTimestamp = statTimestamp;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
}
