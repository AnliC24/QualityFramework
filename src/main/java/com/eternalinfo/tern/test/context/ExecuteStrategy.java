package com.eternalinfo.tern.test.context;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;

public enum ExecuteStrategy {
	
	DEFAULT_IS_NOT_NULL("DefaultIsNotNull","DefaultDbExecute"),
	DEFAULT_DATA_FORMAT("DefaultDataFormat","DefaultDbExecute");
	
	private String arithmeticName;
	private String executeStrategyName;
	private ExecuteStrategy(String arithmeticName,String executeStrategyName) {
		this.arithmeticName = arithmeticName;
		this.executeStrategyName = executeStrategyName;
	}
	public String getArithmeticName() {
		return arithmeticName;
	}
	public void setArithmeticName(String arithmeticName) {
		this.arithmeticName = arithmeticName;
	}
	public String getExecuteStrategyName() {
		return executeStrategyName;
	}
	public void setExecuteStrategyName(String executeStrategyName) {
		this.executeStrategyName = executeStrategyName;
	}
	
	public static String getExecuteStrategy(String arithmeticName) throws QualityExecption {
		for(ExecuteStrategy strategy:ExecuteStrategy.values()) {
			if(strategy.getArithmeticName().equals(arithmeticName)) {
				return strategy.getExecuteStrategyName();
			}
		}
		throw new QualityExecption("未找到该类型"+arithmeticName+"算子的执行策略");
	}
}
