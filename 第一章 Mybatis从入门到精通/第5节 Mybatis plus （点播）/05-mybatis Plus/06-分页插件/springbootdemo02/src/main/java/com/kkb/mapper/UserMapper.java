package com.kkb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.pojo.Permission;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{username}")
    public User findUserByName(String username);

    @Select("select * from  role where rolecode in(select rolecode from userrole where username = #{username})")
    public Set<Role> getUserRoles(String username);

    @Select("select * from permission where permissioncode in (select permissioncode from rolepermission where rolecode = #{roleCode})")
    Set<Permission> getRolePermissions(String roleCode);

    IPage<User> selectPageVo(Page page, @Param("name") String name);
}
