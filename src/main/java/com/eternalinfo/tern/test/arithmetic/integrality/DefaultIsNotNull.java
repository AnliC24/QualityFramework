package com.eternalinfo.tern.test.arithmetic.integrality;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.execute.db.DefaultDbExecute;
import com.eternalinfo.tern.test.execute.factory.ExecuteFactory;

/**
 * @author 王诚沣
 * @下午2:21:53
 * @description	非空算子 数据源类型默认检查
 * @version
 */
public class DefaultIsNotNull extends IsNotNull{
	
	private final String IS_NOT_NULL_SQL = "SELECT * FROM ${DMOD_EN_NAME} TMDCI WHERE ${COL_EN_NAME} IS NOT NULL";
	
	private Examination bean;
	private String DEFAULT_TYPE_EXECUTE = "DefaultDbExecute";  //默认数据源执行策略

	public DefaultIsNotNull() throws QualityExecption {
		
	}
	
	@Override
	public void execute() throws QualityExecption {
		DefaultDbExecute execute = (DefaultDbExecute)ExecuteFactory.getInstance().createExecute(DEFAULT_TYPE_EXECUTE);
		execute.setExecuteSql(IS_NOT_NULL_SQL);	//执行sql
		execute.setDefaultDbObject(bean);		//检查对象
		execute.execute();
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}
}
