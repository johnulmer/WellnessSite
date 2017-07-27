package com.lmig.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SercurityConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// we only perform security on everything under /api (note we put login under
		// /login so it will be open
		// registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/**");
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/findMember");
		// registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/dir");

	}
}