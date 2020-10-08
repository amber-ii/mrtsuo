package com.mrtsuo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrtsuo.model.Product;
import com.mrtsuo.service.ProductService;

/**
 * 
 * @author amber
 *
 */
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/product")
	public @ResponseBody String product() {
//		Product product = productService.queryProductById(id);
		return "home";
	}
	
}
