package com.hgs.ssm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hgs.ssm.mybatis.MyBatisUtils;

public class UserDao {
	public List<User> getUserList(Map<String,String> map) throws IOException{
		SqlSession session = MyBatisUtils.getSession();
		UserMap userMap = session.getMapper(UserMap.class);
		session.commit();
		return userMap.queryUser(map);
	}
	public List<User> getAllUser() throws IOException {
		SqlSession session = MyBatisUtils.getSession();
		UserMap userMap = session.getMapper(UserMap.class);
		List<User> list = userMap.selectAllUser();
		session.close();
		return list;
	}
	
	public User getUser(String id) throws IOException{
		SqlSession session = MyBatisUtils.getSession();
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		User user = session.selectOne("com.hgs.ssm.UserMap.selectUser",map);
		session.close();
		return user;
	}
	public void delete(String id) throws IOException{
		SqlSession session = MyBatisUtils.getSession();
		UserMap userMap = session.getMapper(UserMap.class);
		userMap.deleteUser(id);
		session.commit();
		session.close();
	}
	
	public void insert(String nickname,String password) throws IOException{
		SqlSession session = MyBatisUtils.getSession();
		UserMap userMap = session.getMapper(UserMap.class);
		userMap.insertUser(nickname, password);
		session.commit();
		session.close();
	}
}