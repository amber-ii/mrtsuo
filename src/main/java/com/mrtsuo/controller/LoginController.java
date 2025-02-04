package com.mrtsuo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrtsuo.domain.User;
import com.mrtsuo.service.UserService;

@Controller
@RequestMapping("/admin")
public class LoginController {

	/**
	 * 登入帳號、密碼驗證
	 * 
	 */

	@Autowired
	private UserService userService;

//  登入畫面
	@GetMapping
	public String loginPage() {
		return "admin/login";
	}

//	登入驗證
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			RedirectAttributes attributes) {
		User user = userService.checkUser(username, password);
		if (user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);
			return "admin/index";
		} else {
			attributes.addFlashAttribute("message", "帳號或密碼有誤");
		}
		return "redirect:/admin";
	}

//	登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/admin";
	}
}
