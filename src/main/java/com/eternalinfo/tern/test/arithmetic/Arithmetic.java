package com.eternalinfo.tern.test.arithmetic;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;

/**
 *  @author cfwang
 * */
public interface Arithmetic {
	void execute() throws QualityExecption;
	
	void setExamination(Examination bean);
}
