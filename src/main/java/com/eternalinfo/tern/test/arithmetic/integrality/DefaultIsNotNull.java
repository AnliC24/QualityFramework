package com.eternalinfo.tern.test.arithmetic.integrality;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
import com.eternalinfo.tern.test.context.ExecuteStrategy;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.Strategy;
import com.eternalinfo.tern.test.strategy.StrategyFactory;
/**
 * @author 王诚沣
 * @下午2:21:53
 * @description	非空算子 数据源类型默认检查
 * @version
 */
public class DefaultIsNotNull extends IsNotNull{
	

	private Examination bean;
	
	
	public DefaultIsNotNull() throws QualityExecption{
	}
	
	public DefaultIsNotNull(Examination bean) throws QualityExecption {
		this.bean = bean;
	} 
	
	public DefaultIsNotNull(String resourceUrl,String sqlType) throws QualityExecption {
		if(bean == null) {
			throw new ArithmeticException("检核对象为空,无法进行算子检核");
		}
		bean.setResourceUrl(resourceUrl);
		bean.setSqlType(sqlType);
		bean.setStrategy(ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull"));
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType,Examination bean) throws QualityExecption {
		this.bean = bean;
		bean.setResourceUrl(resourceUrl);
		bean.setSqlType(sqlType);
		bean.setStrategy(ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull"));
	}
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		Strategy strategy = StrategyFactory.getInstance().createStrategy(bean.getStrategy());
		strategy.execute(bean);
	}

	@Override
	public void setExamination(Examination bean) throws QualityExecption {
		this.bean = bean;
		validateExamination(bean);
	}
	
	@Override
	public void validateExamination(Examination bean) throws QualityExecption {
		if(bean.getStrategy().length()<=0)bean.setStrategy(ExecuteStrategy.getExecuteStrategy(DEFAULT_STRATEGY));
		if(bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE)||
				bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE.toLowerCase())||
				bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE.toUpperCase()))
		{
			if(bean.getResourceUrl().length()<=0) {
				bean.setResourceUrl(ResourceUrl.getUrlType(DEFAULT_RESOURCE_URL));
			}
			if(bean.getSqlType().length()<=0) {
				bean.setSqlType(ExecuteSqlType.getSqlType(DEFAULT_SQL_TYPE));
			}
		}
	}
}
