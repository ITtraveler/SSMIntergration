package com.hgs.ssm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] arg) throws IOException {
		// SqlSession session = MyBatisUtils.getSession();
		// User user = session.selectOne("com.hgs.ssm.UserMap.selectUser",2);
		// System.out.println(user);
		// UserMap userMapper = session.getMapper(UserMap.class);
		// User u = userMapper.selectUser(1);
		// System.out.println(u);
		// session.close();
		UserDao uDao = new UserDao();
//		User user = uDao.getUser("8");
//		System.out.println(user);
		//query
//		List<User> uList = uDao.getAllUser();
//		for (User u : uList) {
//			System.out.println(u);
//		}
	//	uDao.insert("hgs", "asd123asd");
//		
//		SqlSession session = MyBatisUtils.getSession();
//		UserDaoImpl userDaoImpl= session.getMapper(UserDaoImpl.class);
//		User mUser = new User();
//		mUser.setNickname("lvan");
//		mUser.setPassword("admin");
//		userDaoImpl.insertUser(mUser);
//		List<User> uList = userDaoImpl.getAllUser();
//		for (User u : uList) {
//			System.out.println(u);
//		}
//		session.commit();
//		session.close();	
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("nickname", "hgs");
//		//map.put("id","1");
//		List<User> user = uDao.getUserList(map);
//		for (User uu : user) {
//			System.out.println(uu);
//		}
//		
		User user = uDao.getUser(6);
		System.out.println(user);
	}
}
