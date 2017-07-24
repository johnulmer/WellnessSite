package com.lmig.application.activityEntities;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FitBitSteps {
	
    public FitBitSteps() {
    }
    @JsonProperty("activities-log-steps")
    private HashMap<Date, int> activities_log_steps;

}
