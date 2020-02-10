package com.eternalinfo.tern.execute;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StopWatch;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.arithmetic.integrality.beans.DefaultIsNotNullCheckBean;
import com.eternalinfo.tern.arithmetic.utils.QualityUtil;
import com.eternalinfo.tern.arithmetic.utils.SqlCommonUtils;
import com.eternalinfo.tern.jdbc.JdbcTemplate;



/**
 * @author 王诚沣
 * @上午10:52:48
 * @description 数据源执行环境
 * @version
 */
public class DbExecute implements Execute{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	private JdbcTemplate jdbc;
	private DefaultIsNotNullCheckBean bean;
	private int sqlErrorCount = 0;
	
    public DbExecute(DefaultIsNotNullCheckBean bean) {
    	this.jdbc = new JdbcTemplate(bean.getJdbc()); 
    	this.bean = bean;
    }

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	public DefaultIsNotNullCheckBean getDefaultIsNotNullCheck() {
		return bean;
	}

	@Override
	public void executeDB() throws QualityExecption {
		executeCore();
		LOG.info("{"+bean.toString()+"} 执行非空检查");
		LOG.info(bean.toReport());
	}
	
	private void executeCore() throws QualityExecption {
		transVariableToString();
		execute();
	}
	
	private void transVariableToString() throws QualityExecption {
		for(Map<String, Object> item:bean.getCheckObject()) {
			item.put("executeSql", QualityUtil.transVariableToString(bean.getSql(), item));
		}
	}
	
	private void execute() throws QualityExecption {
		try {
			batchExecuteSql(this.jdbc);
		}catch (Exception e) {
			throw new QualityExecption("数据源连接失败");
		}
	}
	
	private int batchExecuteSql(JdbcTemplate jdbc) {
		return bean.getCheckObject().stream().map(item->{	
			StopWatch time = new StopWatch();
			try {
				time.start();
				LOG.info("执行SQL:"+item.get("executeSql").toString());
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



	@Override
	public Object toReport() {
		return bean.getCheckObject();
	}
	
}
