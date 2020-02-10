package com.eternalinfo.tern.arithmetic.normalization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.examination.beans.DbCheckBean;
import com.eternalinfo.tern.examination.beans.FileCheckBean;

/**
 * @author 王诚沣
 * @上午11:16:57
 * @description 数据格式检查 默认执行策略
 * @version
 */
public class DefaultDataFormat extends DataFormat{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	public void checkType() {
		LOG.info("执行默认类型检查");
	}

	public void checkLength() {
		LOG.info("执行默认长度检查");
	}

	public void checkPrecision() {
		LOG.info("执行默认精度检查");
	}

	@Override
	public void check(DbCheckBean a) throws QualityExecption {
		checkType();
		checkLength();
		checkPrecision();
	}

	@Override
	public void check(FileCheckBean a) throws QualityExecption {
	}
	
}
