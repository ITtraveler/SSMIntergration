package com.hgs.ssm.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(value={ArithmeticException.class})
	public ModelAndView excepterHandler(Exception ex){
		System.out.println(ex);
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", ex);
		return mav;
	}
}
