package com.lmig.application.activityEntities;

public class PerformanceImprovementActivityRow {

	private int eventID;
	private int memberID;
	private double startStat;
	private double endStat;
	
	public PerformanceImprovementActivityRow() {
		
	}
	public PerformanceImprovementActivityRow(int eventID, int memberID, double startStat, double endStat) {
		this.eventID = eventID;
		this.memberID = memberID;
		this.startStat = startStat;
		this.endStat = endStat;
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
	public double getStartStat() {
		return startStat;
	}
	public void setStartStat(double startStat) {
		this.startStat = startStat;
	}
	public double getEndStat() {
		return endStat;
	}
	public void setEndStat(double endStat) {
		this.endStat = endStat;
	}	
}