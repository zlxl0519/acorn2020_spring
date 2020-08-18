package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	//댓글 목록 얻어오기
	public List<CafeCommentDto> getList(int ref_group);
	//댓글 삭제
	public void delete(int num);
	//댓글 추가
	public void insert(CafeCommentDto dto);
	//추가할 댓글의 글번호를 리턴하는 메소드 //댓글의 글번호를 insert하기 전에 얻어내야지 comment_group 번호와 같은 글번호로 만들기 위해
	public int getSequence();
	//댓글 수정
	public void update(CafeCommentDto dto);
	//댓글 하나의 정보를 리턴하는 메소드
	public CafeCommentDto getData(int num);
}
