package com.kkb.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.context.annotation.ApplicationScope;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
@ApiModel("用户类")
public class User implements Serializable {
	
	@ApiModelProperty(name = "id", value = "ID 主键")
	@TableId(type = IdType.AUTO)
	private String id;
	
	@ApiModelProperty(name = "name", value = "用户名")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String name;
	
	@ApiModelProperty(name = "age", value = "年龄")
	@TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer age;
	
	@ApiModelProperty(name = "email", value = "邮箱")
	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String email;
	
	@TableField(exist = false)
	private Set<Role> roles = new HashSet<>();
}
