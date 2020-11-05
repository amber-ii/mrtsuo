package com.mrtsuo.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.Product;

public interface ProductMapper extends JpaRepository<Product,Long>{
	Product findByName(String name);
}