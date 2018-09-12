package com.hik.dao;

import java.util.List;
import java.util.Map;

import com.hik.entity.User;

/**
 * @Description: 用户Mapper
 * @author: mxy
 *
 */
public interface UserMapper {

	/**
	 * @Description: 登录
	 * @author: mxy
	 *
	 */
	User login(User user);

	/**
	 * @Description: 校验密码
	 * @author: mxy
	 * @date: 2018年4月1日 下午12:51:41
	 */
	User checkPwd(User user);

	/**
	 * @Description: 检查用户名是否重复
	 * @author: mxy
	 *
	 */
	int checkName(String name);

	/**
	 * @Description: 查询用户
	 * @author: mxy
	 *
	 */
	List<User> findUsers(Map<String, Object> map);

	/**
	 * @Description: 获取查询用户数量
	 * @author: mxy
	 *
	 */
	Long getTotalUser(Map<String, Object> map);

	int deleteByPrimaryKey(Integer id);

	int deleteByIds(List<Integer> ids);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}