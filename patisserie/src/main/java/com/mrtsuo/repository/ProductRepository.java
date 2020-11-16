package com.mrtsuo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mrtsuo.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product>{
	Product findByName(String name);
}