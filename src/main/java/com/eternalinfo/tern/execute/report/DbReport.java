package com.eternalinfo.tern.execute.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王诚沣
 * @下午12:21:24
 * @description
 * @version
 */
public class DbReport extends QualityReport{
	private final List<Map<String, Object>> result;
	public static class Builder {
		private List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		public Builder resultBean(List<Map<String, Object>> val) {
			result = val;return this;
		}
		public DbReport build() {
			return new DbReport(this);
		}
	}
	
	private DbReport(Builder builder) {
		result = builder.result;
	}

	public List<Map<String, Object>> getResult() {
		return result;
	}	
	
	
	
}
