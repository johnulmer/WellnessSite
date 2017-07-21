package com.lmig.application.restControllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.application.entities.Medallion;
import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MedallionRepository;
import com.lmig.application.repositories.MemberRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="medallions", description="JSON operations pertaining to WellnessSite Medallions")
public class MedallionJSONController {
	
@Autowired
private MedallionRepository medallionRepository;

/**
 * Accepts a JSON Medallion object with title and description set, optionally with an event. 
 * returns JSON Medallion object with auto-generated ID.
 *
 * @param  addingMedallion A Medallion object with values for title and description, optionally with an event.
 * @return                 returnedMedallion with ID set
 * @see                    Medallion
 */
//@ApiOperation(value = "Adds a new medallion")
//	@RequestMapping(path = "/api/medallion", method = RequestMethod.POST)
//public Medallion addMedallion(@RequestBody @Valid Medallion addingMedallion) {
//	addingMedallion.setActive(true);
//	Medallion returnedMedallion = medallionRepository.save(addingMedallion);
//	return returnedMedallion;
//}
///**
// * Accepts a JSON Medallion object with id set, sets active to false. 
// * returns JSON Medallion object showing updated active=false.
// *
// * @param  retiringMedallion A Medallion object with .
// * @return                   returnedMember with ID set
// * @see                      Member
// */
//@ApiOperation(value = "Inactivate a medallion")
//	@RequestMapping(path = "/api/inactivateMedallion", method = RequestMethod.PUT)
//public Medallion inactivateMedallion(@RequestBody @Valid Medallion retiringMedallion) {
//	retiringMedallion.setActive(false);
//	Medallion returnedMedallion = medallionRepository.save(retiringMedallion);
//	return returnedMedallion;
//}

}
