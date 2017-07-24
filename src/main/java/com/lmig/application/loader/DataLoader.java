package com.lmig.application.loader;

import com.lmig.application.entities.Member;
import com.lmig.application.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataLoader {

    private MemberRepository memberRepository;

    @Autowired
    public DataLoader(MemberRepository userRepository) {
        this.memberRepository = userRepository;
        //LoadUsers();
    }

    private void LoadUsers() {
        memberRepository.save(new Member("john1", "john1@blah.com", "pwd", true, true));
        memberRepository.save(new Member("john2", "john2@blah.com", "pwd", true, false));
        memberRepository.save(new Member("john3", "john3@blah.com", "pwd", true, true));
        memberRepository.save(new Member("john4", "john4@blah.com", "pwd", true, true));
        memberRepository.save(new Member("john5", "john5@blah.com", "pwd", true, false));
        memberRepository.save(new Member("john6", "john6@blah.com", "pwd", true, false));
    }
}