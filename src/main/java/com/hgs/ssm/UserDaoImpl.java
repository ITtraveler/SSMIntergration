package com.hgs.ssm;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 通过注解方式实现
 * @author Administrator
 *
 */
public interface UserDaoImpl {
	@Select("select * from user")
	List<User> getAllUser();
	@Insert("insert into user(nickname,password) values(#{nickname},#{password})")
	void insertUser(User user);
}
