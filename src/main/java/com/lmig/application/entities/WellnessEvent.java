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

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "wellnessevent")
public class WellnessEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	//@NotNull  uncomment for not null constraint
	private String eventName;
	private String location;
	private String description;
//    @OneToOne
//	private Medallion medallion;
	private String eventType;
	@JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate startsOn;
	@JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate endsOn;
	

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
		 String eventType, LocalDate startsOn, LocalDate endsOn) {
		super();
		this.eventName = eventName;
		this.location = location;
		this.description = description;
		this.eventType = eventType;
		this.startsOn = startsOn;
		this.endsOn = endsOn;
		
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

//	public Medallion getMedallion() {
//		return medallion;
//	}
//	public void setMedallion(Medallion medallion) {
//		this.medallion = medallion;
//	}
	

//	public void merge(WellnessEvent other) {
//		if (other.eventName != null) {
//			this.eventName = other.eventName;
//		}
//		if (other.eventType != null) {
//			this.eventType = other.eventType;
//		}
//		if (other.description != null) {
//			this.description = other.description;
//		}
//		if (other.location != null) {
//			this.location = other.location;
//		}
//		if (other.startsOn != null) {
//			this.startsOn = other.startsOn;
//		}
//		if (other.endsOn != null) {
//			this.endsOn = other.endsOn;
//		}
//	}

}