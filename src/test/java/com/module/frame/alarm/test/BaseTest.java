package com.module.frame.alarm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 王诚沣
 * @下午3:53:41
 * @description
 * @version
 */
public abstract class BaseTest {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	protected static  List<Map<String, Object>> checkObject = new ArrayList<Map<String,Object>>();
	
	static {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("ONS_OWN", "tern_gwxy");
		aMap.put("DMOD_EN_NAME", "tn_meta_dmod_col_info");
		aMap.put("COL_EN_NAME", "COL_LEN");
		checkObject.add(aMap);
	}
}
