//package com.kkb.mybatis.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.kkb.mybatis.po.User;
//import com.kkb.mybatis.sqlsession.SqlSession;
//import com.kkb.mybatis.sqlsession.SqlSessionFactory;
//
///**
// * 持久层代码
// * 
// * @author think
// *
// */
//public class UserDaoImpl implements UserDao {
//
//	// 等待注入
//	private SqlSessionFactory sqlSessionFactory;
//
//	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}
//
//	public UserDaoImpl() {
//	}
//
//	@Override
//	public User queryUserById2(User param) {
//		// sqlsession被调用次数很多，而且它需要Configuration对象
//		// 可以考虑使用工厂来屏蔽SqlSession的构造细节
//		SqlSession sqlSession = sqlSessionFactory.openSqlSession();
//		User user = sqlSession.selectOne("test.findUserById", param);
//		return user;
//	}
//
//	/**
//	 * 先使用JDBC实现
//	 */
//	@Override
//	public User queryUserById(User param) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet rs = null;
//
//		User user = null;
//		try {
//			// 加载数据库驱动
//			Class.forName("com.mysql.jdbc.Driver");
//
//			// 通过驱动管理类获取数据库链接connection = DriverManager
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm?characterEncoding=utf-8", "root",
//					"root");
//
//			// 定义sql语句 ?表示占位符
//			// String sql = "select * from user where id = #{id} and username =
//			// #{username}";
//			String sql = "select * from user where id = ?";
//
//			// 获取预处理 statement
//			preparedStatement = connection.prepareStatement(sql);
//
//			// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的
//			preparedStatement.setInt(1, 1);
//
//			// 向数据库发出 sql 执行查询，查询出结果集
//			rs = preparedStatement.executeQuery();
//
//			// 遍历查询结果集
//			while (rs.next()) {
//				user = new User();
//				user.setId(rs.getInt("id"));
//				user.setUsername(rs.getString("username"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (preparedStatement != null) {
//				try {
//					preparedStatement.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return user;
//	}
//
//}
