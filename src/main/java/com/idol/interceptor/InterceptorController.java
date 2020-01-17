package com.idol.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idol.result.IdolResult;

@Controller
public class InterceptorController {

	@RequestMapping("/interceptor/test")
	@ResponseBody
	public IdolResult testInterceptor() {
		System.out.println("处理业务逻辑!!!!!!!!!!!");
		return IdolResult.ok();
	}
}
