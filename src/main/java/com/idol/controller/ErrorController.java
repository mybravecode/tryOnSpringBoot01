package com.idol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idol.result.IdolResult;

@Controller
@RequestMapping("/err")
public class ErrorController {
	
	@RequestMapping("/errorPage")
	public String getError(ModelMap map) {
		int i = 1 / 0;
		System.out.print(i);
		String result = "" + i;		
		return "error";//没有responsebody => error.html
	}
    
	
	@RequestMapping("/ajax/error")
	@ResponseBody
	public String getAjaxError() {
		int i = 1 / 0 ;
		return "" + i;//有responsebody => json 字符串
	}
	
	
	@RequestMapping("/ajaxerror")
    public String getErrorPage() {
		//int i = 1 / 0 ;
		return "thymeleaf/ajaxerror";
	}
	
	@ResponseBody
	@RequestMapping("/getAjaxerror")
	public IdolResult getAjaxData() {
		int i = 1/ 0;
		return IdolResult.ok("OK", "IdolResultData");
	}
}
