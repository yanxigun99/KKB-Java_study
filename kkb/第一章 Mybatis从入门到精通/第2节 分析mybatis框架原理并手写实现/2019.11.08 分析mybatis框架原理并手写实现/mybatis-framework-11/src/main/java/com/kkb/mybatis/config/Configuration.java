package com.kkb.mybatis.config;

import javax.sql.DataSource;

/**
 * 封装了全局配置文件和映射文件中的信息
 * @author 灭霸詹
 *
 */
public class Configuration {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
