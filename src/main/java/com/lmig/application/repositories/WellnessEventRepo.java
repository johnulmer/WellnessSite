package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lmig.application.entities.WellnessEvent;

@Repository
public interface WellnessEventRepo extends JpaRepository <WellnessEvent, Integer>{

	@Query("Select e from WellnessEvent e where UPPER(eventName) like UPPER(CONCAT('%',:eventName, '%'))")
	public List<WellnessEvent> findByEventLike(@Param("eventName") String eventName);


}
