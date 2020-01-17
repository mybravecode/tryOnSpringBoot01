package com.idol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/th")
public class ThymeleafController {
	
	@RequestMapping("/index")
	public String getThymeLeafIndex(ModelMap map) {
		map.addAttribute("name", "FZZ");
		return "thymeleaf/index";
	}
	
	
	@RequestMapping("/center")
	public String getThymeLeafCenter() {
		return "thymeleaf/center/center";
	}

}
