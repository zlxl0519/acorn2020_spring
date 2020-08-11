package com.gura.spring05.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	//회원 가입 폼 요청 처리
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		
		// /WEB-INF/views/users/signup_form.jsp 페이지로 forward 이동해서 응답
		return "users/signup_form"; //요청처리 경로와 꼭 같게 쓸필요는 없다.(WEB-INF 안에서는 굳이 폴더를 만들지 않아도 된다.)확인하기 쉽도록 비슷하게 만든것
	}
}
