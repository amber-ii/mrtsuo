package com.mrtsuo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrtsuo.model.Product;
import com.mrtsuo.model.Type;
import com.mrtsuo.service.ProductService;
import com.mrtsuo.service.TypeService;
import com.mrtsuo.vo.ProductQuery;

/**
 * 
 * @author amber
 *
 */
@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TypeService typeService;

	@GetMapping("/products")
	public String products(
			@PageableDefault(size = 5, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model,ProductQuery prod,Type type) {
		model.addAttribute("type", type);
		model.addAttribute("types", typeService.listType());
		
		model.addAttribute("prod", prod);
		model.addAttribute("page",productService.listProducts(pageable, prod));
		return "admin/products"; 
		
	} 

	@PostMapping("/products/search")
	public String search(
			@PageableDefault(size = 5, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model,ProductQuery prod,Type type) {
		
		model.addAttribute("prod", prod);
		model.addAttribute("page",productService.listProducts(pageable,prod));
		return "admin/products :: productList"; 
		
	} 


	@GetMapping("/products/input")
	public String input(Model model,Type type) {
		model.addAttribute("product", new Product());
		model.addAttribute("type", type);
		model.addAttribute("types", typeService.listType());
		return "admin/products-input";
	}
	
	@GetMapping("/products/{id}/input")
	public String editInput(@PathVariable Long id, Model model,Type type) {
	    model.addAttribute("product", productService.getProduct(id));
		model.addAttribute("type", type);
		model.addAttribute("types", typeService.listType());
	    return "admin/products-input";
	}
	
 
	@PostMapping("/products")
	public String post(@Valid Product product,BindingResult result, RedirectAttributes attributes) {
		Product product1 = productService.getProductByName(product.getName());
	    if (product1 != null) {
	        result.rejectValue("name","nameError","不能添加重複的產品");
	    }
	    if (result.hasErrors()) {
	        return "admin/products-input";
	    }
	    product.setType(typeService.getType(product.getType().getId()));
	    Product p = productService.saveProduct(product);
	    if (p == null ) {
	        attributes.addFlashAttribute("message", "新增失敗");
	    } else {
	        attributes.addFlashAttribute("message", "新增成功");
	    }
	    return "redirect:/admin/products";
	}
	
	@PostMapping("/products/{id}")
	public String editPost(@Valid Product product,BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
	    if (result.hasErrors()) { 
	        return "admin/products-input";
	    }
	    Product p = productService.updateProduct(id, product);
	    if (p == null) {
	        attributes.addFlashAttribute("message", "更新失敗");
	    } else {
	        attributes.addFlashAttribute("message", "更新成功");
	    }
	    return "redirect:/admin/products";
	}
	 
	@GetMapping("/products/{id}/delete")
	public String delete(@PathVariable Long id,RedirectAttributes attributes) {
	    productService.deleteProduct(id);
	    attributes.addFlashAttribute("message", "刪除成功");
	    return "redirect:/admin/products";
	}
	
}