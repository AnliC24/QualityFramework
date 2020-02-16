package com.eternalinfo.tern.test.examination;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author 王诚沣
 * @下午3:07:45
 * @description	数据源类型 对象
 * @version
 */
public class DefaultDbObject extends Db{
	
	public DefaultDbObject() {
		super("METADB", null);
	}
	
	public DefaultDbObject(String jdbc,List<Map<String, Object>> check) {
		super(jdbc, check);
	}

	@Override
	public String toString() {
		return String.join(",", super.check.stream().map(item->{ 
			return item.get(objectName).toString();
		}).collect(Collectors.toList()));
	}
}
