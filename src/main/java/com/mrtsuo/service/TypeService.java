package com.mrtsuo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mrtsuo.model.Type;

public interface TypeService {
//	查詢全部
	List<Type> listType();

//	查詢單一
	Type getType(Long id);
}
