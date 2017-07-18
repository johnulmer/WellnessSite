package com.example.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wellnessevent")
public class WellnessEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String eventName;
	private String startDate;
	private String endDate;
	private String location;
	private String description;
	private String type;

	public WellnessEvent() {

	}

	public WellnessEvent(String eventName, String startDate, String endDate, String location, String description,
			String type) {
		super();
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.description = description;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void merge(WellnessEvent other) {
		if (other.eventName != null) {
			this.eventName = other.eventName;
		}
		if (other.type != null) {
			this.type = other.type;
		}
		if (other.description != null) {
			this.description = other.description;
		}
		if (other.location != null) {
			this.location = other.location;
		}
		if (other.startDate != null) {
			this.startDate = other.startDate;
		}
		if (other.endDate != null) {
			this.endDate = other.endDate;
		}
	}

}
