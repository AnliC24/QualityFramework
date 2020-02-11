package com.eternalinfo.tern.test.execute.db;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StopWatch;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.arithmetic.utils.QualityUtil;
import com.eternalinfo.tern.arithmetic.utils.SqlCommonUtils;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.test.examination.DefaultDbObject;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.execute.Execute;

/**
 * @author 王诚沣
 * @上午10:52:48
 * @description 数据源默认执行策略
 * @version
 */
public class DefaultDbExecute extends Execute{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	private Properties sqlProperties;
	
	private String arithmeticType;
	
	private String resourceUrl;
	
	private JdbcTemplate jdbc;
	private String executeSql;
	private DefaultDbObject executeObject;
	private int sqlErrorCount = 0;
	
	public DefaultDbExecute() {}
	
	public JdbcTemplate getJdbc() {
		return jdbc;
	}
	
	public void setArithmeticType(String type) {
		this.arithmeticType = type;
	}
	public void setResourceUrl(String url) {
		this.resourceUrl = url;
	}
	
	public void setDefaultDbObject(Examination bean) {
		executeObject = (DefaultDbObject)bean;
	}
	
	public void setExecuteSql() throws IOException {
		sqlProperties = Resources.getResourceAsProperties(this.resourceUrl);
		if(!sqlProperties.containsKey(this.arithmeticType)) {
			throw new ArithmeticException("请配置默认执行sql,"+"例如:"+this.arithmeticType+"=sql");
		}
		executeSql = sqlProperties.getProperty(this.arithmeticType);
	}
	
	@Override
	public void execute() throws QualityExecption, ExecuteException, IOException {
		setExecuteSql();
		this.jdbc = new JdbcTemplate(this.executeObject.getJdbc());
		executeCore();
		LOG.info("{"+executeObject.toString()+"} 执行非空检查");
	}
	
	private void executeCore() throws QualityExecption, ExecuteException {
		transVariableToString();
		executeSql();
	}
	
	private void transVariableToString() throws QualityExecption {
		for(Map<String, Object> item:executeObject.getCheck()) {
			item.put("executeSql", QualityUtil.transVariableToString(executeSql, item));
		}
	}
	
	private void executeSql() throws ExecuteException {
		try {
			batchExecuteSql(this.jdbc);
		}catch (Exception e) {
			throw new ExecuteException("数据源连接失败");
		}
	}
	
	private int batchExecuteSql(JdbcTemplate jdbc) {
		return executeObject.getCheck().stream().map(item->{	
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
	
	
	

}
