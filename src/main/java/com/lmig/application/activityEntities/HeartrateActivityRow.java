package com.lmig.application.activityEntities;

import java.util.Date;

public class HeartrateActivityRow {

	private int eventID;
	private int memberID;
	private Date statTimestamp;
	private int stat;
	
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
