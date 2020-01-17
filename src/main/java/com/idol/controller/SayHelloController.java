package com.idol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "hello~";
	}
	
	
	@RequestMapping("/hello/{id}")
	public HashMap<String, Integer> sayHashMap(@PathVariable Integer id){
		HashMap<String, Integer> hh = new HashMap<>();
		hh.put("id", id);
		
		return hh;
	}
    
	@RequestMapping("/count/{to}")
	public int[] countNum(@PathVariable Integer to) {
		int []res = new int[to];
		for(int i=1;i<to + 1;i++) {
			res[i-1] = i;
		}
		return res;
	}
}
