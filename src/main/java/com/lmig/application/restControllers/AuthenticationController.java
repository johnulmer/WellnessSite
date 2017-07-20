package com.lmig.application.restControllers;

import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

	@Autowired
	private MemberRepository memberRepository;

	@RequestMapping(path = "/open/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Member> login(@RequestParam String email, @RequestParam String password) throws Throwable {
		Member returnedMember = memberRepository.findByEmailAndPassword(email, password);
		if (returnedMember != null) {
			TokenGenerator.genToken();
			return new ResponseEntity<Member>(returnedMember, HttpStatus.OK);
		} else {
		  return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
	}
}
