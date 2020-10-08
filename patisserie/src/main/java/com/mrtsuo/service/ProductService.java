package com.mrtsuo.service;

import com.mrtsuo.model.Product;

public interface ProductService {
	/**
	 * 根據產品ID查詢詳情
	 * @param id
	 * @return
	 */

	public Product queryProductById(Integer id);
	
	/**
	 * 根據產品Name查詢詳情
	 * @param pname
	 * @return
	 */
	
	public Product queryProductByName();

}
