package com.lmig.application.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lmig.application.unused.Medallion;


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
	@ApiModelProperty(notes = "email used for login")
	private String email;
	private String password;
	private boolean active;
	private boolean admin;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String encPassword;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "members", cascade=CascadeType.ALL)
	@JsonBackReference
	private Set<WellnessEvent> wellnessEvents;

	public Set<WellnessEvent> getWellnessEvents() {
		return wellnessEvents;
	}
	
	public Member() {
	}
	
	public Member(String screenName, String email, String password, boolean active, boolean admin) {
		this.screenName = screenName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.admin = admin;
		this.encPassword = PasswordEncoderGenerator(password);
	}
	
	
	public String PasswordEncoderGenerator(String password) {

			final String SALT = "ugly";
			String hashedPassword = password + SALT;
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA");
			} catch (NoSuchAlgorithmException ex) {
				System.out.println(ex);
			}
			md.update(password.getBytes());
			String digest = new String(md.digest());
			hashedPassword=digest;
		
		return (hashedPassword);
}
	
	
	
	public Member(String screenName, String email, String password, boolean active, boolean admin, Medallion medallion) {
		this.screenName = screenName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.admin = admin;
	}
	
	public Member(String email, String password) {
		this.email = email;
		this.encPassword = PasswordEncoderGenerator(password);
	}

	public String getEncPassword() {
		return encPassword;
	}
	
	public void setEncPassword(String encPassword) {
		this.encPassword = PasswordEncoderGenerator(password);
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
