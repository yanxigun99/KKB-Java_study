package com.kkb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.pojo.User;

public interface UserService {

    public User findUserByName(String userName) throws Exception;

    public String findEmail() throws Exception;

    public IPage<User> selectUserPage(Page<User> page, String name);

}
