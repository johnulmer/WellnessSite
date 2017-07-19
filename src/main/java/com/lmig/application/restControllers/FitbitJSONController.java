package com.lmig.application.restControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@Api(value="Fitbit", description="JSON operations pertaining to Fitbit connection")
public class FitbitJSONController {
	@RequestMapping(path = "/api/fitbitAPITest", method = RequestMethod.GET)
	public void fitbitAPITest() {	
		System.out.println("calling fitbit method - not working yet");
	}
}
