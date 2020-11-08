package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findByUsernameAndPassword(String username, String password);
    
}