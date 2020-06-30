package com.kkb.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("userRole")
@ApiModel("用户角色关系类")
public class UserRole implements Serializable{

	@ApiModelProperty(name = "id", value = "ID 主键")
	@TableId(type = IdType.AUTO)
	private String id;
	
	@ApiModelProperty(name = "username", value = "用户名")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String username;
	
	@ApiModelProperty(name = "roleCode", value = "角色编号")
	@TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer roleCode;
}
