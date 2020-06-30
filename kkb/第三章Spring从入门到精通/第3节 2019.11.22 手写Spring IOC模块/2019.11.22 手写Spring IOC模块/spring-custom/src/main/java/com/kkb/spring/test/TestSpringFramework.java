package com.kkb.spring.test;

import org.junit.Test;

import com.kkb.spring.bean.factory.BeanFactory;
import com.kkb.spring.bean.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.po.Student;

public class TestSpringFramework {

	@Test
	public void test() throws Exception {
		String location = "beans.xml";
		BeanFactory beanFactory = new DefaultListableBeanFactory(location);
		
		Student student = (Student) beanFactory.getBean("student");
		System.out.println(student);
	}

}
