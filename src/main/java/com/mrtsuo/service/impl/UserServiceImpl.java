package com.mrtsuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrtsuo.model.User;
import com.mrtsuo.repository.UserRepository;
import com.mrtsuo.service.UserService;
import com.mrtsuo.util.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userMapper;

	@Override
	public User checkUser(String username, String password) {
		User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
		return user;
	}

}
