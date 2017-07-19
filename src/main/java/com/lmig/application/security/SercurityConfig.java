package com.lmig.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This just adds our intercepter to the registry so that it is used on the path(s) we specify.  In our
 * case we are adding to any path that starts with /api/    (note I created the login controller on /open/login
 * so it will not be bothered by this intercepter which is good because you have to be able to get there without logging in
 */
@Configuration

public class SercurityConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // we only perform security on everything under /api  (note we put login under /login so it will be open :D
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/**");
    }
}