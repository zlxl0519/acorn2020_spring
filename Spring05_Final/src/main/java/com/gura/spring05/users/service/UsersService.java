package com.gura.spring05.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	public Map<String, Object> isExistId(String inputId);
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session);//mView 에는 처리 결과를 담도록
	public void getInfo(HttpSession session, ModelAndView mView);//아이디 인식을 위해 session, 개인정보를 받기 위해 mView
	public void deleteUser(HttpSession session);
	public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile image);
	public void updateUsers(HttpSession session, UsersDto dto);
	public void pwdUpdate(UsersDto dto, ModelAndView mView, HttpSession session);
}
