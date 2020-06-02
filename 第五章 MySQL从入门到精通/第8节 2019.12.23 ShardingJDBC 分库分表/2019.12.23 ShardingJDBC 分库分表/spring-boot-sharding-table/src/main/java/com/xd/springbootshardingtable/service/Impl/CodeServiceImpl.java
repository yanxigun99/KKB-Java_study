package com.xd.springbootshardingtable.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.springbootshardingtable.entity.Code;
import com.xd.springbootshardingtable.entity.User;
import com.xd.springbootshardingtable.mapper.CodeMapper;
import com.xd.springbootshardingtable.service.CodeService;

/**
 * @Classname UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2019-05-26 17:32
 * @Version 1.0
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {
    @Override
    public boolean save(Code entity) {
        return super.save(entity);
    }

    @Override
    public List<Code> getCodeList() {
        return baseMapper.selectList(Wrappers.<Code>lambdaQuery());
    }

}
