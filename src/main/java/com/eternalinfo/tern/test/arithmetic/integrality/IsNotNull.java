package com.eternalinfo.tern.test.arithmetic.integrality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.test.arithmetic.Arithmetic;

/**
 * @author 王诚沣
 * @下午2:21:03
 * @description
 * @version
 */
public abstract class IsNotNull implements Arithmetic{
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	String DEFAULT_STRATEGY = "DefaultIsNotNull";
	
	String DEFAULT_RESOURCE_URL = "IsNotNull";
	
	String DEFAULT_SQL_TYPE = "DefaultIsNotNull";
}
