package com.xd.springbootshardingtable.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tstore")
public class Store {
	private int id;
	private String name;
	
	
}
