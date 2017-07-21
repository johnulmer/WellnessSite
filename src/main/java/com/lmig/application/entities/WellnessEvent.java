package com.lmig.application.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private LocalDate startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private LocalDate endDate;
	

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

	public WellnessEvent(String eventName, LocalDate startDate, LocalDate endDate, String location, String description,
		 String eventType) {
		super();
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.description = description;
		this.eventType = eventType;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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


	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
//		if (other.startDate != null) {
//			this.startDate = other.startDate;
//		}
//		if (other.endDate != null) {
//			this.endDate = other.endDate;
//		}
//	}

}
