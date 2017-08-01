package com.lmig.application.restControllers;

import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MemberRepository;
//import com.lmig.application.security.TokenGenerator;
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
	public ResponseEntity<Member> login(@RequestBody Member l) throws Throwable {
		Member returnedMember = memberRepository.findByEmailAndPassword(l.getEmail(),l.getPassword());
		if (returnedMember != null) {
			//TokenGenerator.genToken();
			return new ResponseEntity<Member>(returnedMember, HttpStatus.OK);
		} else {
		  return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(path = "/open/login/new/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Member> loginA(@RequestBody Member j) throws Throwable {
		System.out.println("Logging in user " + j.getEmail());
		if (j.getEmail() == null) {
			return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
		}
		
		if (j.getPassword() == null) {
			return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
		}
		Member member2 = new Member(j.getEmail(),j.getPassword());
		Member member = memberRepository.checkCredentials(member2.getEmail(), member2.getEncPassword());
		if (member != null) {
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		}
		return new ResponseEntity<Member>(HttpStatus.UNAUTHORIZED);
	}
	
}
