package com.eternalinfo.tern.test.arithmetic;

import java.io.IOException;
import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.Strategy;
import com.eternalinfo.tern.test.strategy.StrategyFactory;

/**
 *  @author cfwang
 * */
public abstract class Arithmetic {
	
	protected Examination bean;
		
	public void execute() throws QualityExecption, ExecuteException, IOException{
		Strategy strategy = StrategyFactory.getInstance().createStrategy(bean.getStrategy());
		strategy.execute(bean);
	}
	
	public  void setExamination(Examination bean) throws QualityExecption{
		this.bean = bean;
		setStrategy();
		validateExamination(bean);
	}
	
	public abstract void setStrategy() throws QualityExecption;
	
	public abstract void validateExamination(Examination bean) throws QualityExecption;
	
	protected String DEFAULT_VALIDATE_TYPE = "Db";
}
