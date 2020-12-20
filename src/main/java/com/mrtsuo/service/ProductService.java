package com.mrtsuo.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mrtsuo.model.Product;
import com.mrtsuo.vo.ProductQuery;

public interface ProductService {

	/**
	 * 查詢單一商品
	 */
	Product getProduct(Long id);

	/**
	 * 查詢全部、根據商品Name關鍵字搜尋
	 */
	Product getProductByName(String name);

	/**
	 * 商品名稱關鍵字搜尋(後台)
	 * @param pageable
	 * @param product
	 */
	Page<Product> listProducts(Pageable pageable, ProductQuery product);

	/**
	 * 商品名稱關鍵字搜尋(客戶端Menu)
	 * 
	 * @param query
	 * @param pageable
	 */
	Page<Product> listProducts(String query, Pageable pageable);

	/**
	 * 儲存商品
	 */
	Product saveProduct(Product product);

	/**
	 * 修改商品
	 */
	Product updateProduct(Long id, Product product);

	/**
	 * 刪除商品
	 */
	void deleteProduct(Long id);

}
