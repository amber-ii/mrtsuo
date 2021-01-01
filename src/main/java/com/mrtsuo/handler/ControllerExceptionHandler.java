package com.mrtsuo.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 統一異常處理類
 * 
 * @author amber
 * 
 */

@ControllerAdvice // 會捕捉所有標記Controller的類
public class ControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class) // 表示這個方法可以做異常處理，Exception.class只要是Exception都可以處理
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
//		輸出到控制台
		logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);
//		如果有自定義的錯誤類，拋出自定義的錯誤類。
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
//		將後台訊息返回到前端畫面
		ModelAndView mv = new ModelAndView();
		mv.addObject("url", request.getRequestURL()); // 獲取前端訪問網址
		mv.addObject("exception", e); // 獲取錯誤訊息
		mv.setViewName("error/error"); // 要送到的畫面位置
		return mv;
	}

}
