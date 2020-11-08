package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}