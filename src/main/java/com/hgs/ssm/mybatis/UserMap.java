package com.hgs.ssm.mybatis;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator UserMap名字�?定要与namespace的一致，否则报is not known to the
 *         MapperRegistry 其方法也要与id�?�? 这接口相当于UerMapper映射器的接口�?
 */
public interface UserMap {
	User getUser(int id);

	User selectUser(int id);

	List<User> selectAllUser();

	void deleteUser(String id);

	void insertUser(String nickname, String password);

	List<User> queryUser(Map<?, ?> map);
}
