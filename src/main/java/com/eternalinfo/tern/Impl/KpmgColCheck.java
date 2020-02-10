package com.eternalinfo.tern.Impl;

import java.util.HashMap;
import java.util.Map;

import com.eternalinfo.tern.Impl.factory.check.KpmgColCheckFactory;

/**
 * @author 王诚沣
 * @下午1:02:57
 * @description 
 * @version
 */
public class KpmgColCheck extends KpmgColCheckFactory{
		
	public static class Builder{
		private Map<String, Object> stdMap = new HashMap<>();
		private Map<String, Object> checkMap = new HashMap<>();
		
		public Builder stdMap(Map<String, Object> val) {
			stdMap = val;return this;
		}
		
		public Builder checkMap(Map<String, Object> val) {
			checkMap = val;return this;
		}

		public KpmgColCheck build() {
			return new KpmgColCheck(this);
		}
	}

	public KpmgColCheck(Builder builder) {
		super(builder.stdMap, builder.checkMap);
		if(super.isFind()) {
			checkColType();
			checkColLen();
			checkColComment();
			currentColCheckKpmgResult();
		}
	}

	@Override
	public void checkColComment() {
		if (!checkMap.get("COL_COMMENT").toString().equalsIgnoreCase(stdMap.get("EXPRESSION").toString())) {
			super.getErrorMessage().add("指标表达式不匹配");
			checkMap.put("kpmg_errorMsg", String.join(",",super.getErrorMessage()));
			return;
		}
		super.getSuccessMessage().add("指标表达式匹配");
 		checkMap.put("kpmg_successMsg", String.join(",",super.getSuccessMessage()));
	}


	public boolean isFind() {
		return super.isFind();
	}
	
	public void currentColCheckKpmgResult() {
		if(checkMap.get("kpmg_errorMsg").toString().length()>0) {
			checkMap.put("KPMG_CHECK_STATE", "2");
			return;
		}else {
			checkMap.put("KPMG_CHECK_STATE", "1");
		}
	}
}
