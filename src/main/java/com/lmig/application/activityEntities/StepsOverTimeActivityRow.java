package com.lmig.application.activityEntities;

import java.util.Date;

public class StepsOverTimeActivityRow {

	private int wellnessEventID;
	private int memberID;
	private Date dayOfSteps;
	private int stepCount;
	
	public StepsOverTimeActivityRow() {
		
	}
	public StepsOverTimeActivityRow(int wellnessEventID, 
			int memberID, 
			Date dayOfSteps, 
			int stepCount) {
		this.wellnessEventID = wellnessEventID;
		this.memberID = memberID;
		this.dayOfSteps = dayOfSteps;
		this.stepCount = stepCount;
	}

	public int getWellnessEventID() {
		return wellnessEventID;
	}

	public void setWellnessEventID(int wellnessEventID) {
		this.wellnessEventID = wellnessEventID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public Date getDayOfSteps() {
		return dayOfSteps;
	}

	public void setDayOfSteps(Date dayOfSteps) {
		this.dayOfSteps = dayOfSteps;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	
}
