package com.mrtsuo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.mrtsuo.domain.Product;
import com.mrtsuo.domain.Type;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	Product findByName(String name);

//	關鍵字搜尋產品(客戶端menu)
	@Query("select p from Product p where p.name like ?1")
	Page<Product> findByQuery(String query, Pageable pageable);

}