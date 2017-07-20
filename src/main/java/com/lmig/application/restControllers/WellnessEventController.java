package com.lmig.application.restControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.lmig.application.entities.Medallion;
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
	@RequestMapping(path = "/api/event/{eventName}", method = RequestMethod.GET)
	@ApiOperation(value = "Get Event by Name", notes = "Returns event object matching name")
	public ResponseEntity<List<WellnessEvent>> findByeventName(
			@PathVariable(name = "eventName", required = true) String eventName) {
		System.out.println("/api/event/{eventName} is " + eventName);
		if (eventName == "") {
			return new ResponseEntity<List<WellnessEvent>>(HttpStatus.BAD_REQUEST);
		}
		List<WellnessEvent> event = wellnessEventRepo.findByEventLike(eventName);
		System.out.println("Size of Events" + event.size());
		return new ResponseEntity<List<WellnessEvent>>(event, HttpStatus.OK);

	}

	@RequestMapping(path = "/api/event", method = RequestMethod.POST)
	@ApiOperation(value = "Add an Event", notes = "adds a new event - event name must be in request body and must not already\n"
			+ " exist")
	public ResponseEntity<WellnessEvent> createEvent(@RequestBody WellnessEvent e) {
		System.out.println("/api/event POST " + e.getEventName());
		if (e.getEventName() == null) {
			return new ResponseEntity<WellnessEvent>(HttpStatus.BAD_REQUEST);
		}
		if (wellnessEventRepo.findByEventLike(e.getEventName()) != null) {
			return new ResponseEntity<WellnessEvent>(HttpStatus.CONFLICT);
		}

		wellnessEventRepo.save(e);
		Medallion eventMedallion = new Medallion("test", "test");
		return new ResponseEntity<WellnessEvent>(e, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/api/event/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete Event", notes = "Deletes an existing event whose ID matches parameter in URL")
	public ResponseEntity<String> deleteEvent(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/event/{id} DELETE " + id);
		if (id == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
		wellnessEventRepo.delete(id);
		return new ResponseEntity<String>("Event deleted", HttpStatus.OK);
	}

	@RequestMapping(path = "/api/event", method = RequestMethod.PUT)
	@ApiOperation(value = "Updated Person", notes = "Update/merge for an existing person whose ID matches \"id\" in json body of\n"
			+ " request\n")
	public ResponseEntity<WellnessEvent> updatePerson(@RequestBody WellnessEvent e) {
		System.out.println("/api/event PUT ");
		if (e.getId() == 0) {
			return new ResponseEntity<WellnessEvent>(HttpStatus.BAD_REQUEST);
		}
		WellnessEvent existing = wellnessEventRepo.findOne(e.getId());
		existing.merge(e);
		wellnessEventRepo.save(existing);
		return new ResponseEntity<WellnessEvent>(e, HttpStatus.OK);
	}

	@RequestMapping(path = "/api/resetWellnessEvent", method = RequestMethod.GET)
	public void resetWellnessEvent() {
		wellnessEventRepo.save(new WellnessEvent("Event1", "Date1", "Date2", "Indy", "StepsForever", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event2", "Date2", "Date3", "Indy1", "StepsForever1", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event3", "Date3", "Date4", "Indy2", "StepsForever2", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event4", "Date4", "Date5", "Indy3", "StepsForever3", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event5", "Date5", "Date6", "Indy4", "StepsForever4", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event6", "Date6", "Date7", "Indy5", "StepsForever5", "Community"));
	}

	@ApiOperation(value = "Returns a list of all Events")
	@RequestMapping(path = "/api/getAllEvents", method = RequestMethod.GET)
	public List<WellnessEvent> getAllEvents() {
		System.out.println("I am here");
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
