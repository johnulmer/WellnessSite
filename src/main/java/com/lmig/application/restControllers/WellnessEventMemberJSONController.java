package com.lmig.application.restControllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.repositories.WellnessEventRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Member Event Registrations", description="JSON operations pertaining to WellnessSite Members registering or canceling for an Event")
public class WellnessEventMemberJSONController {
	
	@Autowired
	private WellnessEventRepo wellnessEventRepository;
	@Autowired
	private MemberRepository memberRepository;

//	@ApiOperation(value = "testing adding members to event")
//	@RequestMapping(path = "/api/testAddEventMembers", method = RequestMethod.GET)
//	public WellnessEvent testAddEventMembers() {
//		System.out.println("event member connection");
//		WellnessEvent we = wellnessEventRepository.findOne(4);
//		we.addMember(memberRepository.findOne(2));
//		we.addMember(memberRepository.findOne(3));
//		we.addMember(memberRepository.findOne(6));
//		wellnessEventRepository.save(we);
//	    return we;
//	}
	
	/**
	 * TODO retrieves Member from Session and adds the member to each WellnessEvent in the list. 
	 *
	 * @param eventList A list of WellnessEvents to add a Member to.
	 * @param Member    A Member retrieved from the session (currently hard-coded).
	 * @see             Member
	 * @see             WellnessEvent
	 */
	@ApiOperation(value = "Adds a Member to a set of WellnessEvents")
	@RequestMapping(path = "/api/addMemberToEvent", method = RequestMethod.PUT)
	public void addMemberToEvents(@RequestBody Set<WellnessEvent> eventList) {
	    Member addingMember = memberRepository.findOne(2); // TODO update this from session when session is set
		for (WellnessEvent we : eventList) {
		    WellnessEvent weAdding = wellnessEventRepository.findOne(we.getId());
			weAdding.addMember(addingMember);  
			wellnessEventRepository.saveAndFlush(weAdding);
		}
	}

	/**
	 * TODO retrieves Member from Session and removes the member from each WellnessEvent in the list. 
	 *
	 * @param eventList A list of WellnessEvents to remove a Member from.
	 * @param Member    A Member retrieved from the session (currently hard-coded).
	 * @see             Member
	 * @see             WellnessEvent
	 */
	@ApiOperation(value = "Remove a Member from a set of WellnessEvents")
	@RequestMapping(path = "/api/removeMemberFromEvents", method = RequestMethod.PUT)
	public void removeMemberFromEvents(@RequestBody Set<WellnessEvent> eventList) {
	    Member removingMember = memberRepository.findOne(2); // TODO update this from session when session is set
		for (WellnessEvent we : eventList) {
		    WellnessEvent weRemoving = wellnessEventRepository.findOne(we.getId());
		    weRemoving.removeMember(removingMember);  
			wellnessEventRepository.saveAndFlush(weRemoving);
		}
	}
	
	
}
