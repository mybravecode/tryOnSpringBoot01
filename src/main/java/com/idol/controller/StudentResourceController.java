package com.idol.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idol.pojo.IdolResource;
import com.idol.pojo.StudentResource;
import com.idol.pojo.UserT;
import com.idol.result.IdolResult;

@RestController
public class StudentResourceController {
	
	@Autowired
	private StudentResource studentResource;
	
	@RequestMapping("/getJsonStudentResource")
	public IdolResult getJsonResource() {
		
//	    private int age;
//	    private String password;
//	    private String home;
//	    private String name;
		
		StudentResource resource = new StudentResource();
		BeanUtils.copyProperties(studentResource, resource);
		return IdolResult.ok("resource!!!", resource);
	}

}
