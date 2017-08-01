package com.lmig.application.fitbit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

//@SpringBootApplication
//@EnableOAuth2Sso
//@RestController
//@EnableWebSecurity
public class FitbitOAuth extends WebSecurityConfigurerAdapter {

//	@Autowired
//	OAuth2RestTemplate fitbitOAuthRestTemplate;
//
//	@Value("${fitbit.api.resource.activitiesUri}")
//	String fitbitActivitiesUri;
//
//	@RequestMapping("/lifetime-activity")
//	public LifetimeActivity lifetimeActivity() {
//		LifetimeActivity lifetimeActivity;
//
//		try {
//			Activity a = fitbitOAuthRestTemplate.getForObject(fitbitActivitiesUri, Activity.class);
//			lifetimeActivity = a.getLifetime().getTotal();
//		}
//		catch(Exception e) {
//			lifetimeActivity = new LifetimeActivity();
//		}
//
//		return lifetimeActivity;
//	}
//	
//	@RequestMapping("/user")
//	public Principal user(Principal principal) {
//		return principal;
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
//				.authenticated();
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(FitbitOAuth.class, args);
//	}

}
