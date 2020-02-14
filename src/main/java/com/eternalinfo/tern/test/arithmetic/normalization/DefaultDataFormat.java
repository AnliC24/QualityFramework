package com.eternalinfo.tern.test.arithmetic.normalization;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.DefaultDbObject;
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
	
	
	private Examination bean; 
	
	public DefaultDataFormat() throws QualityExecption {
		this.bean = new DefaultDbObject();
	}
	
	public DefaultDataFormat(Examination bean) throws QualityExecption {
		this.bean = bean;
	}
	
	@Override
	public void setExamination(Examination bean) throws QualityExecption {
		this.bean = bean;
		validateExamination(bean);
	}

	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		Strategy strategy = StrategyFactory.getInstance().createStrategy(bean.getStrategy());
		strategy.execute(bean);
	}

	@Override
	public void validateExamination(Examination bean) throws QualityExecption {
		if(bean.getStrategy().length()<=0)bean.setStrategy(DEFAULT_STRATEGY);
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
