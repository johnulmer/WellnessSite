package com.lmig.application.restControllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.lmig.application.entities.WellnessEvent;
import com.lmig.application.repositories.WellnessEventRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Wellness Events")

public class WellnessEventController implements Controller {

	@Autowired
	private WellnessEventRepo wellnessEventRepo;

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
		//WellnessEvent we = new WellnessEvent();
        //String regex = "[0-9]{5}$";
        
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
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		LocalDate d = LocalDate.now().minusWeeks(1);
		double l = 39.980659;
		double lo = -85.909026;

		wellnessEventRepo.save(new WellnessEvent("Event2", "Indy1", "StepsForever1", "5K", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
		wellnessEventRepo.save(new WellnessEvent("Event2", "Indy1", "StepsForever1", "10K", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
		wellnessEventRepo.save(new WellnessEvent("Event3", "Indy2", "StepsForever2", "Step Count", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
		wellnessEventRepo.save(new WellnessEvent("Event4", "Indy3", "StepsForever3", "Step Count", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
		wellnessEventRepo.save(new WellnessEvent("Event5", "Indy4", "StepsForever4", "10k", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
		wellnessEventRepo.save(new WellnessEvent("Event6", "Indy5", "StepsForever5", "5k", d, tomorrow,
				"14277 Camelot House Way", "Fishers", "IN", "46037", l, lo));
	}

	@ApiOperation(value = "Returns a list of all Events")
	@RequestMapping(path = "/api/getAllEvents", method = RequestMethod.GET)
	public List<WellnessEvent> getAllEvents() {
		List<WellnessEvent> wellnessEvent = wellnessEventRepo.findAll();
		return wellnessEvent;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// add data as necessary to the model...
		return mav;
	}

}
