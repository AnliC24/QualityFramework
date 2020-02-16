package com.eternalinfo.tern.test.arithmetic.normalization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.Arithmetic;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
import com.eternalinfo.tern.test.context.ExecuteStrategy;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.Examination;

/**
 * @author 王诚沣
 * @下午4:14:22
 * @description	数据格式检查
 * @version
 */
public abstract class DataFormat extends Arithmetic{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	protected String DEFAULT_STRATEGY = "DefaultDataFormat";
	
	protected String DEFAULT_RESOURCE_URL = "DataFormat";
	
	protected String DEFAULT_SQL_TYPE = "DefaultDataFormat";
	
	
	@Override
	public void validateExamination(Examination bean) throws QualityExecption {	
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

	@Override
	public void setStrategy() throws QualityExecption {
		if(bean.getStrategy().length()<=0)bean.setStrategy(ExecuteStrategy.getExecuteStrategy(DEFAULT_STRATEGY));
	}
}
