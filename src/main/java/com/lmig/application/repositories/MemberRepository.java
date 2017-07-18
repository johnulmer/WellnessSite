package com.lmig.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.lmig.application.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select m from Member m where "
			+ "(:email is null OR UPPER(m.email) LIKE UPPER(CONCAT('%',:email,'%'))) AND "
			+ "(:screenName is null OR UPPER(m.screenName) LIKE UPPER(CONCAT('%',:screenName,'%')))")
	public List<Member> findMemberBySearch(@Param("screenName") String screenName, @Param("email") String email);

	// @Query("select m from Member m where "
	// + "(:email = :unAuthMember.getEmail()) AND "
	// + "(:password = :unAuthMember.getPassword())")
	// public Member authenticateMember(@RequestBody Member unAuthMember);
	// //public Member authenticateMember(@Param("email") String screenName,
	// @Param("screenName") String email);
}
