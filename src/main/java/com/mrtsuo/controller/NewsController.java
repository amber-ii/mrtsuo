package com.mrtsuo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrtsuo.domain.News;
import com.mrtsuo.service.NewsService;

@Controller
@RequestMapping("/admin")
public class NewsController {

	/**
	 * 最新消息(後台)
	 */

	private static final String LIST = "admin/news";
	private static final String INPUT = "admin/news-input";
	private static final String REDIRECT_LIST = "redirect:/admin/news";

	@Autowired
	private NewsService newsService;

//	最新消息列表
	@GetMapping("/news")
	public String news(
			@PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			News news, Model model) {
		model.addAttribute("page", newsService.listNews(pageable, news));
		return LIST;
	}

//	關鍵字搜尋
	@PostMapping("/news/search")
	public String search(
			@PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			News news, Model model) {
		model.addAttribute("page", newsService.listNews(pageable, news));
		return "admin/news :: newsList";
	}

//	至新增頁面
	@GetMapping("/news/input")
	public String input(Model model) {
		model.addAttribute("news", new News());
		return INPUT;

	}

//	至編輯畫面
	@GetMapping("/news/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("news", newsService.getNews(id));
		return INPUT;
	}

//	新增、修改
	@PostMapping("/news")
	public String post(@RequestParam("img") MultipartFile multipartFile, News news, RedirectAttributes attributes)
			throws Exception {
		News n;
		if (news.getId() == null) {
			String oldFileName = multipartFile.getOriginalFilename();
			String newFileName = UUID.randomUUID() + oldFileName;
			String home = System.getProperty("user.home");
			File f = new File(home + File.separator + "uploadpic" + File.separator);
			File targetFile = new File(f, newFileName);

			try {
				multipartFile.transferTo(targetFile);
				news.setPicture(newFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			n = newsService.saveNews(news);
		} else {
			n = newsService.updateNews(news.getId(), news);
		}

		if (n == null) {
			attributes.addFlashAttribute("message", "操作失敗");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		return REDIRECT_LIST;
	}

//			File targetFile = new File(path,newFileName);
//			 String oldFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

//		            try (InputStream inputStream = multipartFile.getInputStream()) {
//		                Files.copy(inputStream, Paths.get(uploadPicPath + filename), // 这里指定了下载的位置
//		                    StandardCopyOption.REPLACE_EXISTING);
//		                news.setPicture(filename);
//		            }
//		        catch (IOException e) {
//		            throw new Exception("失败！" + filename, e);
//		        }

//			測試環境
//			String path = "/Users/amber/mrtsuopat/src/main/resources/static/image/";
//			String path = "/Users/amber/uploadImage/";
//			String path = request.getSession().getServletContext().getRealPath("");
//			String path = request.getSession().getServletContext().getRealPath("");
//			String path = request.getRequestURL().toString() + "/image/";

//			String oldFileName = multipartFile.getOriginalFilename();
//			String newFileName = UUID.randomUUID() + oldFileName;
////			File targetFile = new File(path,newFileName);
//			File aa = new File(ResourceUtils.getURL("classpath:").getPath());
//			if (!aa.exists()) {
//				aa = new File("");
//			}
//			File targetFile = new File(aa.getAbsolutePath() + "/static/image/", newFileName);
//			File targetFile = new File(aa.getAbsolutePath() + "/photo/",newFileName);
//			if(!targetFile.exists()){
//				targetFile.mkdirs();
	// 在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
	// 在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
//			}
//			try {
//				multipartFile.transferTo(targetFile);
//				filename = newFileName; // 將處理好的上傳的檔案的名字傳入變數存進資料庫
//				news.setPicture(filename);
//				news.setUrl("https://mrtsuopat.herokuapp.com/uploadImage/" + newFileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//
//			}

//	新增、修改(原)
//	@PostMapping("/news")
//	public String post(News news, RedirectAttributes attributes) {
//		News n;
//		if (news.getId() == null) {
//			n = newsService.saveNews(news);
//		} else {
//			n = newsService.updateNews(news.getId(), news);
//		}
//		if (n == null) {
//			attributes.addFlashAttribute("message", "操作失敗");
//		} else {
//			attributes.addFlashAttribute("message", "操作成功");
//		}
//		return REDIRECT_LIST;
//	}

//	刪除
	@GetMapping("/news/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		newsService.deleteNews(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return REDIRECT_LIST;
	}
}
