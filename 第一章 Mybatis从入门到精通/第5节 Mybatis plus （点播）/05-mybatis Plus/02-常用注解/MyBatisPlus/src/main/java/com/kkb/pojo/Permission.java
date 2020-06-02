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
@TableName("permission")
@ApiModel("权限类")
public class Permission implements Serializable {

	@ApiModelProperty(name = "id", value = "ID 主键")
	@TableId(type = IdType.AUTO)
	private String id;
	
	@ApiModelProperty(name = "permissionCode", value = "权限编号")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String permissionCode;
	
	@ApiModelProperty(name = "permissionName", value = "权限名")
	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String permissionName;
	
	@ApiModelProperty(name = "path", value = "映射路径")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String path;
}
