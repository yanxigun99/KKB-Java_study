package com.xd.springbootshardingtable.service.Impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.springbootshardingtable.entity.Store;
import com.xd.springbootshardingtable.entity.User;
import com.xd.springbootshardingtable.mapper.StoreMapper;
import com.xd.springbootshardingtable.service.StoreService;

/**
 * @Classname UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2019-05-26 17:32
 * @Version 1.0
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    @Override
    public boolean save(Store entity) {
        return super.save(entity);
    }

   

}
