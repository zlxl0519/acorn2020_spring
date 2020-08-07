package com.gura.spring04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

@Controller
public class MemberController {
	
	//의존객체를 주입 받는다.(DI)
	@Autowired
	private MemberDao dao;
	
	//회원 삭제 요청 처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {//@RequestParam(defaultValue = "") 은 디폴트 값을 지정할수 잇다 정보가 안넘어올때 사용.
		dao.delete(num);
		//리다일렉트 응답
		return "redirect:/member/list.do";
	}
	
	//POST 방식 /member/update 요청 처리
	@RequestMapping(value = "/member/update" , method = RequestMethod.POST)
	public String update(@ModelAttribute MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	//회원정보 수정 폼 요청 처리
	// GET 방식 /member/updateform 요청 처리 // 좀더 정확하게 써놓은것 post 방식이면 반응하지 않는다.
	@RequestMapping(value = "/member/updateform", method = RequestMethod.GET)
	public ModelAndView updateform(@RequestParam int num, ModelAndView mView) {//get parameter 로 넘어온다.
		//dao 를 이용해서 수정할 회원의 정보를 얻어온다.
		MemberDto dto=dao.getData(num);
		//model 을 ModelAndView 객체에 담는다.
		mView.addObject("dto", dto);
		//view 페이지 정보를 ModelAndView 객체에 담는다.
		mView.setViewName("member/updateform");
		//ModelAndView 객체를 리턴해준다.
		return mView;
	}
	
	//회원 추가 로직
	@RequestMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto) {//전송되는 parameter는 dto 를 선언하면 가져온다.
		//회원 정보를 DB 에 저장하고
		dao.insert(dto); //dto 에 들어가려면 dto 에 필드명과 parameter 명이 같아야 된다.
		//view page 로 forward 이동해서 응답
		return "member/insert";
	}
	
	//회원추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		//수행할 비즈니스 로직은 현재 없다.
		
		return "member/insertform";
	}
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//회원목록을 얻어온다.
		List<MemberDto> list=dao.getList();
		//model 을 담는다
		mView.addObject("list", list);
		//view 페이지 정보를 담고 
		mView.setViewName("member/list");
		//ModelAndView 객체를 리턴해 준다.
		return mView;
	}
}
