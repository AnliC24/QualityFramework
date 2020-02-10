package com.eternalinfo.tern.arithmetic;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.examination.beans.DbCheckBean;
import com.eternalinfo.tern.examination.beans.FileCheckBean;

/**
 * @author cfwang
 * @time 2020/2/6
 * @description 算子
 * */
public interface Arithmetic {
	void check(DbCheckBean a) throws QualityExecption;
	
	void check(FileCheckBean a) throws QualityExecption;
}
