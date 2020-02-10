package com.eternalinfo.tern.Impl.factory.check;

import java.util.List;
import java.util.Map;

/**
 * @author 王诚沣
 * @下午11:03:01
 * @description 基础标准字段核验工厂
 * @version
 */
public abstract class BaseColCheckFactory extends DefaultColCheckFactory{
	
	boolean isFind = false;
	
	public BaseColCheckFactory(Map<String, Object> stdMap, Map<String, Object> checkMap) {
		super(stdMap, checkMap);
		this.colAppendBaseResult();
	}
	
	//字段英文名标准匹配
	public abstract void checkColEnName();
	
	
	public void colAppendBaseResult() {
		//判断是否匹配到标准
		if(String.valueOf(checkMap.get("COL_CN_NAME")).equalsIgnoreCase(String.valueOf(stdMap.get("STD_CN_NAME")))) {
			checkMap.put("STD_EN_NAME", stdMap.get("STD_EN_NAME"));
			checkMap.put("STD_CN_NAME", String.valueOf(stdMap.get("STD_CN_NAME")));
			checkMap.put("RANGE_DATA_TYPE", stdMap.get("RANGE_DATA_TYPE"));
			checkMap.put("RANGE_DATA_LENGTH", stdMap.get("RANGE_DATA_LENGTH"));
			checkMap.put("BASE_SCOPE", stdMap.get("SCOPE"));
			checkMap.put("BASE_SOURCE_SYSTEM", stdMap.get("SOURCE_SYSTEM"));
			isFind = true;
		}
	}

	public boolean isFind() {
		return isFind;
	}	
	
	public List<String> getErrorMessage() {
		return super.ERROR_MESSAGE;
	}
	
	public List<String> getSuccessMessage() {
		return super.SUCCESS_MESSAGE;
	}
}
