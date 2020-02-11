package com.eternalinfo.tern.test.arithmetic;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 *  @author cfwang
 * */
public interface Arithmetic {
	
	void execute() throws QualityExecption, ExecuteException, IOException;
	
	void setExamination(Examination bean);
}
