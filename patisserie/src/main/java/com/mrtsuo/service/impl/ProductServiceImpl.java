package com.mrtsuo.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrtsuo.NotFoundException;
import com.mrtsuo.model.Product;
import com.mrtsuo.repository.ProductRepository;
import com.mrtsuo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	@Override
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Transactional
	@Override
	public Page<Product> listProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	@Override
	public List<Product> listProduct() {
	    return productRepository.findAll();
	}
	

	@Transactional
	@Override
	public Product updateProduct(Long id, Product product) {
		Product p = productRepository.findOne(id);
		    if (p == null) {
		        throw new NotFoundException("不存在該產品");
		    }
		    BeanUtils.copyProperties(product,p);
		return productRepository.save(p);
	}

	@Transactional
	@Override
	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}
}
