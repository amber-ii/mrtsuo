package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mrtsuo.domain.News;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News>{
}