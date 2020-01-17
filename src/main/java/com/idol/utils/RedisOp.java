package com.idol.utils;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisOp {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

    
    public String get(String key) {
    	return stringRedisTemplate.opsForValue().get(key);
    }
    
    public void set(String key, String value) {
    	stringRedisTemplate.opsForValue().set(key, value);
    }
    
    public boolean hasKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}
    
    public void set(String key, String value, int ttl) {
    	stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }
    
}
