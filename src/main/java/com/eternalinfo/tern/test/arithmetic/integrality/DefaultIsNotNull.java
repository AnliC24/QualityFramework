package com.eternalinfo.tern.test.arithmetic.integrality;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
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
	
	private String DEFAULT_TYPE_EXECUTE = "DefaultDbExecute";  //默认数据源执行策略

	public DefaultIsNotNull() throws QualityExecption{
		this.url = ResourceUrl.getUrlType("IsNotNull");
		this.sqlType = ExecuteSqlType.getSqlType("DefaultIsNotNull");
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType) {
		url = resourceUrl;
		this.sqlType = sqlType;
	}
	
	public DefaultIsNotNull(String resourceUrl,String sqlType,Examination bean) {
		url = resourceUrl;
		this.sqlType = sqlType;
		this.bean = bean;
	}
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		DefaultDbExecute execute = (DefaultDbExecute)ExecuteFactory.getInstance().createExecute(DEFAULT_TYPE_EXECUTE);
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
}
