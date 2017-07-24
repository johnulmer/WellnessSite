package com.lmig.application.jsonResponses;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lmig.application.entities.WellnessEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberRegistrationUpdate {

	public Set<WellnessEvent> removeEventList;
	public Set<WellnessEvent> addEventList;
}
