package com.eternalinfo.tern.test.arithmetic.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;

/**
 * @author 王诚沣
 * @下午7:17:41
 * @description
 * @version
 */
public abstract class Factory {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	public abstract void createArithmetic(Examination bean,String type) throws QualityExecption;
	public abstract void remove(String type);
}
