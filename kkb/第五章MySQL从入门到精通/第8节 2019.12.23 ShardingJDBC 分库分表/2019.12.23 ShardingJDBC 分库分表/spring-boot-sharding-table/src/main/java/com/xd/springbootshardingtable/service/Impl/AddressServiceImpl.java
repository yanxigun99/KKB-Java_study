package com.xd.springbootshardingtable.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.springbootshardingtable.entity.Address;
import com.xd.springbootshardingtable.entity.User;
import com.xd.springbootshardingtable.mapper.AddressMapper;
import com.xd.springbootshardingtable.service.AddressService;

/**
 * @Classname UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2019-05-26 17:32
 * @Version 1.0
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public boolean save(Address entity) {
        return super.save(entity);
    }

    @Override
    public List<Address> getAddressList() {
        return baseMapper.selectList(Wrappers.<Address>lambdaQuery());
    }

}
