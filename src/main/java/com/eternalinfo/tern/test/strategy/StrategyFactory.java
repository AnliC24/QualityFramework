package com.eternalinfo.tern.test.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.exception.ExecuteException;
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
		strategyPack.put("DefaultDbStrategy",new DefaultDbStrategy());
	}
	
	public static StrategyFactory getInstance() {
		return strategyFactory;
	}
	
	public Strategy createStrategy(String strategyName) throws QualityExecption, ExecuteException {
		if(!strategyPack.containsKey(strategyName)) {
			throw new ExecuteException("未找到"+strategyName+"类型执行策略");
		}
		return strategyPack.get(strategyName);
	}
	
	public void registry(String strategyName,Strategy strategy) {
		strategyPack.put(strategyName, strategy);
	}
	
	public void remove(String strategyName) {
		strategyPack.remove(strategyName);
	}
}
