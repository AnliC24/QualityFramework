package com.eternalinfo.tern.test.arithmetic.integrality;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.context.ExecuteStrategy;
import com.eternalinfo.tern.test.examination.Examination;
/**
 * @author 王诚沣
 * @下午2:21:53
 * @description	非空算子 数据源类型默认检查
 * @version
 */
public class DefaultIsNotNull extends IsNotNull{

	public DefaultIsNotNull() throws QualityExecption{
	}
	
	public DefaultIsNotNull(Examination bean) throws QualityExecption {
		super.bean = super.bean;
	} 
	
	public DefaultIsNotNull(String resourceUrl,String sqlType) throws QualityExecption {
		if(super.bean == null) {
			throw new ArithmeticException("检核对象为空,无法进行算子检核");
		}
		super.bean.setResourceUrl(resourceUrl);
		super.bean.setSqlType(sqlType);
		super.bean.setStrategy(ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull"));
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType,Examination bean) throws QualityExecption {
		super.bean = bean;
		super.bean.setResourceUrl(resourceUrl);
		super.bean.setSqlType(sqlType);
		super.bean.setStrategy(ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull"));
	}
	
}
