package com.eternalinfo.tern.test.arithmetic.normalization;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.Strategy;
import com.eternalinfo.tern.test.strategy.StrategyFactory;

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
	

	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		Strategy strategy = StrategyFactory.getInstance().createStrategy(bean.getStrategy());
		strategy.execute(bean);
	}
}
