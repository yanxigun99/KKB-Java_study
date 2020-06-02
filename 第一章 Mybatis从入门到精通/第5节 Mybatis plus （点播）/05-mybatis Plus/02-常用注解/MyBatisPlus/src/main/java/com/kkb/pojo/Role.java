package com.kkb.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("role")
@ApiModel("角色类")
public class Role implements Serializable {

	@ApiModelProperty(name = "id", value = "ID 主键")
	@TableId(type = IdType.AUTO)
	private String id;
	
	@ApiModelProperty(name = "roleCode", value = "角色编号")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String roleCode;
	
	@ApiModelProperty(name = "roleName", value = "角色名")
	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String roleName;
	
	@TableField(exist = false)
	private Set<Permission> permissions = new HashSet<>();
}
