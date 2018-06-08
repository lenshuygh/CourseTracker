package com.lens.coursetracker.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDuplicateEntryError(Exception exception){
        String errorMessage = exception.getLocalizedMessage();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/errors/duplicateentry");
        modelAndView.addObject("errormessage",errorMessage);
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }


}
