package com.eternalinfo.tern.test.arithmetic.integrality;

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
 * @下午2:21:03
 * @description
 * @version
 */
public abstract class IsNotNull extends Arithmetic{
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	private String DEFAULT_STRATEGY = "DefaultIsNotNull";
	
	private String DEFAULT_RESOURCE_URL = "IsNotNull";
	
	private String DEFAULT_SQL_TYPE = "DefaultIsNotNull";

	@Override
	public void validateExamination(Examination bean) throws QualityExecption {	
		if(super.bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE)||
				super.bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE.toLowerCase())||
				super.bean.getClass().getName().contains(DEFAULT_VALIDATE_TYPE.toUpperCase()))
		{
			if(super.bean.getResourceUrl().length()<=0) {
				super.bean.setResourceUrl(ResourceUrl.getUrlType(DEFAULT_RESOURCE_URL));
			}
			if(super.bean.getSqlType().length()<=0) {
				super.bean.setSqlType(ExecuteSqlType.getSqlType(DEFAULT_SQL_TYPE));
			}
		}
	}
	
	@Override
	public void setStrategy() throws QualityExecption {
		if(super.bean.getStrategy().length()<=0)super.bean.setStrategy(ExecuteStrategy.getExecuteStrategy(DEFAULT_STRATEGY));
	}
}
