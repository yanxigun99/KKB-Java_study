package com.kkb.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkb.pojo.Permission;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;

public interface UserMapper extends BaseMapper<User> {
	
	@Select("select * from user where name = #{name}")
	public User findUserByName(String username);

	// 获取用户所拥有的角色
	@Select("select * from  role where rolecode in(select rolecode from userrole where username = #{userName})")
	public Set<Role> getUserRoles(String username);

}
