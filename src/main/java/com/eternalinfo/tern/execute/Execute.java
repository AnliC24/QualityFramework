package com.eternalinfo.tern.execute;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;

/**
 * 	@author cfwang
 *  @time 2020/2/8
 * */
public interface Execute {
	
	void executeDB() throws QualityExecption;
	
	Object toReport();
}
