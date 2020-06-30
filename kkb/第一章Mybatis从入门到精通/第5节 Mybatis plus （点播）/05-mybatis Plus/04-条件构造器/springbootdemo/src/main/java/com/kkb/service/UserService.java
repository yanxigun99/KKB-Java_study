package com.kkb.service;

import com.kkb.pojo.User;

public interface UserService {


    public User findUserByName(String userName) throws Exception;

    public String findEmail() throws Exception;

}
