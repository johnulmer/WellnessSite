package com.lmig.application.restControllers;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lmig.application.activityEntities.HeartrateActivityRow;
import com.lmig.application.activityEntities.MeetingGoalsActivityRow;
import com.lmig.application.activityEntities.PerformanceImprovementActivityRow;
import com.lmig.application.activityEntities.StepsOverTimeActivityRow;
import com.lmig.application.activityImplementations.MeetingGoalsActivityImplementation;
import com.lmig.application.activityImplementations.PerformanceImprovementActivityImplementation;
import com.lmig.application.activityImplementations.StepsOverTimeActivityImplementation;
import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;
import com.lmig.application.repositories.WellnessEventRepo;

import io.swagger.annotations.Api;

@RestController
@Api(value="Fitbit", description="JSON operations pertaining to Fitbit connection")
public class FitbitJSONController {
	
	@Autowired
	private WellnessEventRepo wellnessEventRepository;
	
	@RequestMapping(path = "/api/fitbitAPITest", method = RequestMethod.GET)
	public void fitbitAPITest() {	
		System.out.println("calling fitbit method - not working yet");
	}

	@RequestMapping(path = "/api/fitbit/retrieveHeartrateData/{eventID}", method = RequestMethod.GET)
	public ArrayList<HeartrateActivityRow> fitbitHeartrateData(@PathVariable Integer eventID) {	
		ArrayList<HeartrateActivityRow> returnList = new ArrayList<HeartrateActivityRow>();
		System.out.println("calling heartrate update method - not working yet");
		WellnessEvent we = wellnessEventRepository.getOne(eventID);
		Set<Member> members = we.getMembers();
		// DO NOT DELETE rows in activity table until feed from fitbit works
			// delete all rows in Heartrate table with that eventID
		// get the start and stop time of the event
			// for each user, get the fitbit token we store << based on current understanding of the docs
			// for each user, request the data from fitbit (heartrate stat for duration start > stop, 1 min interval)
				// for each row of data, save to HeartrateActivity table w/ memberID & eventID key
				//  probably won't use Hibernate for this, could be challenging to sort out in time remaining
		// anything else?
		return returnList;
	}
	
	@RequestMapping(path = "/api/fitbit/retrievePerformanceImprovementData/{eventID}", method = RequestMethod.GET)
	public ArrayList<PerformanceImprovementActivityRow> fitbitPerformanceImprovementData(@PathVariable Integer eventID) {
		ArrayList<PerformanceImprovementActivityRow> returnList = new ArrayList<PerformanceImprovementActivityRow>();
		System.out.println("calling performance improvement method - not working yet");
		//WellnessEvent we = wellnessEventRepository.getOne(eventID);
		//Set<Member> members = we.getMembers();
		// DO NOT DELETE rows in activity table until feed from fitbit works
			// delete all rows in Heartrate table with that eventID
		// get the start and stop days of the event
			// for each user, get the fitbit token we store << based on current understanding of the docs
			// for each user, get the stat of interest from the start day and stop day of the event
				// write two rows of data to the PerformanceImprovement table for event start & event stop
		// anything else?
		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
		return returnList;
	}
	
	@RequestMapping(path = "/api/fitbit/retrieveMeetingGoalsData/{eventID}", method = RequestMethod.GET)
	public ArrayList<MeetingGoalsActivityRow> fitbitMeetingGoals(@PathVariable Integer eventID) {
		ArrayList<MeetingGoalsActivityRow> returnList = new ArrayList<MeetingGoalsActivityRow>();
		System.out.println("calling meeting goals method - not working yet");
		//WellnessEvent we = wellnessEventRepository.getOne(eventID);
		//Set<Member> members = we.getMembers();
		// DO NOT DELETE rows in activity table until feed from fitbit works
			// delete all rows in Heartrate table with that eventID
		// get the start and stop days for that event
			// for each user, get the fitbit token we store << based on current understanding of the docs
			// for each user, get the goal & actual values for each day in the range
				// for each row of data, save to MeetingGoals table w/ memberID & eventID key
		// anything else?
		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
		returnList = mgai.getEventRows(eventID);
		return returnList;
	}
	
	@RequestMapping(path = "/api/fitbit/retrieveStepsOverTimeData/{eventID}", method = RequestMethod.GET)
	public ArrayList<StepsOverTimeActivityRow> fitbitStepsOverTimeData(@PathVariable Integer eventID) {
		ArrayList<StepsOverTimeActivityRow> returnList = new ArrayList<StepsOverTimeActivityRow>();
		System.out.println("calling steps over time method - not working yet");
		//WellnessEvent we = wellnessEventRepository.getOne(eventID);
		//Set<Member> members = we.getMembers();
		// DO NOT DELETE rows in activity table until feed from fitbit works
			// delete all rows in Heartrate table with that eventID
		// get the start and stop days for that event
			// for each user, get the fitbit token we store << based on current understanding of the docs
			// for each user, get the daily steps value for each day in the range
				// for each row of data, save to StepsOverTime table w/ memberID & eventID key
		// anything else?
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		returnList = sotai.getEventRows(eventID);
		return returnList;
	}

}
