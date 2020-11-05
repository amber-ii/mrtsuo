package com.mrtsuo.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrtsuo.NotFoundException;
import com.mrtsuo.mapper.ProductMapper;
import com.mrtsuo.model.Product;
import com.mrtsuo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Transactional
	@Override
	public Product saveProduct(Product product) {
		return productMapper.save(product);
	}

	@Transactional
	@Override
	public Product getProduct(Long id) {
		return productMapper.getOne(id);
	}

	@Override
	public Product selectProductByName(String name) {
		return productMapper.findByName(name);
	}

	@Transactional
	@Override
	public Page<Product> listProducts(Pageable pageable) {
		return productMapper.findAll(pageable);
	}

	@Transactional
	@Override
	public Product updateProduct(Long id, Product product) {
		Product p = productMapper.getOne(id);
		    if (p == null) {
		        throw new NotFoundException("不存在该类型");
		    }
		    BeanUtils.copyProperties(product,p);
		return productMapper.save(p);
	}

	@Transactional
	@Override
	public void deleteProduct(Long id) {
		productMapper.deleteById(id);
	}
}
