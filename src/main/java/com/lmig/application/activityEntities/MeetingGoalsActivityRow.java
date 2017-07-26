package com.lmig.application.activityEntities;

import java.util.Date;

public class MeetingGoalsActivityRow {
	
	private int eventID;
	private int memberID;
	private Date goalDate;
	private double goal;
	private double actual;
	
	public MeetingGoalsActivityRow() {
		
	}
	public MeetingGoalsActivityRow(int eventID, 
			int memberID, 
			Date goalDate, 
			double goal, 
			double actual) {
		this.eventID = eventID;
		this.memberID = memberID;
		this.goalDate = goalDate;
		this.goal = goal;
		this.actual = actual;
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
	public Date getGoalDate() {
		return goalDate;
	}
	public void setGoalDate(Date goalDate) {
		this.goalDate = goalDate;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}

	
}