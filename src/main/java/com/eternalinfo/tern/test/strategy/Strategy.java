package com.eternalinfo.tern.test.strategy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * 	@author cfwang
 *  @time 2020/2/8
 * */
public abstract class Strategy {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	public abstract void execute(Examination bean) throws QualityExecption, ExecuteException, IOException;
	
}
