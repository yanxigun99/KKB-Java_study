package com.kkb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.pojo.Permission;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RoleMapper extends IService {

    @Select("select * from permission where permissioncode in (select permissioncode from rolepermission where rolecode = #{roleCode})")
    Set<Permission> getRolePermissions(String roleCode);
}
