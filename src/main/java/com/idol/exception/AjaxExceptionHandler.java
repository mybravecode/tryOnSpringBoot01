package com.idol.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.idol.result.IdolResult;

//@RestControllerAdvice
public class AjaxExceptionHandler {
    
//	@ExceptionHandler(value=Exception.class)
	public IdolResult ajaxErrorHandler(HttpServletRequest req, Exception e)
			throws Exception{
		e.printStackTrace();
		System.out.println("an error of ajax occured");
		return IdolResult.exception(e.getMessage(), null);
	}
}
