package com.gura.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	@Autowired
	SqlSession session;

	@Override
	public List<CafeCommentDto> getList(int ref_group) {
		
		return session.selectList("cafeComment.getList", ref_group);
	}

	@Override
	public void delete(int num) {
		session.update("cafeComment.delete", num);//삭제 여부를 no 에서 yes 로 바꾸기만 함.
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert", dto);
	}

	@Override
	public int getSequence() {
		
		return session.selectOne("cafeComment.getSequence");
	}

	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update", dto);
	}

}
