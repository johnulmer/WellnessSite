package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.HibernateHeartrateActivityRow;

@Repository
public interface HibernateHeartrateActivityRowRepository extends JpaRepository<HibernateHeartrateActivityRow, Integer> {

}
