package com.eternalinfo.tern.examination.beans;

import java.util.List;
import java.util.Map;

import com.eternalinfo.tern.examination.Examination;

/**
 * @author 王诚沣
 * @下午1:56:14
 * @description
 * @version
 */
public  class DbCheckBean implements Examination{
	
	private String jdbc;
	private List<Map<String, Object>> checkObject;
	
	public DbCheckBean(String jdbc,List<Map<String, Object>> checkObject) {
		this.jdbc = jdbc;
		this.checkObject = checkObject;
	}
	
	public String getJdbc() {
		return jdbc;
	}

	public void setJdbc(String jdbc) {
		this.jdbc = jdbc;
	}

	public  List<Map<String, Object>> getCheckObject() {
		return checkObject;
	}

	public  void setCheckObject(List<Map<String, Object>> checkObject) {
		this.checkObject = checkObject;
	}
	
	
}
