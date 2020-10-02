package com.mrtsuo.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author amber
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/{id}/{name}")
	public String index(@PathVariable Integer id, @PathVariable String name) {
//		模擬一個文章或是商品找不到，	再自定義一個異常類，不要直接導入到404
//		String patisserie = null;
//		if(patisserie == null) {
//			throw new NotFoundException("商品不存在");
//		}
		return "index";
	}
}
