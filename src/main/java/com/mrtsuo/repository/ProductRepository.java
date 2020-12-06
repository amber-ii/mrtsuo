package com.mrtsuo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.mrtsuo.model.Product;
import com.mrtsuo.model.Type;

public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product>{
	Product findByName(String name);
	@Query("select p from Product p where p.name like ?1")
	Page<Product> findByQuery(String query, Pageable pageable);
	
}