package com.idol.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idol.pojo.IdolResource;
import com.idol.pojo.UserT;
import com.idol.result.IdolResult;

@RestController
public class ResourceController {
	
	@Autowired
	private IdolResource idolResource;
	
	@RequestMapping("/getJsonResource")
	public IdolResult getJsonResource() {
		
//	    private int age;
//	    private String password;
//	    private String home;
//	    private String name;
		
		IdolResource resource = new IdolResource();
		BeanUtils.copyProperties(idolResource, resource);
		return IdolResult.ok("resource", resource);
	}

}
