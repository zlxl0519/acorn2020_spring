package com.gura.spring05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.service.UsersService;

@Controller
public class TestController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/react/send")
	@ResponseBody
	public Map<String, Object> sendMessage(@RequestParam String msg){
		// msg=xxx 파라미터 추출된 내용 콘솔창에 출력하기
		System.out.println(msg);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put("msg", "ajax 전송 잘 받았어~ 클리아언트야");
		return map;
	}
	
	
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
}
