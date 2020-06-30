package com.kkb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@TableName("role")
public class Role implements Serializable {

    @TableId
    private Integer id;

    @TableField
    private String roleCode;

    @TableField
    private String roleName;

    @TableField(exist = false)
    private Set<Permission> permissions = new HashSet<>();

}
