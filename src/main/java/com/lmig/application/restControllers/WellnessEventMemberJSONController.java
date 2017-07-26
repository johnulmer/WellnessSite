package com.lmig.application.restControllers;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;
import com.lmig.application.jsonResponses.MemberRegistrationUpdate;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.repositories.WellnessEventRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Member Event Registrations", description = "JSON operations pertaining to WellnessSite Members registering or canceling for an Event")
public class WellnessEventMemberJSONController {

	@Autowired
	private WellnessEventRepo wellnessEventRepository;
	@Autowired
	private MemberRepository memberRepository;

	/**
	 * Given a Member and a list of WellnessEvents, add the member to each
	 * WellnessEvent in the list.
	 *
	 * @param eventList
	 *            A list of WellnessEvents to add a Member to.
	 * @param memberID
	 *            A valid int Member ID
	 * @see Member
	 * @see WellnessEvent
	 */
	@ApiOperation(value = "Adds a Member to a set of WellnessEvents")
	@RequestMapping(path = "/api/addMemberToEvents/{memberID}", method = RequestMethod.PUT)
	public void addMemberToEvents(@PathVariable Integer memberID, @RequestBody Set<WellnessEvent> eventList) {
		Member addingMember = memberRepository.findOne(memberID);
		for (WellnessEvent we : eventList) {
			WellnessEvent weAdding = wellnessEventRepository.findOne(we.getId());
			weAdding.addMember(addingMember);
			wellnessEventRepository.saveAndFlush(weAdding);
		}
	}

	/**
	 * Given a Member and a list of WellnessEvents, remove the member from
	 * everything and then add the member to each WellnessEvent in the list.
	 *
	 * @param eventList
	 *            A list of WellnessEvents to add a Member to.
	 * @param memberID
	 *            A valid int Member ID
	 * @see Member
	 * @see WellnessEvent
	 */
	@ApiOperation(value = "Adds a Member to a set of WellnessEvents after removing from all WellnessEvents")
	@RequestMapping(path = "/api/addMemberToEventsNuclearOption/{memberID}", method = RequestMethod.PUT)
	public void addMemberToEventsNuclearOption(@PathVariable Integer memberID,
			@RequestBody Set<WellnessEvent> eventList) {
		Member addingMember = memberRepository.findOne(memberID);
		List<WellnessEvent> removeList = wellnessEventRepository.findAll();
		for (WellnessEvent we : removeList) {
			we.removeMember(addingMember);
			wellnessEventRepository.saveAndFlush(we);
		}
		for (WellnessEvent we : eventList) {
			WellnessEvent weAdding = wellnessEventRepository.findOne(we.getId());
			weAdding.addMember(addingMember);
			wellnessEventRepository.saveAndFlush(weAdding);
		}
	}

	/**
	 * Given a Member and two lists of WellnessEvents, add the member to each
	 * WellnessEvent in the add list and remove the member from each WellnessEvent
	 * in the remove list.
	 *
	 * @param addEventList
	 *            A list of WellnessEvents to add a Member to.
	 * @param removeEventList
	 *            A list of WellnessEvents to remove a Member from.
	 * @param memberID
	 *            A valid int Member ID
	 * @see Member
	 * @see WellnessEvent
	 */
	@ApiOperation(value = "Updates the WellnessEvents for a Member (must pass two lists for add and remove")
	@RequestMapping(path = "/api/updateMemberEvents/{memberID}", method = RequestMethod.PUT)
	public void updateMemberEvents(@PathVariable Integer memberID,
			@RequestBody MemberRegistrationUpdate memberRegistrationUpdate) {
		Member updatingMember = memberRepository.findOne(memberID);
		Set<WellnessEvent> addEventList = memberRegistrationUpdate.addEventList;
		Set<WellnessEvent> removeEventList = memberRegistrationUpdate.removeEventList;
		for (WellnessEvent we : addEventList) {
			WellnessEvent weAdding = wellnessEventRepository.findOne(we.getId());
			weAdding.addMember(updatingMember);
			wellnessEventRepository.saveAndFlush(weAdding);
		}
		for (WellnessEvent we : removeEventList) {
			WellnessEvent weRemoving = wellnessEventRepository.findOne(we.getId());
			weRemoving.removeMember(updatingMember);
			wellnessEventRepository.saveAndFlush(weRemoving);
		}
	}

	/**
	 * Given a Member and a list of WellnessEvents, remove the member from each
	 * event.
	 *
	 * @param eventList
	 *            A list of WellnessEvents to remove a Member from.
	 * @param memberID
	 *            A valid int Member ID
	 * @see Member
	 * @see WellnessEvent
	 */
	@ApiOperation(value = "Remove a Member from a set of WellnessEvents")
	@RequestMapping(path = "/api/removeMemberFromEvents/{memberID}", method = RequestMethod.PUT)
	public void removeMemberFromEvents(@PathVariable Integer memberID, @RequestBody Set<WellnessEvent> eventList) {
		Member removingMember = memberRepository.findOne(memberID);
		for (WellnessEvent we : eventList) {
			WellnessEvent weRemoving = wellnessEventRepository.findOne(we.getId());
			weRemoving.removeMember(removingMember);
			// removingMember.removeMedallion(weRemoving.getMedallion());
			wellnessEventRepository.saveAndFlush(weRemoving);
		}
	}

	/**
	 * Given a Member, return the WellnessEvents that member has registered for.
	 *
	 * @param memberID
	 *            A valid int Member ID
	 * @return eventList - a list of WellnessEvents a Member has registered for.
	 * @see Member
	 * @see WellnessEvent
	 */
	@ApiOperation(value = "Show the WellnessEvents the logged in Member has registered for")
	@RequestMapping(path = "/api/registeredEvents/{memberID}", method = RequestMethod.GET)
	public Set<WellnessEvent> registeredEvents(@PathVariable Integer memberID) {
		Member m = memberRepository.findOne(memberID);
		Set<WellnessEvent> eventList = m.getWellnessEvents();
		return eventList;
	}

}
