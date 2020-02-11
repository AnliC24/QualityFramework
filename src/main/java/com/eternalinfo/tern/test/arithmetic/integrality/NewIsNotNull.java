package com.eternalinfo.tern.test.arithmetic.integrality;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.execute.db.NewDbExecute;
import com.eternalinfo.tern.test.execute.factory.ExecuteFactory;

/**
 * @author 王诚沣
 * @下午8:27:21
 * @description
 * @version
 */
public class NewIsNotNull extends IsNotNull{
	
	private Examination bean;
	private String DEFAULT_EXECUTE_TYPE = "NewDbExecute";
	
	@Override
	public void execute() throws QualityExecption {
		NewDbExecute execute = (NewDbExecute)ExecuteFactory.getInstance().createExecute(DEFAULT_EXECUTE_TYPE);
		execute.execute();
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}

	@Override
	public void setExecuteStrategy(String strategy) {
	}
	
}
