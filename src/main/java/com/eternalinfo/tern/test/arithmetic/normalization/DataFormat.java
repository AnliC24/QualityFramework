package com.eternalinfo.tern.test.arithmetic.normalization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.test.arithmetic.Arithmetic;

/**
 * @author 王诚沣
 * @下午4:14:22
 * @description	数据格式检查
 * @version
 */
public abstract class DataFormat implements Arithmetic{
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	String DEFAULT_STRATEGY = "DefaultDataFormat";
	
	String DEFAULT_RESOURCE_URL = "DataFormat";
	
	String DEFAULT_SQL_TYPE = "DefaultDataFormat";
}
