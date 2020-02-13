package com.eternalinfo.tern.test.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.strategy.db.DefaultDbStrategy;



/**
 * @author 王诚沣
 * @下午2:59:18
 * @description
 * @version
 */
public class StrategyFactory {
	private static StrategyFactory strategyFactory = new StrategyFactory();
	private static Map<String, Strategy> strategyPack = new ConcurrentHashMap<String, Strategy>();
	static {
		strategyPack.put("DefaultDbExecute",new DefaultDbStrategy());
	}
	public static StrategyFactory getInstance() {
		return strategyFactory;
	}
	public Strategy createExecute(String executeType) throws QualityExecption {
		return strategyPack.get(executeType);
	}
}
