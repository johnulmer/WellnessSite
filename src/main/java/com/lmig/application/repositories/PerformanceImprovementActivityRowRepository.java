package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.PerformanceImprovementActivityRow;

@Repository
public interface PerformanceImprovementActivityRowRepository extends JpaRepository<PerformanceImprovementActivityRow, Integer> {
	
	@Query("SELECT piar from PerformanceImprovementActivityRow piar WHERE WellnessEventID = :wellnessEventID")
	public List<PerformanceImprovementActivityRow> findByEvent(@Param("wellnessEventID") int wellnessEventID);

	@Query("SELECT piar from PerformanceImprovementActivityRow piar WHERE MemberID = :memberID")
	public List<PerformanceImprovementActivityRow> findByMember(@Param("memberID") int memberID);
	
}
