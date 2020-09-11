package com.gura.spring05.users.controller;

import java.net.URLEncoder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.dto.UsersDto;
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
	
	//아이디가 존재하는지 여부를 처리하는 요청처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){//Service 에 파라미터를 인자를 통해서 보내고 Service 에서 Map 을 리턴해준다.
		//service 가 리턴해주는 Map 객체를 리턴한다.
		return service.isExistId(inputId);
	}
	
	//회원 가입 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		//service 객체를 이용해서 사용자 정보를 추가 한다.
		service.addUser(dto);
		// view 페이지로 forward 이동해서 응답하기
		mView.setViewName("users/signup");
		return mView;
	}
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		// url 파라미터가 넘어오는지 읽어와 보기
		String url=request.getParameter("url");
		if(url==null){//목적지 정보가 없다면
			String cPath=request.getContextPath();
			url=cPath+"/home.do";//로그인후 인덱스 페이지로 가도록 하기 위해
		}
		//request 에 담는다.
		request.setAttribute("url", url);
		return "users/loginform";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {//Service 에 dto, mView, HttpSession 값을 넘겨 주어야 된다.
		//로그인후 가야하는 목적지 정보
		String url=request.getParameter("url");
		//목적지 정보도 미리 인코딩 해 놓는다.
		String encodedUrl=URLEncoder.encode(url);
		//view 페이지에 전달하기 위해 ModelAndView 객체에 담아준다.
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		//service 객체를 이용해서 로그인 관련 처리를 한다.
		service.loginProcess(dto, mView, session);
		mView.setViewName("users/login");
		return mView;
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
	}
	
	//개인정보보기 요청 처리
	@RequestMapping("/users/private/info")
	public ModelAndView info(HttpServletRequest request, ModelAndView mView) {//아이디는 session으로 넘겨주던 request로 넘겨주던 dto 로 넘겨주던 선택
		service.getInfo(request.getSession(), mView); //request 에서 session 을 받아서 사용할수 있다.
		mView.setViewName("users/private/info"); // /WEB-INF/views/ users/priate/info.jsp
		return mView;
	}
	
	@RequestMapping("/users/private/delete")
	public ModelAndView delete(HttpServletRequest request, ModelAndView mView) {
		//서비스를 이용해서 사용자 정보를 삭제하고
		service.deleteUser(request.getSession());
		//view 페이지로 forward 이동해서 응답
		mView.setViewName("users/private/delete");
		return mView;
	}
	//회원정보 수정폼 요청 처리
	@RequestMapping("/users/private/updateform")
	public ModelAndView updateForm(HttpServletRequest request, ModelAndView mView) {
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/updateform");
		return mView;
	}
	
	// ajax 프로필 사진 업로드 요청 처리
	@RequestMapping("/users/private/profile_upload")
	@ResponseBody
	public Map<String, Object> profile_upload(HttpServletRequest request, @RequestParam MultipartFile image){
		//service 객체를 이용해서 이미지를 upload 폴더에 저장하고 Map 을 리턴 받는다.
		Map<String, Object> map=service.saveProfileImage(request, image);
		//{"imageSrc":"/upload/xxx.jpg"} 형식의 JSON 문자열을 출력하기 위해
		//Map 을 @ResponseBody 로 리턴해준다.
		return map;
	}
	
	//개인 정보 수정 반영 요청 처리
	@RequestMapping("/users/private/update")
	public ModelAndView update(HttpServletRequest request, UsersDto dto, ModelAndView mView) {
		//service 객체를 이용해서 개인정보를 수정한다.
		service.updateUser(request.getSession(), dto);
		//개인 정보 보기 페이지로 리다일렉트 이동한다.
		mView.setViewName("redirect:/users/private/info.do");
		return mView;
	}
	
	@RequestMapping("/users/private/pwd_updateform")
	public ModelAndView pwdUpdateform(ModelAndView mView) {
		
		mView.setViewName("users/private/pwd_updateform");
		return mView;
	}
	
	@RequestMapping("/users/private/pwd_update")
	public ModelAndView pwdUpdate(ModelAndView mView, UsersDto dto, HttpServletRequest request) {
		service.updateUserPwd(dto, mView, request.getSession());
		//view 페이지로 forward 이동해서 응답
		mView.setViewName("users/private/pwd_update");
		return mView;
	}
	
	@RequestMapping("/users/ajax_login_check")
	@ResponseBody
	public Map<String, Object> ajaxLoginCheck(HttpSession session){
		//세션에서 id 라는 키값으로 저장된 문자열을 읽어온다. 없으면 null
		String id=(String)session.getAttribute("id");
		//결과를 Map 에 담고
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return map;
	}
	@RequestMapping(value = "/users/ajax_login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxLogin(UsersDto dto, HttpSession session){
		//서비스가 리턴해주는 Map 객체를 리턴하면 json 문자열이 응답된다.
		//로그인 성공인 경우 {"isSuccess": true, "id":"gura"}
		//로그인 실패인 경우 {"isSuccess":false}
		return service.ajaxLoginProcess(dto, session);
	}
	@RequestMapping("/users/ajax_logout")
	@ResponseBody
	public Map<String, Object> ajaxLogout(HttpSession session) {
		session.invalidate();
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	@RequestMapping(value = "/users/jsonp_login", method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonpLogin(UsersDto dto, HttpSession session, @RequestParam(defaultValue = "callback") String callback) {
		
		//로그인 성공인 경우 {"isSuccess": true, "id":"gura"}
		//로그인 실패인 경우 {"isSuccess":false}
		Map<String, Object> map=service.ajaxLoginProcess(dto, session);
		//JSONPObject 로 응답하기
		JSONPObject jo=new JSONPObject(callback, map);
		return jo;
	}
	@RequestMapping(value = "/users/jsonp_logout", method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonpLogout(HttpSession session, @RequestParam(defaultValue = "callback") String callback) {
		session.invalidate();
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		JSONPObject jo=new JSONPObject(callback, map);
		return jo;
	}
}
