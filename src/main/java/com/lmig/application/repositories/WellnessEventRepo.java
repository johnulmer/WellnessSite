package com.lmig.application.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lmig.application.entities.WellnessEvent;


@Repository
public interface WellnessEventRepo extends JpaRepository <WellnessEvent, Integer>{

	@Query("Select e from WellnessEvent e where (UPPER(e.eventName) = UPPER(:eventName))")
	public WellnessEvent findByEventName(@Param("eventName") String eventName);
	
//	@Query("Select e from WellnessEvent e WHERE  e.startsOn = :startsOn")
//	public List<WellnessEvent> searchByStartDate(@Param("startsOn") LocalDate startDt);
	

	//@Query("Select e from WellnessEvent e WHERE  e.startsOn = :startsOn")
	
	@Query("SELECT e FROM WellnessEvent e WHERE e.startsOn BETWEEN :startsOn AND :endsOn")
	public List<WellnessEvent> searchByStartDate(@Param("startsOn") LocalDate startsOn, @Param("endsOn") LocalDate endsOn);




}
