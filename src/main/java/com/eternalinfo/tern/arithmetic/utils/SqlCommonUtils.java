package com.eternalinfo.tern.arithmetic.utils;

import java.util.List;

/**
 * @author 王诚沣
 * @下午9:32:14
 * @description
 * @version
 */
public class SqlCommonUtils {
	
	private SqlCommonUtils() {
		throw new AssertionError();
	}
	
	public static  String countSqlOp(String sql) {	
		 StringBuilder executeSql = new StringBuilder("SELECT COUNT(1)  VALUE FROM ( ");
		 executeSql.append(sql);
		 executeSql.append(")row_");
		 return executeSql.toString();
	}
	
	public static String queryForManyContions(String queryContions,StringBuffer sql,List<Object> params)
	{
		String[] nodes = queryContions.split(",");
		if(queryContions.indexOf(",")!=-1)
		{
			String[] index = new String[queryContions.split(",").length];
			for(int j=0;j<queryContions.split(",").length;j++){
				index[j]="?";
			}
			sql.append(sql.toString().join(",",index));
			sql.append(") ");
		}else{
			//只有唯一条件
			sql.append("?) ");
		}
		for(int i=0;i<nodes.length;i++)
		{
			params.add(nodes[i]);
		}
		return sql.toString();
	}
	
	public static String countSqlOp(String ONS_OWN ,String DMOD_PURE_NAME) {
		 StringBuilder totalSql = new StringBuilder("SELECT COUNT(1) TOTAL FROM ");
		 totalSql.append(ONS_OWN);
		 totalSql.append(".");
		 totalSql.append(DMOD_PURE_NAME);
		 return totalSql.toString();
	}
}
