package com.eternalinfo.tern.arithmetic.integrality.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.eternalinfo.tern.examination.beans.DbCheckBean;

/**
 * @author 王诚沣
 * @下午1:04:03
 * @description 非空对象
 * @version
 */
public class DefaultIsNotNullCheckBean implements Serializable{

	private static final long serialVersionUID = 1808142920531017386L;
	
	private String jdbc;
	private String sql = "select * from ${DMOD_EN_NAME} TMDCI WHERE ${COL_EN_NAME} IS NOT NULL"; 
	private List<Map<String, Object>> checkObject;
	
	static String objectName = "DMOD_EN_NAME";
	
	public DefaultIsNotNullCheckBean(DbCheckBean bean) {
		this.jdbc = bean.getJdbc();
		this.checkObject = bean.getCheckObject();
	}
	
	public String getJdbc() {
		return jdbc;
	}

	public String getSql() {
		return sql;
	}

	public List<Map<String, Object>> getCheckObject() {
		return checkObject;
	}


	@Override
	public String toString() {
		return String.join(",", checkObject.stream().map(item->{ 
			return item.get(objectName).toString();
		}).collect(Collectors.toList()));
	}
	
	public String toReport() {
		return String.join(",", checkObject.stream().map(item->{ 
			return reportHandler(item);
		}).collect(Collectors.toList()));
	}
	
	public String reportHandler(Map<String, Object> item) {
		return item.get(objectName).toString()+","+item.get("executeSql").toString()+","+item.get("executeRunTime");
	}
	
}
