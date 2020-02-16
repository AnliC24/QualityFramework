package com.eternalinfo.tern.test.arithmetic.normalization;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;

/**
 * @author 王诚沣
 * @下午4:15:23
 * @description	数据源执行策略 数据格式检查
 * @version
 */
public class DefaultDataFormat extends DataFormat{
	
	public DefaultDataFormat() throws QualityExecption {
	}
	
	public DefaultDataFormat(Examination bean) throws QualityExecption {
		super.bean = bean;
	}
}
