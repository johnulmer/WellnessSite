package com.lmig.application.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="medallion")
public class Medallion {

	@Id
	@GeneratedValue
	@ApiModelProperty(required = true)
	int id;
	private String title;
	private String description;
	private boolean active;
	private WellnessEvent wellnessEvent;

	public Medallion(String title, String description) {
		this.title = title;
		this.description = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public WellnessEvent getWellnessEvent() {
		return wellnessEvent;
	}
	public void setWellnessEvent(WellnessEvent wellnessEvent) {
		this.wellnessEvent = wellnessEvent;
	}
	public int getId() {
		return id;
	}
}
