package com.eternalinfo.tern.test.examination;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 王诚沣
 * @下午3:44:38
 * @description	取值范围检查对象
 * @version
 */
public class RangeCheck extends Examination{
	
	private List<Map<String,Object>> compareParams;
	
	private List<Map<String,Object>> checkInfo;
	
	@JsonProperty("JDBC")
	private String jdbc;

	public List<Map<String, Object>> getCompareParams() {
		return compareParams;
	}

	public void setCompareParams(List<Map<String, Object>> compareParams) {
		this.compareParams = compareParams;
	}

	public List<Map<String, Object>> getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(List<Map<String, Object>> checkInfo) {
		this.checkInfo = checkInfo;
	}

	public String getJdbc() {
		return jdbc;
	}

	public void setJdbc(String jdbc) {
		this.jdbc = jdbc;
	}

}
