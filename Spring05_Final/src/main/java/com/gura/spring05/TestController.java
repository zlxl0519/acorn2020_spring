package com.gura.spring05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.service.UsersService;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class TestController {
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/api/get_info")
	@ResponseBody
	public JSONPObject jsonp(@RequestParam(defaultValue = "callback") String callback) {
		//위에 @RequestParam 으로 아무것도 안보내면 디폴트 값으로 callback 을 갖는다는의미 //callback 은 묵시적으로 정해져있다.
		//클라이언트에게 응답할 데이터를 Map 에 담는다.
		Map<String, Object> map=new HashMap<>();
		map.put("num", 1);
		map.put("name", "김구라");
		//JSONPObject 객체를 생성해서 콜백 함수명과 응답할 데이터를 담고
		JSONPObject jp=new JSONPObject(callback, map);
		//JSONPObject 를 리턴해준다.
		return jp;
	}
	
	/*@RequestMapping(value = "/api/jsonp_login", method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonpLogin(UsersDto dto, HttpSession session, @RequestParam(defaultValue = "callback") String callback) {
		
		//로그인 성공인 경우 {"isSuccess": true, "id":"gura"}
		//로그인 실패인 경우 {"isSuccess":false}
		Map<String, Object> map=usersService.ajaxLoginProcess(dto, session);
		//JSONPObject 로 응답하기
		JSONPObject jo=new JSONPObject(callback, map);
		return jo;
	}*/
	
	@RequestMapping(value = "/api/jsonp_login", method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonpLogin(UsersDto dto, @RequestParam(defaultValue = "callback") String callback) {
		
		//유효한 정보인지 여부를 얻어온다.
		boolean isValid=usersService.jsonpLogin(dto);
		//유효한지 여부를 Map 에 담고
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isValid", isValid);
		//JSONPObject 로 응답하기
		JSONPObject jo=new JSONPObject(callback, map);
		return jo;
	}
}
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;


@Controller
public class TestController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/react/send")
	@ResponseBody
	public Map<String, Object> sendMessage(@RequestParam String msg){
		// msg=xxx  파라미터 추출된 내용 콘솔창에 출력하기 
		System.out.println(msg);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put("msg", "ajax 전송 잘 받았어~ 클라이언트야");
		return map;
	}
	
	@RequestMapping("/api/jsonp_login")
	@ResponseBody
	public JSONPObject jsonpLogin(String callback, UsersDto dto) {
		//유효한 정보인지 여부를 얻어온다. 
		boolean isValid=usersService.jsonpLogin(dto);
		//유효한지 여부를 Map 에 담고
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isValid", isValid);
		//JSONPObject 객체에 담아서 
		JSONPObject jp=new JSONPObject(callback, map);
		return jp; //리턴해준다. 
	}
	
	
	@RequestMapping("/api/get_info")
	@ResponseBody
	public JSONPObject jsonp(@RequestParam(defaultValue="callback") String callback) {
		//클라이언트에게 응답할 데이터를 Map 에 담는다.
		Map<String, Object> map=new HashMap<>();
		map.put("num", 1);
		map.put("name", "김구라");
		//JSONPObject 객체를 생성해서 콜백 함수명과 응답할 데이터를 담고 
		JSONPObject jp=new JSONPObject(callback, map);
		//JSONPObject 를 리턴해준다. 
		return jp;
	}
}






















>>>>>>> refs/remotes/upstream/master
