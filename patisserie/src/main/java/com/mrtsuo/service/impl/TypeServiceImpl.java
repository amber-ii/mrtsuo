package com.mrtsuo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mrtsuo.model.Type;
import com.mrtsuo.repository.TypeRepository;
import com.mrtsuo.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeRepository typeRepository;
	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeRepository.findAll(pageable);
	}

	@Override
	public List<Type> listType() {
		return typeRepository.findAll();
	}

	@Override
	public Type getType(Long id) {
		return typeRepository.findOne(id);
	}

	
}
