package com.gura.spring04.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.todo.dao.TodoDao;
import com.gura.spring04.todo.dto.TodoDto;
@Service
public class TodoServiceImpl implements TodoService{
	@Autowired
	private TodoDao dao;
	
	@Override
	public void addTodo(TodoDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateTodo(TodoDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteTodo(int num) {
		dao.delete(num);
	}

	@Override
	public void getTodo(int num, ModelAndView mView) {//request 영역에 담아주는 것 까지 비즈니스로직이다.
		TodoDto dto=dao.getData(num);
		mView.addObject("todo_dto", dto);
	}

	@Override
	public void getListTodo(ModelAndView mView) {
		List<TodoDto> list=dao.getList();
		mView.addObject("todo_list", list);
	}

}
