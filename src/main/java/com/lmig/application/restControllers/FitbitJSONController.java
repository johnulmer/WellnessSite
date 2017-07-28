package com.lmig.application.restControllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lmig.application.activityEntities.HeartrateActivityRow;
import com.lmig.application.activityEntities.MeetingGoalsActivityRow;
import com.lmig.application.activityEntities.PerformanceImprovementActivityRow;
import com.lmig.application.activityEntities.StepsOverTimeActivityRow;
import com.lmig.application.activityHibernateEntities.HibernateHeartrateActivityRow;
import com.lmig.application.activityHibernateEntities.HibernateStepsOverTimeActivityRow;
import com.lmig.application.activityImplementations.HeartrateActivityImplementation;
import com.lmig.application.activityImplementations.MeetingGoalsActivityImplementation;
import com.lmig.application.activityImplementations.PerformanceImprovementActivityImplementation;
import com.lmig.application.activityImplementations.StepsOverTimeActivityImplementation;
import com.lmig.application.repositories.HibernateHeartrateActivityRowRepository;
import com.lmig.application.repositories.HibernateStepsOverTimeActivityRowRepository;
import com.lmig.application.repositories.WellnessEventRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Activities", description="JSON operations pertaining to activities and Fitbit connection")
public class FitbitJSONController {
	
	@Autowired
	private WellnessEventRepo wellnessEventRepository;
	
	@Autowired
	private HibernateHeartrateActivityRowRepository hibernateHeartrateActivityRowRepository;
	
	@Autowired
	private HibernateStepsOverTimeActivityRowRepository hibernateStepsOverTimeActivityRowRepository;
	
//	public HibernateHeartrateActivityRow(int eventID, 
//			int memberID, 
//			Date statTimestamp, 
//			int stat
	
	
	@ApiOperation(value = "RESET Heartrate Activity rows - Attempt to use Hibernate to access Heartrate Activity")
	@RequestMapping(path = "/api/activity/resetHeartrateRows", method = RequestMethod.GET)
	public void resetHeartrateRows() {
		hibernateHeartrateActivityRowRepository.deleteAll();
		//hibernateHeartrateActivityRowRepository.save(new HibernateHeartrateActivityRow());
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		return sotai.stepsOverTimeStatSummary(eventID);
	}
	
	@ApiOperation(value = "RESET Steps Over Time Activity rows - Attempt to use Hibernate to access Steps Over Time Activity")
	@RequestMapping(path = "/api/activity/getAllStepsOverTimeRows", method = RequestMethod.GET)
	public List<HibernateStepsOverTimeActivityRow> getAllStepsOverTimeRows() {
		return hibernateStepsOverTimeActivityRowRepository.findAll();
	}
	
	@ApiOperation(value = "RESET Steps Over Time Activity rows - Attempt to use Hibernate to access Steps Over Time Activity")
	@RequestMapping(path = "/api/activity/resetStepsOverTimeRows", method = RequestMethod.GET)
	public void resetStepsOverTimeRows() {
		hibernateStepsOverTimeActivityRowRepository.deleteAll();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-21"), 1500));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-21"), 4500));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-21"), 3500));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-22"), 11500));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-22"), 15000));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-22"), 10000));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-23"), 2000));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-23"), 1750));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-23"), 3000));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-22"), 15000));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-22"), 4234));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-22"), 3575));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-23"), 21500));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-23"), 15003));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-23"), 10017));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-24"), 8756));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-24"), 2234));
			hibernateStepsOverTimeActivityRowRepository.save(new HibernateStepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-24"), 4576));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "Show the stat summary for a step event")
	@RequestMapping(path = "/api/activity/stepsOverTimeStatSummary/{eventID}", method = RequestMethod.GET)
	public IntSummaryStatistics stepsOverTimeStatSummary(@PathVariable Integer eventID) {
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		return sotai.stepsOverTimeStatSummary(eventID);
	}
	
	@ApiOperation(value = "this will drop four tables for the demo data of the four event types and rebuild them")
	@RequestMapping(path = "/api/activity/mutuallyAssuredDestruction", method = RequestMethod.GET)
	public void mutuallyAssuredDestruction() {
		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
		hai.reset();
		
		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
		peai.reset();
		
		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
		mgai.reset();
		
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		sotai.reset();
	}
	
	@ApiOperation(value = "Returns four lists showing the events with data per activity type")
	@RequestMapping(path = "/api/activity/EventsByActivityType", method = RequestMethod.GET)
	public HashMap<String, ArrayList<Integer>> getEventsByActivityType() {
		HashMap<String, ArrayList<Integer>> returnHash = new HashMap<String, ArrayList<Integer>>();
		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
		ArrayList<Integer> heartrateList = hai.getEventList();
		returnHash.put("heartrateEvents", heartrateList);
		
		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
		ArrayList<Integer> performanceImprovementList = peai.getEventList();
		returnHash.put("performanceImprovementEvents", performanceImprovementList);
		
		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
		ArrayList<Integer> meetingGoalsList = mgai.getEventList();
		returnHash.put("meetingGoalsEvents", meetingGoalsList);
		
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		ArrayList<Integer> stepsOverTimeList = sotai.getEventList();
		returnHash.put("stepsOverTimeEvents", stepsOverTimeList);
		return returnHash;
	}
	
	@ApiOperation(value = "Returns four lists showing the events with data per activity type per member")
	@RequestMapping(path = "/api/activity/getMemberEvents/{memberID}", method = RequestMethod.GET)
	public HashMap<String, ArrayList<Integer>> getMemberEvents(@PathVariable Integer memberID) {
		HashMap<String, ArrayList<Integer>> returnHash = new HashMap<String, ArrayList<Integer>>();
		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
		ArrayList<Integer> heartrateList = hai.getMemberEventList(memberID);
		returnHash.put("heartrateEvents", heartrateList);
		
		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
		ArrayList<Integer> performanceImprovementList = peai.getMemberEventList(memberID);
		returnHash.put("performanceImprovementEvents", performanceImprovementList);
		
		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
		ArrayList<Integer> meetingGoalsList = mgai.getMemberEventList(memberID);
		returnHash.put("meetingGoalsEvents", meetingGoalsList);
		
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		ArrayList<Integer> stepsOverTimeList = sotai.getMemberEventList(memberID);
		returnHash.put("stepsOverTimeEvents", stepsOverTimeList);
		return returnHash;
	}
	
	@ApiOperation(value = "Returns a list of all Heartrate entries for an event")
	@RequestMapping(path = "/api/activity/HeartrateDataByEvent/{eventID}", method = RequestMethod.GET)
	public ArrayList<HeartrateActivityRow> getHeartrateData(@PathVariable Integer eventID) {	
		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
		return hai.getEventRows(eventID);
	}
	
	@ApiOperation(value = "Returns a list of all Performance Improvement entries for an event")	
	@RequestMapping(path = "/api/activity/PerformanceImprovementDataByEvent/{eventID}", method = RequestMethod.GET)
	public ArrayList<PerformanceImprovementActivityRow> getPerformanceImprovementData(@PathVariable Integer eventID) {
		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
		return peai.getEventRows(eventID);
	}
	
	@ApiOperation(value = "Returns a list of all Meeting Goals entries for an event")
	@RequestMapping(path = "/api/activity/MeetingGoalsDataByEvent/{eventID}", method = RequestMethod.GET)
	public ArrayList<MeetingGoalsActivityRow> getMeetingGoals(@PathVariable Integer eventID) {
		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
		return mgai.getEventRows(eventID);
	}
	
	@ApiOperation(value = "Returns a list of all Steps Over Time entries for an event")
	@RequestMapping(path = "/api/activity/StepsOverTimeDataByEvent/{eventID}", method = RequestMethod.GET)
	public ArrayList<StepsOverTimeActivityRow> getStepsOverTimeData(@PathVariable Integer eventID) {
		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
		return sotai.getEventRows(eventID);
	}

	// fitbit data retrieval - no integration yet
	
	// meeting goals
	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
	//Set<Member> members = we.getMembers();
		// delete all rows in Heartrate table with that eventID
	// get the start and stop days for that event
		// for each user, get the fitbit token we store << based on current understanding of the docs
		// for each user, get the goal & actual values for each day in the range
			// for each row of data, save to MeetingGoals table w/ memberID & eventID key
	// anything else?
	
	// performance improvement
	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
	//Set<Member> members = we.getMembers();
	// DO NOT DELETE rows in activity table until feed from fitbit works
		// delete all rows in Heartrate table with that eventID
	// get the start and stop days of the event
		// for each user, get the fitbit token we store << based on current understanding of the docs
		// for each user, get the stat of interest from the start day and stop day of the event
			// write two rows of data to the PerformanceImprovement table for event start & event stop
	// anything else?
	
	// heartrate
	// DO NOT DELETE rows in activity table until feed from fitbit works
	// delete all rows in Heartrate table with that eventID
// get the start and stop time of the event
	// for each user, get the fitbit token we store << based on current understanding of the docs
	// for each user, request the data from fitbit (heartrate stat for duration start > stop, 1 min interval)
		// for each row of data, save to HeartrateActivity table w/ memberID & eventID key
		//  probably won't use Hibernate for this, could be challenging to sort out in time remaining
// anything else?
	
	// steps over time
	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
	//Set<Member> members = we.getMembers();
		// delete all rows in Heartrate table with that eventID
	// get the start and stop days for that event
		// for each user, get the fitbit token we store << based on current understanding of the docs
		// for each user, get the daily steps value for each day in the range
			// for each row of data, save to StepsOverTime table w/ memberID & eventID key
	// anything else?
	
}
