package com.gura.spring05.users.dao;

import javax.servlet.http.HttpSession;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
	public boolean isValid(UsersDto dto);
	public UsersDto getData(String id);
	public void delete(String id);
	public void updatePwd(UsersDto dto);
}
