package com.mrtsuo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrtsuo.NotFoundException;
import com.mrtsuo.model.News;
import com.mrtsuo.model.Product;
import com.mrtsuo.model.Type;
import com.mrtsuo.repository.ProductRepository;
import com.mrtsuo.service.ProductService;
import com.mrtsuo.vo.ProductQuery;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

//	根據ID查詢
	@Transactional
	@Override
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}

//  查詢全部、產品名稱關鍵字搜尋(後台)
	@Override
	public Page<Product> listProducts(Pageable pageable, ProductQuery product) {
		return productRepository.findAll(new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!"".equals(product.getName()) && product.getName() != null) {
					predicates.add(cb.like(root.<String>get("name"), "%" + product.getName() + "%"));
				}
				if (product.getTypeId() != null) {
					predicates.add(cb.equal(root.<Type>get("type").get("id"), product.getTypeId()));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

//  商品名稱關鍵字搜尋(客戶端)
	@Override
	public Page<Product> listProducts(String query, Pageable pageable) {
		return productRepository.findByQuery(query, pageable);
	}

//	根據名稱查詢
	@Override
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

//	新增
	@Transactional
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

//	修改
	@Transactional
	@Override
	public Product updateProduct(Long id, Product product) {
		Product findProdId = productRepository.findOne(id);
		if (findProdId == null) {
			throw new NotFoundException("不存在該產品");
		}
		BeanUtils.copyProperties(product, findProdId);
		return productRepository.save(findProdId);
	}

//	刪除
	@Transactional
	@Override
	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}
}