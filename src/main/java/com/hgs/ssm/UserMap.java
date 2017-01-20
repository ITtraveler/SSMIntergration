package com.hgs.ssm;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator UserMap名字一定要与namespace的一致，否则报is not known to the
 *         MapperRegistry 其方法也要与id一致 这接口相当于UerMapper映射器的接口版
 */
public interface UserMap {
	User selectUser(int id);

	List<User> selectAllUser();

	void deleteUser(String id);

	void insertUser(String nickname,String password);
	
	List<User> queryUser(Map<?, ?> map);
}
