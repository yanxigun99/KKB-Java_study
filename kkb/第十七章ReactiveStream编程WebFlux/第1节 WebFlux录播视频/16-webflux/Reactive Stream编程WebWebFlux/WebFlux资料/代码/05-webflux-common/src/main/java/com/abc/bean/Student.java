package com.abc.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
// 指定在MongoDB中生成的表
@Document(collection="t_student")
public class Student {

	@Id
	private String id;
	private String name;
	private int age;
}
















