package com.kkb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.mapper.RoleMapper;
import com.kkb.mapper.UserMapper;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;
import com.kkb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String findEmail() {

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name", "cuihua");

        User user = (User) userMapper.selectOne(qw);
        return user.getEmail();

    }

    @Override
    public User findUserByName(String userName) {

        // 查找 User
        User user = userMapper.findUserByName(userName);
        System.out.println("获取到的用户：" + user);

        // 查找 Role
        Set<Role> roles = userMapper.getUserRoles(userName);

        for (Role role : roles) {
            role.setPermissions(roleMapper.getRolePermissions(role.getRoleCode()));
        }

        user.setRoles(roles);

        return user;
    }

    public IPage<User> selectUserPage(Page<User> page, String name) {

        return userMapper.selectPageVo(page, name);
    }
}
