package com.kkb.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkb.pojo.Permission;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;

public interface RoleMapper extends BaseMapper<User> {

	// 获取角色所拥有权限
	@Select("select * from permission where permissioncode in (select permissioncode from rolepermission where rolecode = #{roleCode})")
    public Set<Permission> getRolePermissions(String roleCode);
}
