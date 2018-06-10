package com.lens.coursetracker.configuration;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyTagCreationFromCourseInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("CreateTagFromCourseForm") == null){
            session.setAttribute("CreateTagFromCourseForm",false);
        }
        return true;
    }
}
