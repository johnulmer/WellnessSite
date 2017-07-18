package com.lmig.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.application.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

}
