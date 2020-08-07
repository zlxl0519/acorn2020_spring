package com.gura.spring02;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendsController {
	@RequestMapping
	public String FriendsList(HttpServletRequest request) {// 메소드는 마음대로 만들수 있다.
		//1. 친구리스트를 얻어오는 비즈니스 로직을 작성한다.
		List<String> list=new ArrayList<String>();
		list.add("김구라");
		list.add("해골");
		list.add("코코");
		//2. request 영역에 담는다.
		request.setAttribute("list", list);
		//3.view 페이지(jsp)로 forward 이동해서 응답한다.
		return "friend/list";
	}
}
