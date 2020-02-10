package com.eternalinfo.tern.Impl;

import java.util.HashMap;
import java.util.Map;

import com.eternalinfo.tern.Impl.factory.check.BaseColCheckFactory;

/**
 * @author 王诚沣
 * @下午11:15:06
 * @description 基础标准字段核验实现类
 * @version
 */
public class BaseColCheck extends BaseColCheckFactory{
			
	public static class Builder {
		private  Map<String, Object> stdMap = new HashMap<>();
		private  Map<String, Object> checkMap = new HashMap<>();
	
		public Builder stdMap(Map<String, Object> val) {
			stdMap = val;return this;
		}
		public Builder checkMap(Map<String, Object> val) {
			checkMap = val;return this;
		}
		public BaseColCheck build() {
			return new BaseColCheck(this);
		}
	}
	
	private BaseColCheck(Builder builder) {
		super(builder.stdMap,builder.checkMap);
		if(super.isFind()) {
			checkColType();
			checkColLen();
			checkColEnName();
			currentColCheckBaseResult();
		}
	}
	
	@Override
	public void checkColEnName() {
		if (!checkMap.get("COL_EN_NAME").toString().equalsIgnoreCase(stdMap.get("STD_EN_NAME").toString())) {
			super.getErrorMessage().add("字段英文名不匹配");
			checkMap.put("base_errorMsg", String.join(",",super.getErrorMessage()));
			return;
		}
		super.getSuccessMessage().add("字段英文名匹配");
		checkMap.put("base_successMsg", String.join(",",super.getSuccessMessage()));
	}

	
	public boolean isFind() {
		return super.isFind();
	}
	
	public void currentColCheckBaseResult() {
		if(checkMap.get("base_errorMsg").toString().length()>0) {
			checkMap.put("BASE_CHECK_STATE","2");
			return;
		}
		checkMap.put("BASE_CHECK_STATE","1");
	}
}
