package com.idol.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idol.pojo.IdolResource;
import com.idol.pojo.StudentResource;

@Controller
@RequestMapping("ftl")
public class FreemarkerController {
	
	@Autowired
	private IdolResource idolResource;
	
	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("resource", idolResource);
		return "freemarker/index";
	}
	
	@Autowired
	private StudentResource studentResource;
	
	@RequestMapping("/studentftl")
	public String studentFtl(ModelMap map) {
		map.addAttribute("student",studentResource);
		return "freemarker/center/center";
	}
	

}
