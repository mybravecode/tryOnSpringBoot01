package com.idol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication

//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.idol.mapper")

//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.idol"})

public class BootStarter {

	public static void main(String[] args) {
	    SpringApplication.run(BootStarter.class, args);
		// TODO Auto-generated method stub

	}

}
