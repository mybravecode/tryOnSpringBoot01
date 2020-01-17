package com.idol.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.idol.result.IdolResult;

public interface UserService {
    public IdolResult findUser(long id);
    
    public IdolResult deleteUser(long id);
    
    public IdolResult allUser();
    
    public IdolResult addUser(String name, int age, String password, Long phone);
    
    public IdolResult findConditionalUser(int age);
    
    public IdolResult allPageUser(int page, int size);
    
    public IdolResult findAgeConditionalUser(int age);

    public IdolResult tranUser(String name, int age, String password, Long phone);

    
    public IdolResult redisUser(long id);
    
}
