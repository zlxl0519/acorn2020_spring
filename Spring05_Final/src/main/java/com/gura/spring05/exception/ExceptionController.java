package com.gura.spring05.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // ControllerAdvice 도 Bean 이 된다.
public class ExceptionController {
	
	@ExceptionHandler(NotDeleteException.class)//이런 타입의 Exception 이 발생하면 실행순서가 여기로 옮겨진다.
	public ModelAndView notDelete(NotDeleteException nde) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", nde); //exception 키값으로 인자로 받은 exception 을 담았다.
		mView.setViewName("error/info");
		return mView;
	}
}
