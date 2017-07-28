package com.lmig.application.activityHibernateEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="performanceImprovementActivityRow")
public class PerformanceImprovementActivityRow {

	@Id
	@GeneratedValue
	@ApiModelProperty(required = true)
	int id;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}