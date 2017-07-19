package com.lmig.application.restControllers;

import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MemberRepository;
import com.lmig.application.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AuthenticationController {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(path = "/open/login/", method = RequestMethod.POST)
    public String login(@RequestParam String screenName, @RequestParam String password) throws Throwable {
        Member returnedMember = memberRepository.findByScreenNameAndPassword(screenName, password);
        if(returnedMember != null){
            return TokenGenerator.genToken();
        } else {
            return null;
        }
    }
}
