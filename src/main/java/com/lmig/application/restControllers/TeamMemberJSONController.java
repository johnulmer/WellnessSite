package com.lmig.application.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lmig.application.entities.Member;
import com.lmig.application.entities.Team;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.repositories.TeamRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Member Team Registrations", description = "JSON operations pertaining to WellnessSite Members signing up for or leaving a Team")
public class TeamMemberJSONController {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MemberRepository memberRepository;

	/**
	 * Given a Member and a Team, add the member to the team.
	 *
	 * @param member
	 *            A Member object (must have int ID set at minimum) who will be
	 *            added to a Team.
	 * @param team
	 *            A Team object (must have int ID set at a minimum) to which a
	 *            Member will be added.
	 * @see Member
	 * @see Team
	 */
	@ApiOperation(value = "Adds a Member to a Team")
	@RequestMapping(path = "/api/addMemberToTeam", method = RequestMethod.PUT)
	public void addMemberToTeam(@RequestBody Member member, @RequestBody Team team) {
		Team teamToJoin = teamRepository.getOne(team.getId());
		Member joiningMember = memberRepository.findOne(member.getId());
		teamToJoin.addMember(joiningMember);
		// joiningMember.addMedallion(teamToJoin.getMedallion());
		teamRepository.save(teamToJoin);
		memberRepository.save(joiningMember);
	}

	/**
	 * Given a Member and a Team, remove the member from the team.
	 *
	 * @param member
	 *            A Member object (must have int ID set at minimum) who will be
	 *            added to a Team.
	 * @param team
	 *            A Team object (must have int ID set at a minimum) to which a
	 *            Member will be added.
	 * @see Member
	 * @see Team
	 */
	@ApiOperation(value = "Remove a Member from a Team")
	@RequestMapping(path = "/api/removeMemberFromTeam", method = RequestMethod.PUT)
	public void removeMemberFromTeam(@RequestBody Member member, @RequestBody Team team) {
		Team teamToLeave = teamRepository.getOne(team.getId());
		Member leavingMember = memberRepository.findOne(member.getId());
		teamToLeave.removeMember(leavingMember);
		// leavingMember.removeMedallion(teamToLeave.getMedallion());
		teamRepository.save(teamToLeave);
		memberRepository.save(leavingMember);
	}

	// /**
	// * Given a Member, return the Teams that member has joined.
	// *
	// * @param memberID A valid int Member ID
	// * @return teamList - a list of Teams a Member has joined.
	// * @see Member
	// * @see Team
	// */
	// @ApiOperation(value = "Show the Teams the logged in Member has joined")
	// @RequestMapping(path = "/api/joinedTeams/{memberID}", method =
	// RequestMethod.GET)
	// public Set<WellnessEvent> registeredEvents(@PathVariable Integer memberID) {
	// Member m = memberRepository.findOne(memberID);
	// Set<WellnessEvent> eventList = m.get;
	// return eventList;
	// }

}
