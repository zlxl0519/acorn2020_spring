package com.gura.spring05.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class TimeAspect {
	// file.controller 에 있는 모든 클래스의  아무 타입을 리턴하는 인자로받는것은 아무거나 상관없는 모든 메소드 가 point cut
	@Around("execution(* com.gura.spring05.file.controller.*.*(..))")
	public Object checkTime(ProceedingJoinPoint joinPoint) throws Throwable {
		//로그를 출력할 객체의 참조값 얻어오기
		//jointPoint.getTarget().getClass() => aop 가 적용된 클래스의 type (FileController.class)
		Logger logger=LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info("요청처리 시작!");
		//시작시간(ms밀리초)
		long startTime=System.currentTimeMillis();
		
		Object obj=joinPoint.proceed();// aop 가 적용된 메소드 수행하기 (적용된 메소드의 리턴값(mView)이 여기에 들어간다.)
		//끝시간(ms)
		long endTime=System.currentTimeMillis();
		//시작과 끝의 차이를 얻어낸다.
		long time=endTime-startTime;
		logger.info("time:"+time);
		logger.info("요청처리 끝!");
		
		return obj;
		
		//ModelAndView mView=null;
		//if(obj instanceof ModelAndView) {
		//	mView=(ModelAndView)obj;
		//	mView.setViewName("redirect:/home.do");
		//}
		
		//return mView;//ViewName 을 조작한 다음에 리턴한것을 Spring framework 가 인식해서 이걸로 리턴해준다.
	}
}
