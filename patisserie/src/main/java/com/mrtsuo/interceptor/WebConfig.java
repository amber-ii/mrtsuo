package com.mrtsuo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 控制器的過濾設置
 * 相當於mvc的配置xml
 * @author amber
 *
 */

@Configuration //定義此類為配置文件
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/admin")
		.excludePathPatterns("/admin/login");
	}

	
}
