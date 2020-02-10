package com.eternalinfo.tern.Impl.factory.check;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王诚沣
 * @下午10:38:14
 * @description  字段核验 默认实现
 * @version
 */
public abstract class DefaultColCheckFactory implements ColCheckFactory{

	public Map<String, Object> stdMap;   //标准对象
	public Map<String, Object> checkMap; //比对对象
	
	List<String> ERROR_MESSAGE = new ArrayList<>();	  //返回错误信息
	List<String> SUCCESS_MESSAGE = new ArrayList<>(); //返回正确信息
	
	public DefaultColCheckFactory(Map<String, Object> stdMap,Map<String, Object> checkMap) {
		this.stdMap = stdMap;
		this.checkMap = checkMap;
	}
	
	@Override
	public void checkColType() {
		String STD_RANGE_DATA_TYPE = stdMap.get("RANGE_DATA_TYPE").toString();	
		this.checkColTypeStd(STD_RANGE_DATA_TYPE);
		if (!checkMap.get("COL_TYPE").toString().equalsIgnoreCase(STD_RANGE_DATA_TYPE)) {
			ERROR_MESSAGE.add("字段类型不匹配");
			return;
		}
		SUCCESS_MESSAGE.add("字段类型匹配");
	}

	@Override
	public void checkColLen() {
		if (!checkMap.get("COL_LEN").toString().equalsIgnoreCase(stdMap.get("RANGE_DATA_LENGTH").toString())) {
			ERROR_MESSAGE.add("字段长度不匹配 ");
			return;
		}
		SUCCESS_MESSAGE.add("字段长度匹配");
	}
	
	
	/**
	 * 	字段标准匹配
	 * */
	@SuppressWarnings("unchecked")
	private void checkColTypeStd(String STD_RANGE_DATA_TYPE) {	
		if(((List<Map<String,Object>>)checkMap.get("ColTypeToStdType")).size()>0){
			for(Map<String,Object> stdType : (List<Map<String,Object>>)checkMap.get("ColTypeToStdType")){	
				if(STD_RANGE_DATA_TYPE.equalsIgnoreCase(stdType.get("STD_TYPE").toString())){
					STD_RANGE_DATA_TYPE = stdType.get("DATA_TYPE").toString();
         			break;
         		}
			}
		}else {
			ERROR_MESSAGE.add("字段类型未进行标准映射匹配");
		}
	}

}
