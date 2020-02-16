package com.eternalinfo.tern.test.arithmetic.normalization;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.Strategy;
import com.eternalinfo.tern.test.strategy.StrategyFactory;

/**
 * @author 王诚沣
 * @下午3:58:00
 * @description
 * @version
 */
public class Range extends DataFormat{
	
	private String strategy = "RangeDbStrategy";
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		Strategy strategy = StrategyFactory.getInstance().createStrategy(bean.getStrategy());
		strategy.execute(bean);
	}

	@Override
	public void setStrategy() throws QualityExecption {
		bean.setStrategy(strategy);
	}
	
}
