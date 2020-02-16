package com.eternalinfo.tern.test.arithmetic.factory;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.Arithmetic;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午7:17:41
 * @description
 * @version
 */
public abstract class Factory {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	public abstract void createArithmetic(Examination bean) throws QualityExecption, ExecuteException, IOException;
	public abstract void remove(String type);
	public abstract void registry(String type,Arithmetic arithmetic);
}
