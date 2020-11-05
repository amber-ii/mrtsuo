package com.mrtsuo.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.User;

public interface UserMapper extends JpaRepository<User,Long>{
    
    User findByUsernameAndPassword(String username, String password);
    
}