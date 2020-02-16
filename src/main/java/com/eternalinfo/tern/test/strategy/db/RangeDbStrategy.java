package com.eternalinfo.tern.test.strategy.db;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
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
		LOG.info("取值范围执行策略");
	}

	@Override
	public void setJdbcTemplate(Examination bean) throws QualityExecption {
		if(bean != null) {
			jdbc = new JdbcTemplate(this.bean.getJdbc());
			if(jdbc.getDataSource() == null) {
				throw new QualityExecption("Spring容器为空，无法获取beanName:"+this.bean.getJdbc());
			}
		}
	}

	@Override
	public void setExamination(Examination bean) throws QualityExecption {
		if(!(bean instanceof RangeCheck)) {
			throw new QualityExecption("取值范围执行策略不支持其他检核对象");
		}
		this.bean = (RangeCheck)bean;
	}

	@Override
	public void setStrategySql() throws IOException {
		executeSql = "SELECT ${COL_EN_NAME} FROM ${DMOD_EN_NAME} TMDCI WHERE ${COL_EN_NAME} > 0";
	}
	
}
