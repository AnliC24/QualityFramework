package com.eternalinfo.tern.test.arithmetic.normalization;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;

/**
 * @author 王诚沣
 * @下午3:58:00
 * @description
 * @version
 */
public class Range extends DataFormat{
	
	private String strategy = "RangeDbStrategy";
	
	@Override
	public void setStrategy() throws QualityExecption {
		bean.setStrategy(strategy);
	}
	
}
