package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.domain.News;

public interface FileDBRepository extends JpaRepository<News, String> {

}