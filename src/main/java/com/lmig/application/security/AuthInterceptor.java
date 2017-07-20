package com.lmig.application.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is your filter aka AuthInterceptor that will look at every request it is added to.. see the SecurityConfig class
 * for how its "added" to a path.  Note I've implemented a time based token.. It WILL expire in 15 minutes and front end will need to
 * catch the 401 code and login again.  This is not ideal.  Some solutions are issue tokens with screenNames in them and let them
 * valid per user for 1 hr.  Or with every request, if authorized, generate a new token and return that in the response as well.
 */
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean authorized = false;

        // they better have included a valid token
        String key = httpServletRequest.getHeader("x-authorization-key");
        if (key != null) {
            authorized = TokenGenerator.isValidKey(key);
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }

        if(!authorized){
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        return authorized;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // this is called after each request but we don't need to do anything here
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // this is called at very end but we don't need to do anything here
    }
}
