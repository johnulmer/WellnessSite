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

	@ApiOperation(value = "testing adding members to event")
	@RequestMapping(path = "/api/testAddEventMembers", method = RequestMethod.GET)
	public WellnessEvent testAddEventMembers() {
		System.out.println("event member connection");
		WellnessEvent we = wellnessEventRepository.findOne(4);
		we.addMember(memberRepository.findOne(2));
		we.addMember(memberRepository.findOne(3));
		we.addMember(memberRepository.findOne(6));
		wellnessEventRepository.save(we);
	    return we;
	}
	
	@ApiOperation(value = "testing adding a member to events")
	@RequestMapping(path = "/api/addMemberToEvent", method = RequestMethod.PUT)
	public void addMemberToEvents(@RequestBody Set<WellnessEvent> eventList) {

	    Member addingMember = memberRepository.findOne(2); // update this from session when session is set
		System.out.println("adding " + addingMember.getScreenName() + " to events");
		for (WellnessEvent we : eventList) {
		    System.out.println("ID: " + we.getId());
		    WellnessEvent weAdding = wellnessEventRepository.findOne(we.getId());
			weAdding.addMember(addingMember);  
			wellnessEventRepository.saveAndFlush(weAdding);
		}
	}

	@ApiOperation(value = "testing removing a member from events")
	@RequestMapping(path = "/api/removeMemberFromEvents", method = RequestMethod.PUT)
	public void removeMemberFromEvents(@RequestBody Set<WellnessEvent> eventList) {
		
	    Member removingMember = memberRepository.findOne(2); // update this from session when session is set
		System.out.println("removing " + removingMember.getScreenName() + " from events");
		
		for (WellnessEvent we : eventList) {
		    System.out.println("ID: " + we.getId());
		    WellnessEvent weRemoving = wellnessEventRepository.findOne(we.getId());
		    weRemoving.removeMember(removingMember);  
			wellnessEventRepository.saveAndFlush(weRemoving);
		}
//		WellnessEvent we = wellnessEventRepository.findOne(4);
//		we.addMember(memberRepository.findOne(2));
//		wellnessEventRepository.save(we);
	}
	
	
}
