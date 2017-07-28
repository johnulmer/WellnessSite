package com.lmig.application.unused;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;

//@Entity
//@Table(name = "team")
public class Team {
//	@Id
//	@GeneratedValue
//	private int id;
//	
//	public int getId() {
//		return id;
//	}
//	private String teamName;
//	private String description;
//	private boolean active;
//	private boolean publishable;
////    @OneToOne
////	private Medallion medallion;
//
//
//	@JoinTable(name = "wellnessevent_member", joinColumns = @JoinColumn(name = "wellnessevent_id") , 
//			inverseJoinColumns = @JoinColumn(name = "member_id") )
//    @ManyToMany(fetch = FetchType.EAGER)
//	private Set<Member> members;	
//	
//	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "members", cascade=CascadeType.ALL)
//	@JsonBackReference
//	private Set<WellnessEvent> wellnessEvents;
//
//	public Team() {
//	}
//	public Team(String teamName, String description) {
//		this.teamName = teamName;
//		this.description = description;
//	}
//	
//	public String getTeamName() {
//		return teamName;
//	}
//	public void setTeamName(String teamName) {
//		this.teamName = teamName;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public Set<WellnessEvent> getWellnessEvents() {
//		return wellnessEvents;
//	}
//	public Set<Member> getMembers() {
//		return members;
//	}
//	public void addMember(Member member) {
//		this.members.add(member);
//	}
//	public void removeMember(Member member) {
//		this.members.remove(member);
//	}
//	public boolean isActive() {
//		return active;
//	}
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//	public boolean isPublishable() {
//		return publishable;
//	}
//	public void setPublishable(boolean publishable) {
//		this.publishable = publishable;
//	}
////	public Medallion getMedallion() {
////		return medallion;
////	}
////	public void setMedallion(Medallion medallion) {
////		this.medallion = medallion;
////	}
}
