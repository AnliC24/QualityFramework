package com.eternalinfo.tern.test.strategy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午5:06:28
 * @description
 * @version
 */
public abstract class DbStrategy extends Strategy{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	protected JdbcTemplate jdbc;
	
	protected String executeSql;
	
	@Override
	public void execute(Examination bean) throws QualityExecption, ExecuteException, IOException {
		setExamination(bean);
		setJdbcTemplate(bean);
		setStrategySql();
		strategy(bean);
	}
	
	public abstract void setExamination(Examination bean) throws QualityExecption;
	
	public abstract void strategy(Examination bean) throws IOException, QualityExecption, ExecuteException;
	
	public abstract void setJdbcTemplate(Examination bean) throws QualityExecption;

	public abstract void setStrategySql() throws IOException;
}