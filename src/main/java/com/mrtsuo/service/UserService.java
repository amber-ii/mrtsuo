package com.mrtsuo.service;

import com.mrtsuo.model.User;

public interface UserService {
//	登入驗證
	User checkUser(String username, String password);
}
