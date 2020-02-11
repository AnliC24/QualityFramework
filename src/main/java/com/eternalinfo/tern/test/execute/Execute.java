package com.eternalinfo.tern.test.execute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * 	@author cfwang
 *  @time 2020/2/8
 * */
public abstract class Execute {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	public abstract void execute() throws QualityExecption, ExecuteException;
	
}
