package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.HeartrateActivityRow;

@Repository
public interface HeartrateActivityRowRepository extends JpaRepository<HeartrateActivityRow, Integer> {
	
	@Query("SELECT har from HeartrateActivityRow har WHERE eventid = :wellnessEventID")
	public List<HeartrateActivityRow> findByEvent(@Param("wellnessEventID") int wellnessEventID);

	@Query("SELECT har from HeartrateActivityRow har WHERE MemberID = :memberID")
	public List<HeartrateActivityRow> findByMember(@Param("memberID") int memberID);

}