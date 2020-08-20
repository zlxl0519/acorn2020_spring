package com.seung.spring05.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seung.spring05.users.dao.UsersDao;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	UsersDao usersDao;
}
