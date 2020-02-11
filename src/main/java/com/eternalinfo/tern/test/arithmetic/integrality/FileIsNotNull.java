package com.eternalinfo.tern.test.arithmetic.integrality;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;

/**
 * @author 王诚沣
 * @下午7:28:11
 * @description	非空算子 --- 文件类型 检查
 * @version
 */
public class FileIsNotNull extends IsNotNull{
	
	private Examination bean;
	public FileIsNotNull() {}
	@Override
	public void execute() throws QualityExecption {
		LOG.info("文件类型非空检查");
	}

	@Override
	public void setExamination(Examination bean) {
		this.bean = bean;
	}
	@Override
	public void setExecuteStrategy(String strategy) {
	}
	
}
