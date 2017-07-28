package com.lmig.application.activityHibernateEntities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="stepsOverTimeActivityRow")
public class StepsOverTimeActivityRow {

	@Id
	@GeneratedValue
	@ApiModelProperty(required = true)
	int id;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	
}
