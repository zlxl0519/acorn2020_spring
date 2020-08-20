package com.seung.spring05.users.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seung.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	UsersService usersService; 
	
	@RequestMapping("/users/signup_form.do")
	public String signup_form() {
		
		return "users/signup_form";
	}
	
	@RequestMapping("/users/loginform.do")
	public String loginform() {
		
		return "users/logintform";
	}
}
