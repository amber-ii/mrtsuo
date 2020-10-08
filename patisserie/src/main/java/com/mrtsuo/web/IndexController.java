package com.mrtsuo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/home")
	public String helloIndex(){
		return "/admin/product-upload";
	}
}
