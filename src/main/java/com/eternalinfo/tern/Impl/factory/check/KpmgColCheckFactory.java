package com.eternalinfo.tern.Impl.factory.check;

import java.util.List;
import java.util.Map;

/**
 * @author 王诚沣
 * @下午12:58:31
 * @description 指标标准核验工厂
 * @version
 */
public abstract class KpmgColCheckFactory extends DefaultColCheckFactory{
	
	boolean isFind = false;
	
	public KpmgColCheckFactory(Map<String, Object> stdMap, Map<String, Object> checkMap) {
		super(stdMap, checkMap);
		this.colAppendkpmgResult();
	}
	
	//指标表达式匹配
	public abstract void checkColComment();
	
	
	
	
	public void colAppendkpmgResult() {
		String checkName = checkMap.get("DMOD_EN_NAME").toString()+"."+String.valueOf(checkMap.get("COL_EN_NAME"));
		String checkKpmgEnName = stdMap.get("INDEX_SRC_MOD_NAME").toString()+"."+String.valueOf(stdMap.get("INDEX_SRC_COL_NAME"));
		if(checkName.equalsIgnoreCase(checkKpmgEnName)) {
			checkMap.put("INDEX_SRC_COL_NAME",stdMap.get("INDEX_SRC_MOD_NAME").toString());
		    checkMap.put("EXPRESSION",stdMap.get("EXPRESSION").toString());
			checkMap.put("KPMG_SCOPE", stdMap.get("SCOPE"));
			checkMap.put("KPMG_SOURCE_SYSTEM", stdMap.get("SOURCE_SYSTEM"));
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
