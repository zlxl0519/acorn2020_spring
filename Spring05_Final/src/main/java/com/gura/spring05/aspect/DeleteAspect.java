package com.gura.spring05.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.NotDeleteException;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Aspect
@Component
public class DeleteAspect {
	
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CafeDao cafeDao;
	
	@Around("execution(void com.gura.spring05.cafe.service.*.deleteContent(..))")
	public void checkCafeDelete(ProceedingJoinPoint joinPoint) throws Throwable {
<<<<<<< HEAD
		//인자로 받는 값 넣을 지역변수
		int num=0;
		HttpServletRequest request=null;
		// 인자로 받은 값 가져오기
		Object[] args=joinPoint.getArgs();
		for(Object tmp:args) {
			if(tmp instanceof Integer) {
				num=(int)tmp;
			}
			if(tmp instanceof HttpServletRequest) {
				request=(HttpServletRequest)tmp;
			}
		}
		//삭제할 글 정보를 얻어온다.
		CafeDto dto=cafeDao.getData(num);
		//세션에 저장된 아이디를 읽어온다(로그인된 아이디)
		String id=(String)request.getSession().getAttribute("id");
		if(! id.equals(dto.getWriter())) {
			throw new NotDeleteException("남의 글을 지우지 마세요!!");
		}
		//메소드 정상 수행하기
		joinPoint.proceed();
	}
	
	//file. service .안에 있는 모든 클래스의 delete 로 시작하는 인자는 아무거나 상관없는 메소드에 point cut
	@Around("execution(void com.gura.spring05.file.service.*.delete*(..))")
	public void checkFileDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자값을 저장할 지역 변수
		int num=0;
		HttpServletRequest request=null;
		//메소드에 전달되 인자의 원하는 타입을 for 문을 이용해서 가져와서 사용한다.
		Object[] args=joinPoint.getArgs();
		for(Object tmp:args) {
			if(tmp instanceof Integer) { // 전달된 인자중에서 정수(int) 찾기
				num=(int)tmp;
			}
			if(tmp instanceof HttpServletRequest) {//HttpServletRequest 찾기
				request=(HttpServletRequest)tmp;
			}
		}
		//삭제할 파일의 정보를 얻어온다.
		FileDto fileDto=fileDao.getData(num);
		//세션에서 저장된 아이디를 읽어온다(로그인된 아이디)
		String id=(String)request.getSession().getAttribute("id");
		if(! id.equals(fileDto.getWriter())) {
			throw new NotDeleteException("남의 파일 지우기 없기!"); // exception class 로 띄어 버려서 어차피 메소드가 수행이 안되므로 return 필요 없음
		}
		//메소드 정상 수행하기
		joinPoint.proceed();
	}
}
=======
		//메소드에 전달된 인자값을 저장할 지역 변수 
		int num=0;
		HttpServletRequest request=null;
		
		Object[] args=joinPoint.getArgs();
		for(Object tmp:args) {
			if(tmp instanceof Integer) { //전달된 인자중에서 정수(int) 찾기
				num=(int)tmp;
			}
			if(tmp instanceof HttpServletRequest) {//HttpServletRequest 찾기
				request=(HttpServletRequest)tmp;
			}
		}
		//삭제할 글 정보를 얻어온다.
		CafeDto cafeDto=cafeDao.getData(num);
		//세션에 저장된 아이디를 읽어온다(로그인된 아이디)
		String id=(String)request.getSession().getAttribute("id");
		if(!id.equals(cafeDto.getWriter())) {
			throw new NotDeleteException("남의 글 지우기 없기!");
		}
		//메소드 정상 수행하기 
		joinPoint.proceed();		
	}
	
	@Around("execution(void com.gura.spring05.file.service.*.delete*(..))")
	public void checkFileDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자값을 저장할 지역 변수 
		int num=0;
		HttpServletRequest request=null;
		
		Object[] args=joinPoint.getArgs();
		for(Object tmp:args) {
			if(tmp instanceof Integer) { //전달된 인자중에서 정수(int) 찾기
				num=(int)tmp;
			}
			if(tmp instanceof HttpServletRequest) {//HttpServletRequest 찾기
				request=(HttpServletRequest)tmp;
			}
		}
		//삭제할 파일의 정보를 얻어온다.
		FileDto fileDto=fileDao.getData(num);
		//세션에 저장된 아이디를 읽어온다(로그인된 아이디)
		String id=(String)request.getSession().getAttribute("id");
		if(!id.equals(fileDto.getWriter())) {
			throw new NotDeleteException("남의 파일 지우기 없기!");
		}
		//메소드 정상 수행하기 
		joinPoint.proceed();
	}
}






>>>>>>> refs/remotes/upstream/master
