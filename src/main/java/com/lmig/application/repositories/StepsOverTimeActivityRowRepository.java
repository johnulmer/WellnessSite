package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.StepsOverTimeActivityRow;

@Repository
public interface StepsOverTimeActivityRowRepository extends JpaRepository<StepsOverTimeActivityRow, Integer> {
	
	@Query("SELECT sotar from StepsOverTimeActivityRow sotar WHERE WellnessEventID = :wellnessEventID")
	public List<StepsOverTimeActivityRow> findByEvent(@Param("wellnessEventID") int wellnessEventID);

	@Query("SELECT sotar from StepsOverTimeActivityRow sotar WHERE memberid = :memberID")
	public List<StepsOverTimeActivityRow> findByMember(@Param("memberID") int memberID);
	
}
