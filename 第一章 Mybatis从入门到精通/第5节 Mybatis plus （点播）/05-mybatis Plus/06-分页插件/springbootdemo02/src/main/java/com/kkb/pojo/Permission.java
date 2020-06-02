package com.kkb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("permission")
public class Permission implements Serializable {

    @TableId
    private Integer id;

    @TableField
    private String permissionCode;

    @TableField
    private String permissionName;

}
