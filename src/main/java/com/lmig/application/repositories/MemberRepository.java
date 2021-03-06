package com.lmig.application.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select m from Member m where "
			+ "(:email is null OR UPPER(m.email) LIKE UPPER(CONCAT('%',:email,'%'))) AND "
			+ "(:screenName is null OR UPPER(m.screenName) LIKE UPPER(CONCAT('%',:screenName,'%')))")
	public List<Member> findMemberBySearch(@Param("screenName") String screenName, @Param("email") String email);
	
//	@Query("select we from wellnessevent_member we_m where :memberID = we_m.member_id")
//	public ArrayList<WellnessEvent> findRegisteredEvents(@Param("memberID") Integer memberID);

	// @Query("select m from Member m where "
	// + "(:email = :unAuthMember.getEmail()) AND "
	// + "(:password = :unAuthMember.getPassword())")
	// public Member (@RequestBody Member unAuthMember);
	// //public Member authenticateMember(@Param("email") String screenName,
	// @Param("screenName") String email);

	@Query("select m from Member m where "
			+ "(UPPER(m.email) = UPPER(:email)) AND "
			+ "(UPPER(m.password) = UPPER(:password))")
	public Member findByEmailAndPassword(@Param("email")String email, @Param("password") String password);
	
	@Query("SELECT m from Member m WHERE UPPER(email) = UPPER(:email)")
	public Member findMemberByUserName(@Param("email") String email);
	
	@Query("SELECT m from Member m WHERE UPPER(email) = UPPER(:email) and encPassword = :password")
	public Member checkCredentials(@Param("email") String email, @Param("password") String password);

}
