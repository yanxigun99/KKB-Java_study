package com.kkb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId
    private Integer id;

    @TableField
    private String name;

    @TableField
    private String password;

    @TableField
    private String phone;

    @TableField
    private String email;

    @TableField
    private String address;

    @TableField(exist = false)
    private Set<Role> roles = new HashSet<>();

}
