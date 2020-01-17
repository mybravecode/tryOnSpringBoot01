package com.idol.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.idol.result.IdolResult;

@RestControllerAdvice
public class IdolExceptionHandler {
	
	public static final String IDOL_ERROR_PAGE = "error/error";
	
//	@ExceptionHandler(value = Exception.class)
//	public Object errorHandler(HttpServletRequest request,
//			HttpServletResponse response, Exception e) throws Exception {
//		
//		System.out.println("!!!!!!!!!!!!!!1");
//		e.printStackTrace();
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("exceptionfzz",e);
//		modelAndView.addObject("url",request.getRequestURL());
//		modelAndView.setViewName(IDOL_ERROR_PAGE);
//		return modelAndView;
//	}
	
	
	@ExceptionHandler(value = Exception.class)
	public Object errorHanlder(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws Exception {
		
		System.out.println("captured by AjaxExpHandler");
		e.printStackTrace();
		System.out.println("!!!!!!1"+isAjax(request));

		if(isAjax(request)) {
			System.out.println("!!!!!!2"+isAjax(request));
			return IdolResult.exception(e.getMessage(), null);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("exceptionfzz",e);
			modelAndView.addObject("url",request.getRequestURL());
			modelAndView.setViewName(IDOL_ERROR_PAGE);
			return modelAndView;
		}
	}
	
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest"
				.equals(httpRequest.getHeader("X-Requested-With").toString()));
	}	
}
