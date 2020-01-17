package com.idol.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idol.mapper.UserCustomMapper;
import com.idol.pojo.UserT;
import com.idol.result.IdolResult;
import com.idol.service.UserService;

@Controller
@RequestMapping("/user")

public class UserController {
   
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserCustomMapper userCustomMapper;
	
    
//	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserT getUser() {
		UserT user = new UserT();
		user.setPassword("dsd");
		user.setName("imooc");
		user.setAge(20);
		user.setBirthday(new Date());
		user.setDesc(null);
		
		return user;
	}
	
	
	@RequestMapping("/getJsonUser")
	@ResponseBody
	public IdolResult getJsonUser() {
		
		UserT user = new UserT();
		user.setPassword("dsd");
		user.setName("imooc");
		user.setAge(20);
		user.setBirthday(new Date());
		user.setDesc(null);
		
		return IdolResult.ok("okfzz", user);
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public IdolResult findUserById(@RequestParam Long id) {
	    
		System.out.println("user id: " + id);
		return userService.findUser(id);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public IdolResult addUserById(@RequestParam String name, 
			@RequestParam int age,
			@RequestParam String password, 
			@RequestParam Long phone) {
	    		
		return userService.addUser(name, age, password, phone);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public IdolResult deleteUserById(@RequestParam Long id) {
		return userService.deleteUser(id);
	}
	
	@RequestMapping("/allUser")
	@ResponseBody
	public IdolResult getAllUser() {
		return userService.allUser();
	}
	
	
	@RequestMapping("/allPageUser")
	@ResponseBody
	public IdolResult allPageUser(@RequestParam int page, @RequestParam int size) {
		 
		return userService.allPageUser(page, size);
	}
	
	
	@RequestMapping("/findConditionalUser")
	@ResponseBody
	public IdolResult findConditionalUser(@RequestParam int age) {
		return userService.findConditionalUser(age);
	}
	
	
	@RequestMapping("/findAgeConditionalUser")
	@ResponseBody
	public IdolResult findAgeConditionalUser(@RequestParam int age) {
//		return IdolResult.ok("OK", userCustomMapper.queryAllUserByAgeOrdered(age));
		return userService.findAgeConditionalUser(age);

	}
	
	//测试事务使用的例子
	@RequestMapping("/tranUser")
	@ResponseBody
	public IdolResult tranUser(@RequestParam String name, 
			@RequestParam int age,
			@RequestParam String password, 
			@RequestParam Long phone) {
	    		
		return userService.tranUser(name, age, password, phone);
	}
	
	//测试redis的例子
	@RequestMapping("/testRedis")
	@ResponseBody
	public IdolResult redisUser(@RequestParam long id) {
	    		
		return userService.redisUser(id);
	}
}
