package com.eternalinfo.tern.test.arithmetic.integrality;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
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
	
	private String url = "com/eternalinfo/tern/test/resource/arithmetic.properties";
	
	private String ArithmeticType = "defaultIsNotNull";

	private Examination bean;
	private String DEFAULT_TYPE_EXECUTE = "DefaultDbExecute";  //默认数据源执行策略

	public DefaultIsNotNull() throws QualityExecption {
		
	}
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		DefaultDbExecute execute = (DefaultDbExecute)ExecuteFactory.getInstance().createExecute(DEFAULT_TYPE_EXECUTE);
		execute.setResourceUrl(url);
		execute.setArithmeticType(ArithmeticType);
		execute.setDefaultDbObject(bean);		//检查对象
		execute.execute();
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}
}
