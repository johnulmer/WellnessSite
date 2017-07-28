package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.MeetingGoalsActivityRow;

@Repository
public interface MeetingGoalsActivityRowRepository extends JpaRepository<MeetingGoalsActivityRow, Integer> {
	
	@Query("SELECT mgar from MeetingGoalsActivityRow mgar WHERE WellnessEventID = :wellnessEventID")
	public List<MeetingGoalsActivityRow> findByEvent(@Param("wellnessEventID") int wellnessEventID);

}
