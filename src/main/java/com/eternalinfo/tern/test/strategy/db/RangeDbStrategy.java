package com.eternalinfo.tern.test.strategy.db;

import java.io.IOException;
import java.util.Map;

import com.eternalinfo.tern.test.examination.RangeCheck;
import com.eternalinfo.tern.test.standard.ReferenceStandard;
import com.eternalinfo.tern.test.strategy.DbStrategy;

/**
 * @author 王诚沣
 * @下午3:43:37
 * @description	取值范围执行策略
 * @version 2020/02/16
 */
public class RangeDbStrategy extends DbStrategy implements ReferenceStandard{
	
	private RangeCheck bean;
	
	@Override
	public void setStrategySql() throws IOException {
		this.bean = (RangeCheck)super.bean;
		StringBuffer sql = new StringBuffer("SELECT ${COL_EN_NAME} FROM ${ONS_OWN}.${DMOD_EN_NAME} ");
		sql.append("WHERE  ");
		executeSql = appendExecuteSql(sql);
	}
	
	private String appendExecuteSql(StringBuffer sql) {
		int i = 1;
		if(this.bean.getCompareParams() == null || this.bean.getCompareParams().size() <= 0) {
			throw new ArithmeticException("请配置取值范围算子检查配置");
		}
		for(Map<String,Object> params:this.bean.getCompareParams()) {
			for(String value:getCompareData(params.get("COMPARE_VALUE"))) {
				if(i != 1 && params.get("RELATION").toString().equals("")) {
					sql.append(" and ");
				}
				String COMPARE_VALUE_NAME = "COMPARE_VALUE"+i;
			    sql.append("  "+params.get("RELATION").toString()+" ");
				sql.append("${COL_EN_NAME} ");
				sql.append(params.get("COMPARE_TYPE").toString());
				sql.append(" ${"+COMPARE_VALUE_NAME+"} ");
				appendBeanParams(COMPARE_VALUE_NAME,value);
				i++;
			}
		}
		LOG.info("取值范围算子执行sql:"+sql.toString());
		return sql.toString();
	}
	
	
	public void appendBeanParams(String compareValueName,String value) {
		for(Map<String, Object> item:this.bean.getCheck()) {
			item.put(compareValueName, value);
		}
	}

	public String[] getCompareData(Object params) {
		String[] values = null;
		if("STD".equals(params.toString())) {
			//取标准接口的值
		}else {
			values = params.toString().split(",");
		}
		return values;
	}
}
