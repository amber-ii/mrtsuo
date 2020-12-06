package com.mrtsuo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mrtsuo.model.Product;
import com.mrtsuo.model.Type;
import com.mrtsuo.service.ProductService;
import com.mrtsuo.service.TypeService;
import com.mrtsuo.vo.ProductQuery;

public class TypeShowController {
	@Autowired
	private TypeService typeService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/types/{id}")
	public String type(@PageableDefault(size = 8, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model, Product product, Type type, @PathVariable Long id) {
		List<Type> types = typeService.listType();
//		if(id == -1) {
//			id = types.get(0).getId();
//		}
		ProductQuery prodQuery = new ProductQuery();
		prodQuery.setTypeId(id);
		model.addAttribute("types", types);
		model.addAttribute("page", productService.listProducts(pageable, prodQuery));
		return "types";

	}
}
