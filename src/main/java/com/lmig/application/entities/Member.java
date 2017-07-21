package com.lmig.application.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="member")
public class Member {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(required = true)
	int id;
	

	@NotNull
	//@Column(unique = true) uncomment for unique string name
	private String screenName;
	//@Column(unique = true) uncomment for unique email
	private String email;
	private String password;
	private boolean active;
	private boolean admin;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "members", cascade=CascadeType.ALL)
	@JsonBackReference
	private Set<WellnessEvent> wellnessEvents;

	
	public Set<WellnessEvent> getWellnessEvents() {
		return wellnessEvents;
	}

//	public void setWellnessEvents(Set<WellnessEvent> wellnessEvents) {
//		this.wellnessEvents = wellnessEvents;
//	}
	
	public Member() {
	}
	public Member(String screenName, String email, String password, boolean active, boolean admin) {
		this.screenName = screenName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.admin = admin;
	}
	
	
	public int getId() {
		return id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
