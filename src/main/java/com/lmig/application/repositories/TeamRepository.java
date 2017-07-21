package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmig.application.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
