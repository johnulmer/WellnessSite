package com.lmig.application.restControllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;

@RestController
@Api(value="Fitbit", description="JSON operations pertaining to Fitbit connection")
public class FitbitJSONController {
	@RequestMapping(path = "/api/fitbitAPITest", method = RequestMethod.GET)
	public void fitbitAPITest() {	
		System.out.println("calling fitbit method - not working yet");
	}
	
	@RequestMapping(path = "/api/closeStepEvent", method = RequestMethod.GET)
	public ArrayList<Object> getSteps() {	
		ArrayList<Object> soMuchNews = new ArrayList<Object>();
		soMuchNews.add("calling fitbit method - not working yet");
		RestTemplate restTemplate = new RestTemplate();
		return soMuchNews;
	}

}
