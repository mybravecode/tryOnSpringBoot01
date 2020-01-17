package com.idol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idol.pojo.UserT;
import com.idol.result.IdolResult;

@RestController
public class TestController {

	@RequestMapping("/test")
	public UserT getTestUser() {
		UserT user = new UserT();
		user.setAge(200);
		user.setName("qqq");
		
		return user;
	}
	
	@RequestMapping("/testJson")
	public IdolResult getTestJsonUser() {
		UserT user = new UserT();
		user.setAge(200);
		user.setName("qqq");
		IdolResult result = new IdolResult("testJson", user);

		return result;
	}
}
