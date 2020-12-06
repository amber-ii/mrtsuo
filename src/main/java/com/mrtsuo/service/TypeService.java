package com.mrtsuo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mrtsuo.model.Type;

public interface TypeService {
	Page<Type> listType(Pageable pageable);
	List<Type> listType();
	Type getType(Long id);
}
