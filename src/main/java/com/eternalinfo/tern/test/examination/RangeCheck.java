package com.eternalinfo.tern.test.examination;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author 王诚沣
 * @下午3:44:38
 * @description	取值范围检查对象
 * @version
 */
public class RangeCheck extends Db{
	
	public RangeCheck() {
		super("METADB", null);
	}
	
	public RangeCheck(String jdbc, List<Map<String, Object>> check) {
		super(jdbc, check);
	}
	
	@JsonProperty("RANGE_PARAMS")
	private List<Map<String,Object>> compareParams;
	
	public List<Map<String, Object>> getCompareParams() {
		return compareParams;
	}

	public void setCompareParams(List<Map<String, Object>> compareParams) {
		this.compareParams = compareParams;
	}


}
