package com.idol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.idol.interceptor.UserInterceptor;

@Configuration//表明这是一个适配器
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		/**
		 * 拦截器按照顺序执行
		 * */
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/interceptor/**")
														.addPathPatterns("/ss/**);
		
		super.addInterceptors(registry);
	}

}
