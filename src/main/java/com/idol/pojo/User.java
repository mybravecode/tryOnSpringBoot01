package com.idol.pojo;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")             
    private Long id;

    
//    @Id
//    @GeneratedValue(generator="UUID") 
//    private String id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;

    private String password;

    private Long phone;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户年龄
     *
     * @return age - 用户年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置用户年龄
     *
     * @param age 用户年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return phone
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }
}