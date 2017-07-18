package com.lmig.application.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private String screenName;
	private String email;
	private String password;
	private boolean active;
	private boolean admin;

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
