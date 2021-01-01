package com.mrtsuo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mrtsuo.domain.Type;
import com.mrtsuo.repository.TypeRepository;
import com.mrtsuo.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeRepository typeRepository;

//	查詢全部
	@Override
	public List<Type> listType() {
		return typeRepository.findAll();
	}

//	查詢單一
	@Override
	public Type getType(Long id) {
		return typeRepository.findOne(id);
	}
}
