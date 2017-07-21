package com.lmig.application.restControllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.lmig.application.entities.Member;
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
	@RequestMapping(path = "/api/eventbyname/{eventName}", method = RequestMethod.GET)
	@ApiOperation(value = "Get Event by Name", notes = "Returns event object matching name")
	public ResponseEntity<List<WellnessEvent>> findByEventName(
			@PathVariable(name = "eventName", required = true) String eventName) {
		if (eventName == "") {
			return new ResponseEntity<List<WellnessEvent>>(HttpStatus.BAD_REQUEST);
		}
		List<WellnessEvent> gevent = wellnessEventRepo.findByEventName(eventName);
		return new ResponseEntity<List<WellnessEvent>>(gevent, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Returns event matching ID", notes = "requires path variable ID")
	@RequestMapping(path = "/api/eventbyid/{id}", method = RequestMethod.GET)
	public WellnessEvent getEventByID(@PathVariable Integer id) {
		return wellnessEventRepo.getOne(id);
	}

	@RequestMapping(path = "/api/add/event", method = RequestMethod.POST)
	@ApiOperation(value = "Add an Event", notes = "adds a new event - event name must be in request body and must not already")
	public ResponseEntity<WellnessEvent> createWellnessEvent(@RequestBody WellnessEvent eventBody) {
//		if (e.getEventName() == null) {
//			return new ResponseEntity<WellnessEvent>(HttpStatus.BAD_REQUEST);
//		}
//		if (wellnessEventRepo.findByEventName(e.getEventName()) != null) {
//			return new ResponseEntity<WellnessEvent>(HttpStatus.CONFLICT);
//		}
		wellnessEventRepo.save(eventBody);
		return new ResponseEntity<WellnessEvent>(eventBody, HttpStatus.CREATED);
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

	@RequestMapping(path = "/api/update/event/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update Event", notes = "Update existing event by ID")
	public WellnessEvent updateEvent(@PathVariable Integer id, @RequestBody WellnessEvent updatingEvent) {
		WellnessEvent e = wellnessEventRepo.getOne(id);
		if (updatingEvent.getEventName() != null) {
			e.setEventName(updatingEvent.getEventName());
		}
		if (updatingEvent.getDescription()!= null){
			e.setDescription(updatingEvent.getDescription());
		}
		if (updatingEvent.getEndDate() !=null) {
			e.setEndDate(updatingEvent.getEndDate());
		}
		if (updatingEvent.getStartDate() != null) {
			e.setStartDate(updatingEvent.getStartDate());
		}
		if (updatingEvent.getLocation() != null) {
			e.setLocation(updatingEvent.getLocation());
		}
		if (updatingEvent.getEventType() != null) {
			e.setEventType(updatingEvent.getEventType());
		}
			wellnessEventRepo.save(e);
			return e;
	}
	
	

	
	
	@RequestMapping(path = "/api/resetWellnessEvent", method = RequestMethod.GET)
	public void resetWellnessEvent() {
		
		wellnessEventRepo.save(new WellnessEvent("Event1", null, null, "Indy", "StepsForever", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event2", null, null, "Indy1", "StepsForever1", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event3", null, null, "Indy2", "StepsForever2", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event4", null, null, "Indy3", "StepsForever3", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event5", null, null, "Indy4", "StepsForever4", "Community"));
		wellnessEventRepo.save(new WellnessEvent("Event6", null, null, "Indy5", "StepsForever5", "Community"));
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
