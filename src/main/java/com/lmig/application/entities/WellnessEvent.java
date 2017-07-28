package com.lmig.application.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "wellnessevent")
public class WellnessEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	//@NotNull(message = "Event Name is required")  uncomment for not null constraint
	private String eventName;
	private String location;
	private String description;
//  @OneToOne
//	private Medallion medallion;
	private String eventType;
	@JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate startsOn;
	@JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate endsOn;
	//@NotNull(message = "Street Address is required.")
	//@Size(min = 5)
	private String streetAddress;
	//@NotNull(message = "City is required.")
	//@Size(min = 2)
	private String city;
	//@NotNull(message = "State is required.")
	//@Size(min = 2)
	private String state;
	//@NotNull(message = "ZipCode is required")
	private String zipCode;
	private Double latitude;
	private Double longitude;
	
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	

	@JoinTable(name = "wellnessevent_member", joinColumns = @JoinColumn(name = "wellnessevent_id") , 
			inverseJoinColumns = @JoinColumn(name = "member_id") )
    @ManyToMany(fetch = FetchType.EAGER)
	private Set<Member> members;	
	
	public Set<Member> getMembers() {
		return members;
	}
	public void addMember(Member member) {
		this.members.add(member);
	}
	public void removeMember(Member member) {
		this.members.remove(member);
	}
	
	public WellnessEvent() {

	}

	public WellnessEvent(String eventName, String location, String description,
		 String eventType, LocalDate startsOn, LocalDate endsOn, String streetAddress, String city, String state, String zipCode, Double latitude, Double longitude ) {
		super();
		this.eventName = eventName;
		this.location = location;
		this.description = description;
		this.eventType = eventType;
		this.startsOn = startsOn;
		this.endsOn = endsOn;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
		
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


	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public LocalDate getStartsOn() {
		return startsOn;
	}
	public void setStartsOn(LocalDate startsOn) {
		this.startsOn = startsOn;
	}
	public LocalDate getEndsOn() {
		return endsOn;
	}
	public void setEndsOn(LocalDate endsOn) {
		this.endsOn = endsOn;
	}

}