package com.mrtsuo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mrtsuo.model.Product;

public interface ProductService {
	/**
	 * 儲存產品
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product);
	
	/**
	 * 查詢詳情
	 * 根據產品ID
	 * 
	 * @param id
	 * @return
	 */

	Product getProduct(Long id);

	/**
	 * 查詢詳情
	 * 根據產品Name
	 * 
	 * @param pname
	 * @return
	 */

	Product getProductByName(String name);
	
	/**
	 * 查詢所有產品
	 * @param product
	 * @return
	 */
	Page<Product> listProducts(Pageable pageable);

	
	/**
	 * 更新產品
	 * @param product
	 * @return
	 */
	Product updateProduct(Long id, Product product);

	/**
	 * 刪除產品
	 * @param id
	 */
	void deleteProduct(Long id);

	List<Product> listProduct();



}
