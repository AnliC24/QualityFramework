package com.eternalinfo.tern.test.strategy;

import java.io.IOException;
import java.util.Map;

import org.springframework.util.StopWatch;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.arithmetic.utils.QualityUtil;
import com.eternalinfo.tern.arithmetic.utils.SqlCommonUtils;
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
	
	private int sqlErrorCount = 0;

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

	@Override
	public void strategy(Examination bean) throws IOException, QualityExecption, ExecuteException {
		executeCore();
	}
	
	private void executeCore() throws QualityExecption, ExecuteException {
		transVariableToString();
		executeSql();
	}
	
	private void transVariableToString() throws QualityExecption {
		for(Map<String, Object> item:bean.getCheck()) {
			item.put("executeSql", QualityUtil.transVariableToString(executeSql, item));
		}
	}
	
	private void executeSql() throws ExecuteException {
		try {
			batchExecuteSql(jdbc);
		}catch (Exception e) {
			throw new ExecuteException("数据源连接失败");
		}
	}
	
	private int batchExecuteSql(JdbcTemplate jdbc) {
		return bean.getCheck().stream().map(item->{	
			StopWatch time = new StopWatch();
			try {
				time.start();
				LOG.info("执行SQL:"+item.get("executeSql").toString());
				item.put("totalSql", SqlCommonUtils.countSqlOp(item.get("ONS_OWN").toString(),item.get("DMOD_EN_NAME").toString()));
				item.put("totalCount", jdbc.queryForList(SqlCommonUtils.countSqlOp(item.get("ONS_OWN").toString(),item.get("DMOD_EN_NAME").toString()),Integer.class).get(0));
				item.put("failCount", jdbc.queryForList(SqlCommonUtils.countSqlOp(item.get("executeSql").toString()),Integer.class).get(0));
			} catch (Exception e) {
				sqlErrorCount++;
				item.put("executeExp", e.getMessage()!=null?e.getMessage().toString():"异常获取失败");
				LOG.info("执行SQL:"+item.get("executeSql").toString()+"执行异常:"+e.getMessage());
			}finally {
				time.stop();
				item.put("executeRunTime", time.getTotalTimeMillis());
				LOG.info("执行时间:"+time.getTotalTimeMillis());
			}
			return sqlErrorCount;
		}).reduce((current,next)->{return current+next;}).get();
	}
}