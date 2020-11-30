package com.mrtsuo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mrtsuo.model.News;
import com.mrtsuo.model.Product;
import com.mrtsuo.model.Type;
import com.mrtsuo.service.NewsService;
import com.mrtsuo.service.ProductService;
import com.mrtsuo.service.TypeService;
import com.mrtsuo.vo.ProductQuery;

@Controller
public class IndexController {

	@Autowired
	private ProductService productService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private TypeService typeService;

	@GetMapping("/product")
	public String product(
			@PageableDefault(size = 12, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model, Product product, News news, Type type) {
		model.addAttribute("page", productService.listProducts(pageable));
		model.addAttribute("types", typeService.listType());
		model.addAttribute("pro", productService.listProduct());
		return "product";
	}

	@GetMapping("/product/types/{id}")
	public String type(@PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model, @PathVariable Long id) {
		List<Type> types = typeService.listType();
		if (id == -1) {
			id = types.get(0).getId();
		}
		ProductQuery prodQuery = new ProductQuery();
		prodQuery.setTypeId(id);
		model.addAttribute("name", typeService.getType(id).getName());
		model.addAttribute("types", types);
		model.addAttribute("page", productService.listProducts(pageable, prodQuery));
		return "types";
	}
//最新消息頁面
	@GetMapping("/newspage")
	public String newspage(
			@PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model, News news) {
		model.addAttribute("page", newsService.listNews(pageable));
		return "newspage";
	}


// 單一最新消息畫面
	@GetMapping("/newspage/{id}")
	public String newspageByid(Pageable pageable, Model model, News news, @PathVariable Long id) {
		model.addAttribute("newsone", newsService.getNews(id));
		return "newspageone";
	}

//	搜尋產品
	@PostMapping("/product/search")
	public String search(
			@PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam String query, Model model) {
		model.addAttribute("page", productService.listProducts("%" + query + "%", pageable));
//		將查詢結果返回
		model.addAttribute("query", query);
		return "search";

	}

	@GetMapping("/product/{id}")
	public String product() {
		return "product";
	}

//首頁
	@GetMapping("/")
	public String home() {
		return "home";
	}

//門市資訊
	@GetMapping("/store")
	public String store() {
		return "store";
	}
}