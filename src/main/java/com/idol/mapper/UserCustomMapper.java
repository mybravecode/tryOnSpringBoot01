package com.idol.mapper;




import java.util.List;

import com.idol.pojo.User;


public interface UserCustomMapper {
	List<User> queryAllUserByAgeOrdered(int age);
}