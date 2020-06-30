package com.kkb.mybatis.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestJdbc {

	@Test
	public void test() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 通过驱动管理类获取数据库链接connection = DriverManager
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
					"root", "root");

			// 定义sql语句 ?表示占位符
			String sql = "select * from user where username = ?";

			// 获取预处理 statement
			preparedStatement = connection.prepareStatement(sql);

			// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的
			preparedStatement.setString(1, "王五");

			// 向数据库发出 sql 执行查询，查询出结果集
			rs = preparedStatement.executeQuery();

			// 遍历查询结果集
			while (rs.next()) {
				System.out.println(rs.getString("id") + " " + rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block e.printStackTrace();
				}
			}
		}
	}

	/*
	@Test
	public void test2() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			// 1.根据配置文件路径，读取配置文件（InputStream流）
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);

			XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
			Configuration configuration = xmlConfigBuilder.parse(inputStream);

			DataSource dataSource = configuration.getDataSource();
			connection = dataSource.getConnection();

			// 定义sql语句 ?表示占位符

			MappedStatement mappedStatement = configuration.getMappedStatement("test.findUserById");
			// 获取SqlSource，它里面存储了从映射文件中解析出来的SQL文本（包含#{}）
			SqlSource sqlSource = mappedStatement.getSqlSource();
			// 解析SQL文本，将#{}替换为?，并且将替换前的参数信息和解析后SQL语句封装到BoundSql中
			BoundSql boundSql = sqlSource.getBoundSql();
			String sql = boundSql.getSql();

			// 准备一个参数对象
			Integer id = 1;
			User user = new User();
			user.setId(id);
		

			// String sql = "select * from user where username = #{id}";

			// 获取预处理 statement
			String statementType = mappedStatement.getStatementType();
			if (statementType.equals("prepared")) {

				// 预编译
				preparedStatement = connection.prepareStatement(sql);
				// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的

				List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				for (int i = 0; i < parameterMappings.size(); i++) {
					ParameterMapping parameterMapping = parameterMappings.get(i);
					// 要知道映射文件中参数的类型
					Class<?> parameterTypeClass = parameterMapping.getParameterTypeClass();
					// 要知道参数值（只要知道参数名称，就可以去入参对象）
					String parameterName = parameterMapping.getParameterName();

					// 要判断入参是什么类型（8种基本类型、String类型、Map类型、集合和数组类型\POJO类型）
					// 为什么要做这个判断呢？
					if (parameterTypeClass == Integer.class) {
						Integer value = id;
						preparedStatement.setObject(i + 1, value);
					} else {
						// 利用反射，通过参数名称，获取对象的属性值
						Field field = parameterTypeClass.getDeclaredField(parameterName);
						field.setAccessible(true);
						Object value = field.get(user);
						preparedStatement.setObject(i + 1, value);
					}

				}

				// 向数据库发出 sql 执行查询，查询出结果集
				rs = preparedStatement.executeQuery();

				// 遍历查询结果集
				Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
				List<Object> results = new ArrayList<>();
				while (rs.next()) {
					Object resultObj = resultTypeClass.newInstance();
					// 通过反射去给实例赋值
					ResultSetMetaData metaData = rs.getMetaData();

					int columnCount = metaData.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						String columnName = metaData.getColumnName(i);

						// 前提是列名和属性名称要一周
						Field declaredField = resultTypeClass.getDeclaredField(columnName);
						// 暴力访问
						declaredField.setAccessible(true);
						// rs的下标是从1开始的
						declaredField.set(resultObj, rs.getObject(i));
					}

					results.add(resultObj);
				}
			} else if (statementType.equals("callable")) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block e.printStackTrace();
				}
			}
		}
	}
	*/
}
