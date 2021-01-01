package com.mrtsuo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrtsuo.domain.Product;
import com.mrtsuo.domain.Type;
import com.mrtsuo.service.ProductService;
import com.mrtsuo.service.TypeService;
import com.mrtsuo.vo.ProductQuery;

@Controller
@RequestMapping("/admin")
public class ProductController {
	/**
	 * 商品(後台)
	 */

	@Autowired
	private ProductService productService;

	@Autowired
	private TypeService typeService;

	private static final String LIST = "admin/products";
	private static final String INPUT = "admin/products-input";
	private static final String REDIRECT_LIST = "redirect:/admin/products";

//	產品列表
	@GetMapping("/products")
	public String products(
			@PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, Model model,
			ProductQuery prod, Type type) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", productService.listProducts(pageable, prod));
		return LIST;
	}

//	關鍵字搜尋
	@PostMapping("/products/search")
	public String search(@PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model, ProductQuery prod) {
		model.addAttribute("page", productService.listProducts(pageable, prod));
		return "admin/products :: productList";
	}

//	至新增畫面
	@GetMapping("/products/input")
	public String input(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("types", typeService.listType());
		return INPUT;
	}

//	至編輯畫面
	@GetMapping("/products/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProduct(id));
		model.addAttribute("types", typeService.listType());
		return INPUT;
	}

//	新增
//	@PostMapping("/products")
//	public String post(@Valid Product product, BindingResult result, RedirectAttributes attributes) {
//		Product product1 = productService.getProductByName(product.getName());
//		if (product1 != null) {
//			result.rejectValue("name", "nameError", "不能添加重複的產品");
//		}
//		if (result.hasErrors()) {
//			return INPUT;
//		}
//		
//		product.setType(typeService.getType(product.getType().getId()));
//		Product p = productService.saveProduct(product);
//		if (p == null) {
//			attributes.addFlashAttribute("message", "新增失敗");
//		} else {
//			attributes.addFlashAttribute("message", "新增成功");
//		}
//		return REDIRECT_LIST;
//	}
	
	
	
	@PostMapping("/products")
	public String post(@Valid Product product, @RequestParam("img") MultipartFile multipartFile, BindingResult result, RedirectAttributes attributes) {
		Product product1 = productService.getProductByName(product.getName());
		if (product1 != null) {
			result.rejectValue("name", "nameError", "不能添加重複的產品");
		}
		if (result.hasErrors()) {
			return INPUT;
		}
		
			// 準備變數放入實體類 放入資料庫
			String filename = null;
			// 1.定義上傳的目標路徑"static" + File.separator + "upload" 靜態資原始檔夾 分隔符 存放img的資料夾
			String path = "/Users/amber/mrtsuopat/src/main/resources/static/image/";
			// 2.獲取原始檔名
			String oldFileName = multipartFile.getOriginalFilename();

			String newFileName = UUID.randomUUID() + oldFileName;

			File targetFile = new File(path, newFileName);

			// 寫入 上傳
			try {
				multipartFile.transferTo(targetFile);

			} catch (IOException e) {
				e.printStackTrace();
				return INPUT;
			}
			filename = newFileName; // 將處理好的上傳的檔案的名字傳入變數存進資料庫
			product.setPicture(filename);
			
		product.setType(typeService.getType(product.getType().getId()));
		Product p = productService.saveProduct(product);
		if (p == null) {
			attributes.addFlashAttribute("message", "新增失敗");
		} else {
			attributes.addFlashAttribute("message", "新增成功");
		}
		return REDIRECT_LIST;
	}
	
	

//	修改
	@PostMapping("/products/{id}")
	public String editPost(@Valid Product product, @RequestParam("img") MultipartFile multipartFile, BindingResult result, @PathVariable Long id,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return INPUT;
		}
		// 準備變數放入實體類 放入資料庫
					String filename = null;
					// 1.定義上傳的目標路徑"static" + File.separator + "upload" 靜態資原始檔夾 分隔符 存放img的資料夾
					String path = "/Users/amber/mrtsuopat/src/main/resources/static/image/productImg/";
					// 2.獲取原始檔名
					String oldFileName = multipartFile.getOriginalFilename();

					String newFileName = UUID.randomUUID() + oldFileName;

					File targetFile = new File(path, newFileName);

					// 寫入 上傳
					try {
						multipartFile.transferTo(targetFile);

					} catch (IOException e) {
						e.printStackTrace();
						return INPUT;
					}
					filename = newFileName; // 將處理好的上傳的檔案的名字傳入變數存進資料庫
					product.setPicture(filename);
		Product p = productService.updateProduct(id, product);
		if (p == null) {
			attributes.addFlashAttribute("message", "更新失敗");
		} else {
			attributes.addFlashAttribute("message", "更新成功");
		}
		return REDIRECT_LIST;
	}

//	刪除
	@GetMapping("/products/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		productService.deleteProduct(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return REDIRECT_LIST;
	}

}