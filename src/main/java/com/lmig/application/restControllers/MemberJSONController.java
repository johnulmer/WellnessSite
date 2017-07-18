package com.lmig.application.restControllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MemberRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Members", description="JSON operations pertaining to WellnessSite Members")
public class MemberJSONController {
	
@Autowired
private MemberRepository memberRepository;

/**
 * Accepts a JSON Member object with email, password, screenName set, 
 * returns JSON Member object with auto-generated ID.
 *
 * @param addingMember A Member object with values for email, password, screenName.
 * @return             returnedMember with ID set
 * @see                Member
 */
@ApiOperation(value = "Adds a new member")
@RequestMapping(path = "/api/member/", method = RequestMethod.POST)
public Member addMember(@RequestBody @Valid Member addingMember) {
	Member returnedMember = memberRepository.save(addingMember);
	return returnedMember;
	//return new Member("john", "john@blah.com", "pwd", true);
}

/**
 * Return a list of Members in the DB as JSON objects.
 *
 * @return      memberList - a list of all Members.
 * @see         Member
 */
@ApiOperation(value = "Returns a list of all Members")
@RequestMapping(path = "/api/getAllMembers", method = RequestMethod.GET)
public List<Member> getAllMembers() {
	List<Member> memberList = memberRepository.findAll();
	return memberList;
}
/**
 * Return the Member matching a specific ID as a JSON object.
 *
 * @return      Member - a Member matching a specific ID.
 * @see         Member
 */
@ApiOperation(value = "Returns Member matching ID")
@RequestMapping(path = "/api/member/{id}", method = RequestMethod.GET)
public Member getMemberByID(@PathVariable Integer id) {
	return memberRepository.getOne(id);
}
/**
 * Accepts a JSON Member object with screenName, email, and /or password set 
 * returns a JSON Member object showing the updated values.
 *
 * @param updatingMember A Member object with values for ID & optionally for screenName, email, and /or password.
 * @return               returnedMember showing updated values
 * @see                  Member
 */
@ApiOperation(value = "Updates a member's screenName, email, and /or password based on ID")
@RequestMapping(path = "/api/member/{id}", method = RequestMethod.PUT)
public Member updateMember(@PathVariable Integer id,
		@RequestBody Member updatingMember) {
	Member m = memberRepository.getOne(id);
	if (updatingMember.getEmail() != null) {
		m.setEmail(updatingMember.getEmail());
	}
	if (updatingMember.getPassword() != null) {
		m.setPassword(updatingMember.getPassword());
	}
	if (updatingMember.getScreenName() != null) {
		m.setScreenName(updatingMember.getScreenName());
	}
	if (updatingMember.isActive()) {
		m.setActive(true);
	} else if (!(updatingMember.isActive())) {
		m.setActive(false);
	}
	memberRepository.save(m);
	return m;
}
/**
 * Given a member ID (int), make that member Inactive.
 *
 * @param id An int that is a valid ID for a member.
 * @return   m (the member object that is being marked inactive) with screenName, email, password.
 * @see      Member
 */
@ApiOperation(value = "Inactivates a Member based on ID")
@RequestMapping(path = "/api/inactivateMember/{id}", method = RequestMethod.DELETE)
public Member inactivateMember(@PathVariable Integer id) {	
	Member m = memberRepository.getOne(id);
	m.setActive(false);
	memberRepository.save(m);
	return m;
}
/**
 * Given a member ID (int), make that member Active.
 *
 * @param id An int that is a valid ID for a member.
 * @return   m (the member object that is being marked active) with screenName, email, password.
 * @see      Member
 */
@ApiOperation(value = "Activates a Member based on ID")
@RequestMapping(path = "/api/activateMember/{id}", method = RequestMethod.PUT)
public Member activateMember(@PathVariable Integer id) {	
	Member m = memberRepository.getOne(id);
	m.setActive(true);
	memberRepository.save(m);
	return m;
}
@RequestMapping(path = "/api/resetMemberTable", method = RequestMethod.GET)
public void resetMemberTable() {	
	memberRepository.save(new Member("john1", "john1@blah.com", "pwd", true));
	memberRepository.save(new Member("john2", "john2@blah.com", "pwd", true));
	memberRepository.save(new Member("john3", "john3@blah.com", "pwd", true));
	memberRepository.save(new Member("john4", "john4@blah.com", "pwd", true));
	memberRepository.save(new Member("john5", "john5@blah.com", "pwd", true));
	memberRepository.save(new Member("john6", "john6@blah.com", "pwd", true));
}
}
