package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrtsuo.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	Product findByName(String name);
}