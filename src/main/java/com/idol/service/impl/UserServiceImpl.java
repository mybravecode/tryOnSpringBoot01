package com.idol.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idol.mapper.UserCustomMapper;
import com.idol.mapper.UserMapper;
import com.idol.pojo.User;
import com.idol.result.IdolResult;
import com.idol.service.UserService;
import com.idol.utils.JsonUtils;
import com.idol.utils.RedisOp;

import tk.mybatis.mapper.entity.Example;


@Service
public class UserServiceImpl implements UserService{
    	
	@Autowired
    private UserMapper mapper;
	
	@Autowired
	private UserCustomMapper userCustomMapper;
//	
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisOp redisOp;
	
	@Override
	public IdolResult findUser(long id) {
		// TODO Auto-generated method stub
		
		User findUser = mapper.selectByPrimaryKey(id);
		return IdolResult.ok("OK", findUser);
	}

	@Override
	public IdolResult deleteUser(long id) {
//		User findUser = mapper.selectByPrimaryKey(id);
//		int result = mapper.delete(findUser);
		int result = mapper.deleteByPrimaryKey(id);

		// TODO Auto-generated method stub
		return IdolResult.ok("ok", result);
	}

	@Override
	public IdolResult allUser() {
		// TODO Auto-generated method stub
		
		List<User> userList = mapper.selectAll(); 
		
		return IdolResult.ok("ok+返回所有用户", userList);
	}

	@Override
	public IdolResult addUser(String name, int age, String password, Long phone) {
		
		User addUser = new User();
//		addUser.setId(100L);

		addUser.setAge(age);
		addUser.setName(name);
		addUser.setPassword(password);
		addUser.setPhone(phone);
		int result = mapper.insert(addUser);
		// TODO Auto-generated method stub
		return IdolResult.ok(result + "", addUser);
	}

	@Override
	public IdolResult findConditionalUser(int age) {
		
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLessThanOrEqualTo("age", age);
		List <User> userList = mapper.selectByExample(example);
		// TODO Auto-generated method stub
		return IdolResult.ok("OK", userList);
	}

	@Override
	public IdolResult allPageUser(int page, int size) {
		//分页功能 		PageHelper.startPage(page, size);

		PageHelper.startPage(page, size);
		List<User> userList = mapper.selectAll(); 

//		HashMap<String, Object> pageUserMap= new HashMap<>();
//		PageInfo<User> pageInfo = new PageInfo<User>(userList);
//		pageUserMap.put("users", userList);
//		pageUserMap.put("total", pageInfo.getTotal());
//		pageUserMap.put("pages", pageInfo.getPages());
		// TODO Auto-generated method stub
//		return IdolResult.ok("ok+返回分页用户", pageUserMap);
		return IdolResult.ok("ok+返回分页用户", userList);
	}

	@Override
	public IdolResult findAgeConditionalUser(int age) {
		// TODO Auto-generated method stub
		
		return IdolResult.ok("ok", userCustomMapper.queryAllUserByAgeOrdered(age));
	}

	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
	public IdolResult tranUser(String name, int age, String password, Long phone) {
		User addUser = new User();
		addUser.setAge(age);
		addUser.setName(name);
		addUser.setPassword(password);
		addUser.setPhone(phone);
		mapper.insert(addUser);
		
//		int i = 1 / 0;
		addUser.setName("!!!!");
		mapper.updateByPrimaryKey(addUser);
		// TODO Auto-generated method stub
		return IdolResult.ok("ok + 修改删除", mapper.selectByPrimaryKey(addUser.getId()));
	}
    
	/*
	 * 1. 去缓存中查找,如果找到了则返回,  否则到2
	 * 2.去数据库中查找,如果找到了则放一份到redis中,然后返回
	 * 
	 * */
	//web - 缓存 - mysql
	@Override
	public IdolResult redisUser(long id) {
		// TODO Auto-generated method stub
//		String userKey = "user_" + id;
//		
//		//去缓存中找
//		if(stringRedisTemplate.hasKey(userKey)) {
//			String jsonUser = stringRedisTemplate.opsForValue().get(userKey);
//			System.out.println("key: "+ userKey + ", json: " + jsonUser);
//			User user = JsonUtils.jsonToPojo(jsonUser, User.class);
//			return IdolResult.ok("FROM REDIS", user);
//		}
//		
//		//如果缓存中没有,则从数据库中查找并放入缓存中
//		//缓存击穿
//		//雪崩
//		User user = mapper.selectByPrimaryKey(id);
//		IdolResult result = IdolResult.ok("FROM MYSQL", user);
//		
//		//放入缓存
//		user.setPassword("redis" + user.getPassword());
//		String jsonUser = JsonUtils.objectToJson(user);
//		
//		//放入
//		stringRedisTemplate.opsForValue().set(userKey, jsonUser);
//		return result;
		
		
		String userKey = "user_" + id;
		
		//去缓存中找
		if(redisOp.hasKey(userKey)) {
			String jsonUser = redisOp.get(userKey);
			System.out.println("key: "+ userKey + ", json: " + jsonUser);
			User user = JsonUtils.jsonToPojo(jsonUser, User.class);
			return IdolResult.ok("FROM REDIS", user);
		}
		
		//如果缓存中没有,则从数据库中查找并放入缓存中
		//缓存击穿
		//雪崩
		User user = mapper.selectByPrimaryKey(id);
		IdolResult result = IdolResult.ok("FROM MYSQL", user);
		
		//放入缓存
		user.setPassword("redis" + user.getPassword());
		String jsonUser = JsonUtils.objectToJson(user);
		
		//放入
		redisOp.set(userKey, jsonUser, 5);
		return result;
		
	}

}

/**
 * MVC
 * M Model Pojo Dao Mapper Bean  User类  增删改查
 * C Control Service         根据路由 找到对应的函数
 * V View 视图 html ftl       之类的 渲染页面 
 * 
 * */
