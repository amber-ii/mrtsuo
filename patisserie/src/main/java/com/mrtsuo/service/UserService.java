package com.mrtsuo.service;

import com.mrtsuo.model.User;

public interface UserService {
	User checkUser(String username, String password);
	
}
