package com.eternalinfo.tern.test.arithmetic.integrality;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
import com.eternalinfo.tern.test.context.ExecuteStrategy;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.execute.db.DefaultDbExecute;
import com.eternalinfo.tern.test.execute.factory.ExecuteFactory;

/**
 * @author 王诚沣
 * @下午2:21:53
 * @description	非空算子 数据源类型默认检查
 * @version
 */
public class DefaultIsNotNull extends IsNotNull{
	
	private String url;
	
	private String sqlType;

	private Examination bean;
	
	private String EXECUTE_STRATEGY;  //默认数据源执行策略

	public DefaultIsNotNull() throws QualityExecption{
		this.url = ResourceUrl.getUrlType("IsNotNull");
		this.sqlType = ExecuteSqlType.getSqlType("DefaultIsNotNull");
		this.EXECUTE_STRATEGY = ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull");
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType) throws QualityExecption {
		url = resourceUrl;
		this.sqlType = sqlType;
		this.EXECUTE_STRATEGY = ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull");
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType,Examination bean) throws QualityExecption {
		url = resourceUrl;
		this.sqlType = sqlType;
		this.bean = bean;
		this.EXECUTE_STRATEGY = ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull");
	}
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		DefaultDbExecute execute = (DefaultDbExecute)ExecuteFactory.getInstance().createExecute(EXECUTE_STRATEGY);
		execute.setResourceUrl(url);
		execute.setArithmeticType(sqlType);
		execute.setDefaultDbObject(bean);		//检查对象
		execute.execute();
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	@Override
	public void setExecuteStrategy(String strategy) {
		this.EXECUTE_STRATEGY = strategy;
	}
}
