package com.eternalinfo.tern.test.strategy.db;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import com.eternalinfo.tern.test.examination.DefaultDbObject;
import com.eternalinfo.tern.test.strategy.DbStrategy;
/**
 * @author 王诚沣
 * @上午10:52:48
 * @description 数据源默认执行策略
 * @version
 */
public class DefaultDbStrategy extends DbStrategy{
	
	private Properties sqlProperties;
	
	private DefaultDbObject bean;
		
	@Override
	public void setStrategySql() throws IOException {
		this.bean = (DefaultDbObject) super.bean;
		sqlProperties = Resources.getResourceAsProperties(this.bean.getResourceUrl());
		if(!sqlProperties.containsKey(bean.getSqlType())) {
			throw new ArithmeticException("请配置默认执行sql,"+"例如:"+this.bean.getSqlType()+"=sql");
		}
		executeSql = sqlProperties.getProperty(this.bean.getSqlType());
	}
}
