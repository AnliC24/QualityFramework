package com.eternalinfo.tern.test.context;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;

public enum ExecuteSqlType {

	DEFAULT_IS_NOT_NULL("DefaultIsNotNull","defaultIsNotNull"),
	DEFAULT_DATA_FORMAT("DefaultDataFormat","defaultDataFormat");
	
	private String arithmeticName;
	private String sqlType;
	private ExecuteSqlType(String arithmeticName,String sqlType) {
		this.arithmeticName = arithmeticName;
		this.sqlType= sqlType;
	}
	public String getArithmeticName() {
		return arithmeticName;
	}
	public void setArithmeticName(String arithmeticName) {
		this.arithmeticName = arithmeticName;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	
	public static String getSqlType(String arithmeticName) throws QualityExecption {
		for(ExecuteSqlType sqlType : ExecuteSqlType.values()) {
			if(sqlType.getArithmeticName().equals(arithmeticName)) {
				return sqlType.getSqlType();
			}
		}
		throw new QualityExecption("未找到"+arithmeticName+"算子执行的sqlType");
	}
}
