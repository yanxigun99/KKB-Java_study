package com.xd.springbootshardingtable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.springbootshardingtable.entity.Store;
import com.xd.springbootshardingtable.entity.User;

import java.util.List;

/**
 * @Classname UserService
 * @Description 用户服务类
 * @Date 2019-05-26 17:31
 * @Version 1.0
 */
public interface StoreService extends IService<Store> {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(Store entity);

    
}
