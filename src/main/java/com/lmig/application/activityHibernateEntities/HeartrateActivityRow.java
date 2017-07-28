package com.lmig.application.activityHibernateEntities;

import java.text.SimpleDateFormat;
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
@Table(name="heartrateActivityRow")
public class HeartrateActivityRow {

	@Id
	@GeneratedValue
	@ApiModelProperty(required = true)
	int id;

	private int eventID;
	private int memberID;
	private Date statTimestamp;
	private int stat;
	private String formattedTime;

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
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
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
	public String getFormattedTime() {
		return formattedTime;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
