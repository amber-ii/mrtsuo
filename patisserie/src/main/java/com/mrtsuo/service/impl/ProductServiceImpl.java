package com.mrtsuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrtsuo.mapper.ProductMapper;
import com.mrtsuo.model.Product;
import com.mrtsuo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Product queryProductById(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public Product queryProductByName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
