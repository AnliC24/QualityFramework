package com.eternalinfo.tern.test.examination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 王诚沣
 * @下午3:07:45
 * @description	数据源类型 对象
 * @version
 */
public class DefaultDbObject extends Examination{
	
	@JsonProperty("JDBC")
	private String jdbc;
	
	@JsonProperty("CHECK_DMOD_INFO")
	private List<Map<String, Object>> check;
	

	private static String objectName = "DMOD_EN_NAME";
	
	public DefaultDbObject() {
		this.jdbc = "METADB";
		this.check = new ArrayList<Map<String,Object>>();
	}
	
	public DefaultDbObject(String jdbc,List<Map<String, Object>> check) {
		this.jdbc = jdbc;
		this.check = check;
	}
	
	public String getJdbc() {
		return jdbc;
	}

	public void setJdbc(String jdbc) {
		this.jdbc = jdbc;
	}

	public List<Map<String, Object>> getCheck() {
		return check;
	}

	public void setCheck(List<Map<String, Object>> check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return String.join(",", check.stream().map(item->{ 
			return item.get(objectName).toString();
		}).collect(Collectors.toList()));
	}
	
}
