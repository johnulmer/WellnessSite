package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmig.application.entities.Medallion;


public interface MedallionRepository extends JpaRepository<Medallion, Integer> {

}
