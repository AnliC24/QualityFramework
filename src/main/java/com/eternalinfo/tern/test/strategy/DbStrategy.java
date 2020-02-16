package com.eternalinfo.tern.test.strategy;

import java.io.IOException;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.test.examination.Db;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午5:06:28
 * @description
 * @version
 */
public abstract class DbStrategy extends Strategy{
		
	protected JdbcTemplate jdbc;
	
	protected String executeSql;
	
	protected Db bean;

	@Override
	public void execute(Examination bean) throws QualityExecption, ExecuteException, IOException {
		vaildateExamination(bean);
		setJdbcTemplate(bean);
		setStrategySql();
		strategy(bean);
	}
	
	public void vaildateExamination(Examination bean) throws QualityExecption{
		if(!(bean instanceof Db)) {
			throw new QualityExecption("默认数据源执行策略不支持其他检核对象");
		}
		this.bean = (Db)bean;
	}
	
	public void setJdbcTemplate(Examination bean) throws QualityExecption{
		if(bean != null) {
			jdbc = new JdbcTemplate(this.bean.getJdbc());
			if(jdbc.getDataSource() == null) {
				throw new QualityExecption("Spring容器为空，无法获取beanName:"+this.bean.getJdbc());
			}
		}
	}
	
	public abstract void setStrategySql() throws IOException;
}