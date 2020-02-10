package com.eternalinfo.tern.Impl.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author 王诚沣
 * @下午1:47:29
 * @description  模型核验返回接口值
 * @version
 */
public class DmodColCheckResult implements Serializable{
	
	private static final long serialVersionUID = 9127199237713729729L;
	
	private final String checkResult;  //返回结果  核验成功 核验失败
	private final String checkScope;   //核验归属范围
	private final String checkSourceSystem; //核验来源系统
	private final List<Map<String, Object>> checkDmodResult; //核验模型结果集
	
	public static class Builder {
		private String checkResult = "";
		private String checkScope = "";
		private String checkSourceSystem = "";
		private List<Map<String, Object>> checkDmodResult = new ArrayList<Map<String,Object>>();
		
		public Builder CHECK_RESULT(String val) {
			checkResult = val; return this;
		}
		
		public Builder CHECK_SCOPE(String val) {
			checkScope = val; return this;
		}
		
		public Builder CHECK_SOURCE_SYSTEM(String val) {
			checkSourceSystem = val; return this;
		}
		
		public Builder CHECK_DMOD_RESULT(List<Map<String, Object>> val) {
			checkDmodResult = val; return this;
		}
		
		public DmodColCheckResult build() {
			return new DmodColCheckResult(this);
		}
	}
	
	private DmodColCheckResult(Builder builder) {
		checkResult = builder.checkResult;
		checkScope = builder.checkScope;
		checkSourceSystem = builder.checkSourceSystem;
		checkDmodResult = builder.checkDmodResult;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public String getCheckScope() {
		return checkScope;
	}

	public String getCheckSourceSystem() {
		return checkSourceSystem;
	}

	public List<Map<String, Object>> getCheckDmodResult() {
		return checkDmodResult;
	}

	
	
	
}
