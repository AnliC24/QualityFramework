package com.eternalinfo.tern.test.arithmetic.normalization;

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
 * @下午4:15:23
 * @description	数据源执行策略 数据格式检查
 * @version
 */
public class DefaultDataFormat extends DataFormat{
	
	private String url;
	
	private String sqlType;
	
	private Examination bean; 
	
	private String EXECUTE_STRATEGY;
	
	public DefaultDataFormat() throws QualityExecption {
		this.url = ResourceUrl.getUrlType("DataFormat");
		this.sqlType = ExecuteSqlType.getSqlType("DefaultDataFormat");
		this.EXECUTE_STRATEGY = ExecuteStrategy.getExecuteStrategy("DefaultIsNotNull");
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		DefaultDbExecute execute = (DefaultDbExecute)ExecuteFactory.getInstance().createExecute(EXECUTE_STRATEGY);
		execute.setResourceUrl(url);
		execute.setArithmeticType(sqlType);
		execute.setDefaultDbObject(bean);
		execute.execute();
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}

	@Override
	public void setExecuteStrategy(String strategy) {
		this.EXECUTE_STRATEGY = strategy;
	}
	
}
