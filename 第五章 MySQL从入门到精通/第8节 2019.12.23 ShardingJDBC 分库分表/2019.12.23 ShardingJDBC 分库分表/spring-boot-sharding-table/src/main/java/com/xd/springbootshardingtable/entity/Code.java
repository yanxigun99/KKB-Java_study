package com.xd.springbootshardingtable.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("code")
public class Code {
	
	private Long id;
	private String name;
	
	
}
