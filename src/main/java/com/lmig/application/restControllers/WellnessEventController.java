package com.lmig.application.restControllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.lmig.application.entities.Member;
import com.lmig.application.entities.WellnessEvent;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.repositories.WellnessEventRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Wellness Events")

public class WellnessEventController implements Controller {

	@Autowired
	private WellnessEventRepo wellnessEventRepo;
	@Autowired
	private MemberRepository memberRepository;

	// returns event by name
	@ApiOperation(value = "Returns event matching eventName", notes = "requires path variable eventName")
	@RequestMapping(path = "/api/eventbyname/{eventName}", method = RequestMethod.GET)
	public WellnessEvent findByEventName(@PathVariable(name = "eventName", required = true) String eventName) {
		WellnessEvent e = wellnessEventRepo.findByEventName(eventName);
		return e;
	}

	@ApiOperation(value = "Returns event matching ID", notes = "requires path variable ID")
	@RequestMapping(path = "/api/eventbyid/{id}", method = RequestMethod.GET)
	public WellnessEvent getEventByID(@PathVariable Integer id) {
		return wellnessEventRepo.getOne(id);
	}

	@RequestMapping(path = "/api/add/event", method = RequestMethod.POST)
	@ApiOperation(value = "Add an Event", notes = "adds a new event - event name must be in request body and must not already")
	public ResponseEntity<WellnessEvent> createWellnessEvent(@RequestBody WellnessEvent eventBody) {
		WellnessEvent evt = new ConsumeResults().getLngLatFromGoogle(eventBody);
		wellnessEventRepo.save(evt);
		return new ResponseEntity<WellnessEvent>(evt, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/api/delete/event/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete Event", notes = "Deletes an existing event whose ID matches parameter in URL")
	public ResponseEntity<String> deleteEvent(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/event/{id} DELETE " + id);
		if (id == null) {
			return new ResponseEntity<String>("Cannot delete", HttpStatus.BAD_REQUEST);
		}
		wellnessEventRepo.delete(id);
		return new ResponseEntity<String>("Event deleted", HttpStatus.OK);
	}

	@RequestMapping(path = "api/event/searchStartDate", method = RequestMethod.GET)
	@ApiOperation(value = "Search by Start Date", notes = "Search by starting date of wellness event")
	public List<WellnessEvent> findByStartDate(@RequestParam String startsOn, @RequestParam String endsOn) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatterUno = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<WellnessEvent> wellnessEvent = wellnessEventRepo.searchByStartDate(LocalDate.parse(startsOn, formatter),
				LocalDate.parse(endsOn, formatterUno));
		return wellnessEvent;
	}

	@RequestMapping(path = "/api/update/event/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update Event", notes = "Update existing event by ID")
	public WellnessEvent updateEvent(@PathVariable Integer id, @RequestBody WellnessEvent updatingEvent) {
		// WellnessEvent we = new WellnessEvent();
		// String regex = "[0-9]{5}$";

		WellnessEvent e = wellnessEventRepo.getOne(id);
		if (updatingEvent.getEventName() != null) {
			e.setEventName(updatingEvent.getEventName());
		}
		if (updatingEvent.getDescription() != null) {
			e.setDescription(updatingEvent.getDescription());
		}
		if (updatingEvent.getEndsOn() != null) {
			e.setEndsOn(updatingEvent.getEndsOn());
		}
		if (updatingEvent.getStartsOn() != null) {
			e.setStartsOn(updatingEvent.getStartsOn());
		}
		if (updatingEvent.getLocation() != null) {
			e.setLocation(updatingEvent.getLocation());
		}
		if (updatingEvent.getEventType() != null) {
			e.setEventType(updatingEvent.getEventType());

		}
		if (updatingEvent.getStreetAddress() != null) {
			e.setStreetAddress(updatingEvent.getStreetAddress());
		}
		if (updatingEvent.getCity() != null) {
			e.setCity(updatingEvent.getCity());
		}
		if (updatingEvent.getState() != null) {
			e.setState(updatingEvent.getState());
		}
		if (updatingEvent.getZipCode() != null) {
			e.setZipCode(updatingEvent.getZipCode());
		}
		updatingEvent = new ConsumeResults().getLngLatFromGoogle(updatingEvent);
		e.setLatitude(updatingEvent.getLatitude());
		e.setLongitude(updatingEvent.getLongitude());
		wellnessEventRepo.save(e);
		return e;
	}

	@RequestMapping(path = "/api/resetWellnessEvent", method = RequestMethod.GET)
	public void resetWellnessEvent() {
		LocalDate startUno = LocalDate.now().minusDays(5);
		LocalDate endUno = LocalDate.now().plusDays(10);
		LocalDate startUnos = LocalDate.now().minusDays(2);
		LocalDate endUnos = LocalDate.now().plusDays(12);
		LocalDate startDos = LocalDate.now().minusDays(1);
		LocalDate endDos = LocalDate.now().plusDays(7);
		LocalDate startDoso = LocalDate.now().minusDays(0);
		LocalDate endDoso = LocalDate.now().plusDays(8);
		LocalDate startTres = LocalDate.now().plusWeeks(2);
		LocalDate startTreso = LocalDate.now().plusWeeks(1);
		LocalDate endTres = LocalDate.now().plusWeeks(3);
		LocalDate endTreso = LocalDate.now().plusWeeks(4);

		wellnessEventRepo.save(new WellnessEvent("Joe's Challenge", "Barrington", "Just a 5K", "5K", startUno, startUno,
				"14 Hunter Lane", "Barrington", "NH", "03825", 43.191904, -71.019577));
		wellnessEventRepo.save(new WellnessEvent("Jeb's Challenge", "Fishers", "A 10K of fun", "10K", startDos, startDos,
				"14277 Camelot House Way", "Fishers", "IN", "46037", 39.980659, -85.909026));
		wellnessEventRepo.save(new WellnessEvent("Dave's Challenge", "GreenLand", "Dave's Monthly Challenge", "Step Count",startTres, startTres, 
				"60 Mc Shane Avenue", "GreenLand", "NH", "03840", 43.050105, -70.829194));		
		wellnessEventRepo.save(new WellnessEvent("John's Challenge", "New York", "Step Challenge", "Step Count",startTres, endTres, 
				"725 5th Ave", "New York", "NY", "10022", 40.762374, -73.973912));
		wellnessEventRepo.save(new WellnessEvent("Lucas 10K", "Somwersworth", "Walks on the beach", "10k", startTreso, startTreso,
				"16 Cameron Way", "Somersworth", "NH", "03878", 43.282887, -70.889938));
		wellnessEventRepo.save(new WellnessEvent("Liberty Event", "Dover", "Romantic Run", "5k", endTres, endTres,
				"150 Liberty Way", "Dover", "NH", "03820", 43.209739, -70.912609));
		wellnessEventRepo.save(new WellnessEvent("Ron's Challenge", "Wausau", "Steps Up", "Step Count", startTreso, endTreso,
				"1305 N 14th St", "Wausau", "WI", "54403", 44.968023, -89.608543));
		wellnessEventRepo.save(new WellnessEvent("Terri's Challenge", "Cambridge", "Step Around", "StepCount", startDoso, endDoso,
				"2 Earhart Street", "Cambridge", "MA", "02141", 42.371026, -71.074150));
		wellnessEventRepo.save(new WellnessEvent("Liberty Corporate", "Boston", "Step Fun", "StepCount", startDoso, endTreso,
				"157 Berkeley Street", "Boston", "MA", "02116", 42.349154, -71.071818));
		wellnessEventRepo.save(new WellnessEvent("Liberty Corporate 2nd Round", "Boston", "Step Fun", "StepCount", startTreso, endTreso,
				"157 Berkeley Street", "Boston", "MA", "02116", 42.349154, -71.071818));
		wellnessEventRepo.save(new WellnessEvent("Rural Run", "Hatford City", "5K Run", "5K", endDoso, endDoso,
				"809 North High Street", "Hartford City", "IN", "47348", 40.457519, -85.368778));
		wellnessEventRepo.save(new WellnessEvent("White House Run", "Washhington DC", "10K Run", "10K", startUnos, startUnos,
				"1600 Pennsylvania Avenue ", "Hartford City", "IN", "20006", 38.897663, -77.036574));
		wellnessEventRepo.save(new WellnessEvent("Platform 24", "Carmel", "Open Space Run", "10K", startTres, startTres,
				"12400 N Meridian Street", "Carmel", "IN", "46032", 39.9705529, -86.1596646));
		wellnessEventRepo.save(new WellnessEvent("Gillette Stadium", "Foxborough", "Fan Challenge", "StepCount", startDos, endDos,
				"1 Patriot Place", "Foxborough", "MA", "02035", 42.091188, -71.26688899999999));
		wellnessEventRepo.save(new WellnessEvent("Safeco Mountan Run", "Seattle", "Mt. Rainer", "StepCount", startDoso, endTreso,
				"1001 4th Ave", "Seattle", "WA", "98154", 47.6061304, -122.33384940000002));
		wellnessEventRepo.save(new WellnessEvent("Run with Elvis", "Memphis", "Loose some weight 70's Elvis", "10K", endUno, endUno,
				"Elvis Presley Blvd", "Memphis", "TN", "38116", 35.0302462, -90.02517030000001));
		wellnessEventRepo.save(new WellnessEvent("Run with Prince", "Chanhassen", "Step it out with Prince", "Step Count", startUno, endTreso,
				"7801 Audubon Rd", "Chanhassen", "MN", "55317", 44.8617041, -93.5605883));
	}

	@ApiOperation(value = "Returns a list of all Events")
	@RequestMapping(path = "/api/getAllEvents", method = RequestMethod.GET)
	public List<WellnessEvent> getAllEvents() {
		List<WellnessEvent> wellnessEvent = wellnessEventRepo.findAll();
		return wellnessEvent;
	}

	// @ApiOperation(value = "Returns a list of sorted Events")
	// @RequestMapping(path = "/api/getAllEventsByMemberSorted/{memberID}", method =
	// RequestMethod.GET)
	// public ArrayList<WellnessEvent> getAllEventsSorted(@PathVariable Integer
	// memberID) {
	// ArrayList<WellnessEvent> returnList = new ArrayList<WellnessEvent>();
	// List<WellnessEvent> wellnessEvents = wellnessEventRepo.findAll();
	// Member member = memberRepository.findOne(memberID);
	// Set<WellnessEvent> eventSet = member.getWellnessEvents();
	// for (WellnessEvent registeredWellnessEvent: eventSet) {
	// returnList.add(registeredWellnessEvent);
	// wellnessEvents.remove(registeredWellnessEvent);
	// }
	// for (WellnessEvent unregisteredWellnessEvent: wellnessEvents) {
	// returnList.add(unregisteredWellnessEvent);
	// }
	// return returnList;
	// }

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// add data as necessary to the model...
		return mav;
	}

}
