package com.lmig.application.restControllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// application entities and repositories
import com.lmig.application.activityHibernateEntities.HeartrateActivityRow;
import com.lmig.application.activityHibernateEntities.MeetingGoalsActivityRow;
import com.lmig.application.activityHibernateEntities.PerformanceImprovementActivityRow;
import com.lmig.application.activityHibernateEntities.StepsOverTimeActivityRow;
import com.lmig.application.repositories.HeartrateActivityRowRepository;
import com.lmig.application.repositories.MeetingGoalsActivityRowRepository;
import com.lmig.application.repositories.PerformanceImprovementActivityRowRepository;
import com.lmig.application.repositories.StepsOverTimeActivityRowRepository;
//documentation
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Activities", description="JSON operations pertaining to activities and Fitbit connection")
public class FitbitJSONController {
	
	@Autowired
	private HeartrateActivityRowRepository heartrateActivityRowRepository;
	
	@Autowired
	private StepsOverTimeActivityRowRepository stepsOverTimeActivityRowRepository;
	
	@Autowired
	private MeetingGoalsActivityRowRepository meetingGoalsActivityRowRepository;
	
	@Autowired
	private PerformanceImprovementActivityRowRepository performanceImprovementActivityRowRepository;
	
	@ApiOperation(value = "Get all Heartrate Activity rows")
	@RequestMapping(path = "/api/activity/getAllHeartrateRows", method = RequestMethod.GET)
	public List<HeartrateActivityRow> getAllHeartrateRows() {
		return heartrateActivityRowRepository.findAll();
	}
	
	@ApiOperation(value = "Get Heartrate Activity rows per event")
	@RequestMapping(path = "/api/activity/getAllHeartrateRowsByEvent/{wellnessEventID}", method = RequestMethod.GET)
	public List<HeartrateActivityRow> getHeartrateRowsByEvent(int wellnessEventID) {
		return heartrateActivityRowRepository.findByEvent(wellnessEventID);
	}	
	
	@ApiOperation(value = "Get Heartrate Activity rows per member")
	@RequestMapping(path = "/api/activity/getAllHeartrateRowsByMember/{memberID}", method = RequestMethod.GET)
	public List<HeartrateActivityRow> getHeartrateRowsByMember(int memberID) {
		return heartrateActivityRowRepository.findByMember(memberID);
	}	
	
	@ApiOperation(value = "Get all Steps Over Time Activity rows")
	@RequestMapping(path = "/api/activity/getAllStepsOverTimeRows", method = RequestMethod.GET)
	public List<StepsOverTimeActivityRow> getAllStepsOverTimeRows() {
		return stepsOverTimeActivityRowRepository.findAll();
	}
	
	@ApiOperation(value = "Get Steps Over Time Activity rows per event")
	@RequestMapping(path = "/api/activity/getAllStepsOverTimeRowsByEvent/{wellnessEventID}", method = RequestMethod.GET)
	public List<StepsOverTimeActivityRow> getAllStepsOverTimeRowsByEvent(int wellnessEventID) {
		return stepsOverTimeActivityRowRepository.findByEvent(wellnessEventID);
	}
	
	@ApiOperation(value = "Get Steps Over Time Activity rows per member")
	@RequestMapping(path = "/api/activity/getAllStepsOverTimeRowsByMember/{memberID}", method = RequestMethod.GET)
	public List<StepsOverTimeActivityRow> getAllStepsOverTimeRowsByMember(int memberID) {
		return stepsOverTimeActivityRowRepository.findByMember(memberID);
	}
	
	@ApiOperation(value = "Get all Performance Improvement Activity rows")
	@RequestMapping(path = "/api/activity/getAllPerformanceImprovementRows", method = RequestMethod.GET)
	public List<PerformanceImprovementActivityRow> getAllPerformanceImprovementRows() {
		return performanceImprovementActivityRowRepository.findAll();
	}
	
	@ApiOperation(value = "Get Performance Improvement Activity rows per event")
	@RequestMapping(path = "/api/activity/getAllPerformanceImprovementRowsByEvent/{wellnessEventID}", method = RequestMethod.GET)
	public List<PerformanceImprovementActivityRow> getPerformanceImprovementRowsByEvent(int wellnessEventID) {
		return performanceImprovementActivityRowRepository.findByEvent(wellnessEventID);
	}
	
	@ApiOperation(value = "Get Performance Improvement Activity rows per member")
	@RequestMapping(path = "/api/activity/getAllPerformanceImprovementRowsByMember/{memberID}", method = RequestMethod.GET)
	public List<PerformanceImprovementActivityRow> getPerformanceImprovementRowsByMember(int memberID) {
		return performanceImprovementActivityRowRepository.findByMember(memberID);
	}

	@ApiOperation(value = "Get all Meeting Goals Activity rows")
	@RequestMapping(path = "/api/activity/getAllMeetingGoalsRows", method = RequestMethod.GET)
	public List<MeetingGoalsActivityRow> getAllMeetingGoalsRows() {
		return meetingGoalsActivityRowRepository.findAll();
	}
	
	@ApiOperation(value = "Get Meeting Goals Activity rows per event")
	@RequestMapping(path = "/api/activity/getAllMeetingGoalsRowsByEvent/{wellnessEventID}", method = RequestMethod.GET)
	public List<MeetingGoalsActivityRow> getMeetingGoalsByEvent(int wellnessEventID) {
		return meetingGoalsActivityRowRepository.findByEvent(wellnessEventID);
	}
	
	@ApiOperation(value = "Get Meeting Goals Activity rows per member")
	@RequestMapping(path = "/api/activity/getAllMeetingGoalsRowsByMember/{memberID}", method = RequestMethod.GET)
	public List<MeetingGoalsActivityRow> getMeetingGoalsRowsByMember(int memberID) {
		return meetingGoalsActivityRowRepository.findByMember(memberID);
	}

//	@ApiOperation(value = "Show the stat summary for a step event")
//	@RequestMapping(path = "/api/activity/stepsOverTimeStatSummary/{eventID}", method = RequestMethod.GET)
//	public IntSummaryStatistics stepsOverTimeStatSummary(@PathVariable Integer eventID) {
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		return sotai.stepsOverTimeStatSummary(eventID);
//	}
//	
//	@ApiOperation(value = "this will drop four tables for the demo data of the four event types and rebuild them")
//	@RequestMapping(path = "/api/activity/mutuallyAssuredDestruction", method = RequestMethod.GET)
//	public void mutuallyAssuredDestruction() {
//		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
//		hai.reset();
//		
//		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
//		peai.reset();
//		
//		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
//		mgai.reset();
//		
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		sotai.reset();
//	}
//	
//	@ApiOperation(value = "Returns four lists showing the events with data per activity type")
//	@RequestMapping(path = "/api/activity/EventsByActivityType", method = RequestMethod.GET)
//	public HashMap<String, ArrayList<Integer>> getEventsByActivityType() {
//		HashMap<String, ArrayList<Integer>> returnHash = new HashMap<String, ArrayList<Integer>>();
//		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
//		ArrayList<Integer> heartrateList = hai.getEventList();
//		returnHash.put("heartrateEvents", heartrateList);
//		
//		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
//		ArrayList<Integer> performanceImprovementList = peai.getEventList();
//		returnHash.put("performanceImprovementEvents", performanceImprovementList);
//		
//		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
//		ArrayList<Integer> meetingGoalsList = mgai.getEventList();
//		returnHash.put("meetingGoalsEvents", meetingGoalsList);
//		
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		ArrayList<Integer> stepsOverTimeList = sotai.getEventList();
//		returnHash.put("stepsOverTimeEvents", stepsOverTimeList);
//		return returnHash;
//	}
//	
//	@ApiOperation(value = "Returns four lists showing the events with data per activity type per member")
//	@RequestMapping(path = "/api/activity/getMemberEvents/{memberID}", method = RequestMethod.GET)
//	public HashMap<String, ArrayList<Integer>> getMemberEvents(@PathVariable Integer memberID) {
//		HashMap<String, ArrayList<Integer>> returnHash = new HashMap<String, ArrayList<Integer>>();
//		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
//		ArrayList<Integer> heartrateList = hai.getMemberEventList(memberID);
//		returnHash.put("heartrateEvents", heartrateList);
//		
//		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
//		ArrayList<Integer> performanceImprovementList = peai.getMemberEventList(memberID);
//		returnHash.put("performanceImprovementEvents", performanceImprovementList);
//		
//		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
//		ArrayList<Integer> meetingGoalsList = mgai.getMemberEventList(memberID);
//		returnHash.put("meetingGoalsEvents", meetingGoalsList);
//		
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		ArrayList<Integer> stepsOverTimeList = sotai.getMemberEventList(memberID);
//		returnHash.put("stepsOverTimeEvents", stepsOverTimeList);
//		return returnHash;
//	}
//	
//	@ApiOperation(value = "Returns a list of all Heartrate entries for an event")
//	@RequestMapping(path = "/api/activity/HeartrateDataByEvent/{eventID}", method = RequestMethod.GET)
//	public ArrayList<HeartrateActivityRow> getHeartrateData(@PathVariable Integer eventID) {	
//		HeartrateActivityImplementation hai = new HeartrateActivityImplementation();
//		return hai.getEventRows(eventID);
//	}
//	
//	@ApiOperation(value = "Returns a list of all Performance Improvement entries for an event")	
//	@RequestMapping(path = "/api/activity/PerformanceImprovementDataByEvent/{eventID}", method = RequestMethod.GET)
//	public ArrayList<PerformanceImprovementActivityRow> getPerformanceImprovementData(@PathVariable Integer eventID) {
//		PerformanceImprovementActivityImplementation peai = new PerformanceImprovementActivityImplementation();
//		return peai.getEventRows(eventID);
//	}
//	
//	@ApiOperation(value = "Returns a list of all Meeting Goals entries for an event")
//	@RequestMapping(path = "/api/activity/MeetingGoalsDataByEvent/{eventID}", method = RequestMethod.GET)
//	public ArrayList<MeetingGoalsActivityRow> getMeetingGoals(@PathVariable Integer eventID) {
//		MeetingGoalsActivityImplementation mgai = new MeetingGoalsActivityImplementation();
//		return mgai.getEventRows(eventID);
//	}
//	
//	@ApiOperation(value = "Returns a list of all Steps Over Time entries for an event")
//	@RequestMapping(path = "/api/activity/StepsOverTimeDataByEvent/{eventID}", method = RequestMethod.GET)
//	public ArrayList<StepsOverTimeActivityRow> getStepsOverTimeData(@PathVariable Integer eventID) {
//		StepsOverTimeActivityImplementation sotai = new StepsOverTimeActivityImplementation();
//		return sotai.getEventRows(eventID);
//	}
//
//	// fitbit data retrieval - no integration yet
//	
//	// meeting goals
//	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
//	//Set<Member> members = we.getMembers();
//		// delete all rows in Heartrate table with that eventID
//	// get the start and stop days for that event
//		// for each user, get the fitbit token we store << based on current understanding of the docs
//		// for each user, get the goal & actual values for each day in the range
//			// for each row of data, save to MeetingGoals table w/ memberID & eventID key
//	// anything else?
//	
//	// performance improvement
//	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
//	//Set<Member> members = we.getMembers();
//	// DO NOT DELETE rows in activity table until feed from fitbit works
//		// delete all rows in Heartrate table with that eventID
//	// get the start and stop days of the event
//		// for each user, get the fitbit token we store << based on current understanding of the docs
//		// for each user, get the stat of interest from the start day and stop day of the event
//			// write two rows of data to the PerformanceImprovement table for event start & event stop
//	// anything else?
//	
//	// heartrate
//	// DO NOT DELETE rows in activity table until feed from fitbit works
//	// delete all rows in Heartrate table with that eventID
//// get the start and stop time of the event
//	// for each user, get the fitbit token we store << based on current understanding of the docs
//	// for each user, request the data from fitbit (heartrate stat for duration start > stop, 1 min interval)
//		// for each row of data, save to HeartrateActivity table w/ memberID & eventID key
//		//  probably won't use Hibernate for this, could be challenging to sort out in time remaining
//// anything else?
//	
//	// steps over time
//	//WellnessEvent we = wellnessEventRepository.getOne(eventID);
//	//Set<Member> members = we.getMembers();
//		// delete all rows in Heartrate table with that eventID
//	// get the start and stop days for that event
//		// for each user, get the fitbit token we store << based on current understanding of the docs
//		// for each user, get the daily steps value for each day in the range
//			// for each row of data, save to StepsOverTime table w/ memberID & eventID key
//	// anything else?
//	
	
@ApiOperation(value = "RESET Performance Improvement Activity rows")
@RequestMapping(path = "/api/activity/resetPerformanceImprovementRows", method = RequestMethod.GET)
public void PerformanceImprovementRows() {
	performanceImprovementActivityRowRepository.deleteAll();
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(3,2, 7.5, 6.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(3,4, 7.2, 4.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(3,6, 8.5, 3.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(4,1, 4.5, 6.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(4,3, 2.5, 7.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(4,5, 3.5, 7.3));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(5,1, 4.5, 4.5));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(5,3, 22.0, 24.6));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(5,6, 18, 20));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(6,2, 4.0, 6.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(6,5, 3.5, 5.2));
	performanceImprovementActivityRowRepository.save(new PerformanceImprovementActivityRow(6,6, 2.4, 4.7));
}

@ApiOperation(value = "RESET Steps Meeting Goals Activity rows")
@RequestMapping(path = "/api/activity/resetMeetingGoalsRows", method = RequestMethod.GET)
public void resetMeetingGoalsRows() {
	meetingGoalsActivityRowRepository.deleteAll();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	try {
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 1,formatter.parse("2017-06-21"), 119, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 2,formatter.parse("2017-06-21"), 23, 64));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 3,formatter.parse("2017-06-21"), 45, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 1,formatter.parse("2017-06-22"), 50, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 2,formatter.parse("2017-06-22"), 21, 64));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 3,formatter.parse("2017-06-22"), 43, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 1,formatter.parse("2017-06-23"), 25, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 2,formatter.parse("2017-06-23"), 67, 64));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(7, 3,formatter.parse("2017-06-23"), 134, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 1,formatter.parse("2017-07-21"), 98, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 4,formatter.parse("2017-07-21"), 87, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 6,formatter.parse("2017-07-21"), 76, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 1,formatter.parse("2017-07-22"), 68, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 4,formatter.parse("2017-07-22"), 112, 128));
		meetingGoalsActivityRowRepository.save(new MeetingGoalsActivityRow(8, 6,formatter.parse("2017-07-22"), 129, 128));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	}

@ApiOperation(value = "RESET Heartrate Activity rows")
@RequestMapping(path = "/api/activity/resetHeartrateRows", method = RequestMethod.GET)
public void resetHeartrateRows() {
	heartrateActivityRowRepository.deleteAll();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try {
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:00"), 45));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:00"), 40));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:00"), 42));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:00"), 44));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:00"), 50));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:01"), 50));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:01"), 51));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:01"), 43));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:01"), 49));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:01"), 51));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:02"), 52));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:02"), 64));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:02"), 78));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:02"), 81));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:02"), 75));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:03"), 61));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:03"), 63));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:03"), 23));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:03"), 45));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:03"), 52));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:04"), 67));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:04"), 77));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:04"), 88));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:04"), 99));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:04"), 72));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:05"), 64));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:05"), 62));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:05"), 61));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:05"), 60));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:05"), 61));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:06"), 47));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:06"), 48));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:06"), 49));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:06"), 56));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:06"), 57));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 1, formatter.parse("2017-07-21 13:07"), 43));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 2, formatter.parse("2017-07-21 13:07"), 67));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 3, formatter.parse("2017-07-21 13:07"), 89));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 4, formatter.parse("2017-07-21 13:07"), 91));
		heartrateActivityRowRepository.save(new HeartrateActivityRow(9, 5, formatter.parse("2017-07-21 13:07"), 45));
	}
	 catch (ParseException e) {
			e.printStackTrace();
		}
}

@ApiOperation(value = "RESET Steps Over Time Activity rows")
@RequestMapping(path = "/api/activity/resetStepsOverTimeRows", method = RequestMethod.GET)
public void resetStepsOverTimeRows() {
	stepsOverTimeActivityRowRepository.deleteAll();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	try {
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-21"), 1500));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-21"), 4500));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-21"), 3500));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-22"), 11500));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-22"), 15000));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-22"), 10000));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 1, formatter.parse("2017-07-23"), 2000));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 2, formatter.parse("2017-07-23"), 1750));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(1, 3, formatter.parse("2017-07-23"), 3000));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-22"), 15000));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-22"), 4234));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-22"), 3575));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-23"), 21500));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-23"), 15003));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-23"), 10017));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 4, formatter.parse("2017-07-24"), 8756));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 5, formatter.parse("2017-07-24"), 2234));
		stepsOverTimeActivityRowRepository.save(new StepsOverTimeActivityRow(2, 6, formatter.parse("2017-07-24"), 4576));
	} catch (ParseException e) {
		e.printStackTrace();
	}
}
}
