package com.kkb.mybatis.dao;

import com.kkb.mybatis.po.User;

public interface UserDao {

	/**
	 * 根据用户Id查询用户信息
	 * @param param
	 * @return
	 */
	User queryUserById(User param);
	/**
	 * 根据用户Id查询用户信息
	 * @param param
	 * @return
	 */
	User queryUserById2(User param);
}
