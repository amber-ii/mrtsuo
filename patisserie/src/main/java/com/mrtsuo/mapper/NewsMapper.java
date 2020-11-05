package com.mrtsuo.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.News;

public interface NewsMapper extends JpaRepository<News, Long> {
}