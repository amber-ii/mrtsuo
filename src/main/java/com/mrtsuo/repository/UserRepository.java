package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findByUsernameAndPassword(String username, String password);
    
}