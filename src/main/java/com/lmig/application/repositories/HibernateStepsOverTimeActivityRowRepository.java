package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lmig.application.activityHibernateEntities.HibernateHeartrateActivityRow;
import com.lmig.application.activityHibernateEntities.HibernateStepsOverTimeActivityRow;

@Repository
public interface HibernateStepsOverTimeActivityRowRepository extends JpaRepository<HibernateStepsOverTimeActivityRow, Integer> {

}
