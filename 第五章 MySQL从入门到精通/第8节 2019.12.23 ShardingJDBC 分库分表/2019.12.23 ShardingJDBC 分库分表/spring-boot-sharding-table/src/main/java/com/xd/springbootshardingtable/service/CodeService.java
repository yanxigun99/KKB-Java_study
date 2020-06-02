package com.xd.springbootshardingtable.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.springbootshardingtable.entity.Code;
import com.xd.springbootshardingtable.entity.User;

/**
 * @Classname UserService
 * @Description 用户服务类
 * @Date 2019-05-26 17:31
 * @Version 1.0
 */
public interface CodeService extends IService<Code> {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(Code entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<Code> getCodeList();
}
