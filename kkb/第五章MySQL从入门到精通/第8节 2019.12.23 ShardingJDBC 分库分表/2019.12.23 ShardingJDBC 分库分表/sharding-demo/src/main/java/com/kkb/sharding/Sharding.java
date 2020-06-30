package com.kkb.sharding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

public class Sharding {
	public static DataSource createDataSource(String username, String password, String url) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		return dataSource;
	}

	public static void execute(DataSource ds, String sql,int oid,int uid,String name) throws SQLException{
		Connection conn = ds.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, oid);
		ps.setInt(2, uid);
		ps.setString(3, name);
		ps.execute();
	}

	public static void main(String[] args) {
		Map<String, DataSource> map=new HashMap<>();
		map.put("kkb_ds_0", createDataSource("root","root","jdbc:mysql://192.168.56.101:3306/kkb_ds_0"));
		map.put("kkb_ds_1", createDataSource("root","root","jdbc:mysql://192.168.56.102:3306/kkb_ds_1"));
		
		ShardingRuleConfiguration config=new ShardingRuleConfiguration();
		
		// 配置Order表规则
		TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
		orderTableRuleConfig.setLogicTable("t_order");//设置逻辑表.
		//kkb_ds_0.t_order_0 kkb_ds_0.t_order_1   kkb_ds_1.t_order_0  kkb_ds_1.t_order_1 
		orderTableRuleConfig.setActualDataNodes("kkb_ds_${0..1}.t_order_${0..1}");//设置实际数据节点.
		orderTableRuleConfig.setKeyGeneratorColumnName("oid");//设置主键列名称.
			    
		// 配置Order表规则：配置分库 + 分表策略(这个也可以在ShardingRuleConfiguration进行统一设置)
		orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("uid", "kkb_ds_${uid % 2}"));
		orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("oid", "t_order_${oid % 2}"));
		config.getTableRuleConfigs().add(orderTableRuleConfig);
		
		try {
			DataSource ds=ShardingDataSourceFactory.createDataSource(map, config, new HashMap(), new Properties());
			
			for(int i=1;i<=10;i++) {
				//逻辑表
				String sql="insert into t_order(oid,uid,name) values(?,?,?)";
				execute(ds,sql,i,i+1,i+"aaa");
			}
			System.out.println("数据插入完成。。。");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
