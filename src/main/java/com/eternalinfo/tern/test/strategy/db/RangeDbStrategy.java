package com.eternalinfo.tern.test.strategy.db;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.examination.RangeCheck;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.DbStrategy;

/**
 * @author 王诚沣
 * @下午3:43:37
 * @description	取值范围执行策略
 * @version 2020/02/16
 */
public class RangeDbStrategy extends DbStrategy{
	
	private RangeCheck bean;
	
	@Override
	public void strategy(Examination bean) throws IOException, QualityExecption, ExecuteException {
		LOG.info(this.bean.getTypeArithmetic()+"算子正在执行策略");
	}
	
	@Override
	public void setStrategySql() throws IOException {
		this.bean = (RangeCheck)super.bean;
		executeSql = "SELECT ${COL_EN_NAME} FROM ${DMOD_EN_NAME} TMDCI WHERE ${COL_EN_NAME} > 0";
	}
	
}
