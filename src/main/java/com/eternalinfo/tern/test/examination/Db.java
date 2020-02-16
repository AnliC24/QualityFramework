package com.eternalinfo.tern.test.examination;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 王诚沣
 * @下午8:01:27
 * @description
 * @version
 */
public abstract class Db extends Examination{
	
	@JsonProperty("JDBC")
	protected String jdbc;
	
	@JsonProperty("CHECK_DMOD_INFO")
	protected List<Map<String, Object>> check;
	
	protected static String objectName = "DMOD_EN_NAME";
	
	public Db(String jdbc,List<Map<String, Object>> check) {
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
	
	
}
