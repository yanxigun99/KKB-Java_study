//package com.kkb.mybatis.test;
//
//import java.io.InputStream;
//
//import org.junit.Test;
//
//import com.kkb.mybatis.framework.builder.Configuration;
//import com.kkb.mybatis.framework.builder.SqlSessionFactoryBuilder;
//import com.kkb.mybatis.framework.builder.XmlConfigBuilder;
//import com.kkb.mybatis.framework.resource.Resources;
//import com.kkb.mybatis.framework.sqlsession.SqlSession;
//import com.kkb.mybatis.framework.sqlsession.SqlSessionFactory;
//import com.kkb.mybatis.po.User;
//
//public class MybatisTest {
//
//	@Test
//	public void test() throws Exception {
//		// 指定全局配置文件的类路径
//		String resource = "SqlMapConfig.xml";
//		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
//		// InputStream inputStream = Resources.getResourceAsStream(resource);
//		
//		XmlConfigBuilder builder = new XmlConfigBuilder(inputStream);
//		Configuration configuration = builder.parse();
//		System.out.println(configuration);
//	}
//
//	@Test
//	public void test1() throws Exception {
//		// 指定全局配置文件的类路径
//		String resource = "SqlMapConfig.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//		SqlSession sqlSession = sqlSessionFactory.openSqlSession();
//
//		User user = sqlSession.selectOne("findUserById", 1);
//
//		System.out.println(user);
//	}
//}
