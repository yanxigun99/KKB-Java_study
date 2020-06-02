package com.kkb.mybatis.test;

import java.io.InputStream;


import org.junit.Test;

import com.kkb.mybatis.config.Configuration;
import com.kkb.mybatis.config.XMLConfigBuilder;
import com.kkb.mybatis.io.Resources;

public class UserDaoTest {

	/**
	 * 解析阶段
	 * @throws Exception
	 */
	@Test
	public void testInitConfiguration() throws Exception {
		//1.指定全局配置文件路径
		String location = "mybatis-config.xml";
		//2.加载配置文件成InputStream
		InputStream inputStream = Resources.getResourceAsStream(location);
		//3.根据InputStream获取Document对象
		XMLConfigBuilder configBuilder = new XMLConfigBuilder();
		//4.需要专门对mybatis标签进行解析
		Configuration configuration = configBuilder.parse(inputStream);
		System.out.println(configuration);
	}

	@Test
	public void testQueryUserById() {
//		UserDao userDao = new UserDaoImpl();
//		User param = new User();
//		param.setId(1);
//		User user = userDao.queryUserById(param);
//		System.out.println(user);

	}

	/**
	 * 使用手写mybatis框架去实现的
	 */
	@Test
	public void testQueryUserById2() {
//		String resource = "SqlMapConfig.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		// SqlSessionFactory的创建可能有几种创建方式，但是我还是不想要知道SqlSessionFactory的构造细节
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		
//		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
//		User param = new User();
//		param.setId(1);
//		User user = userDao.queryUserById2(param);
//		System.out.println(user);
	}

}
