package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmig.application.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}
