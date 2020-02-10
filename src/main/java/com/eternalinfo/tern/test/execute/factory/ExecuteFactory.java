package com.eternalinfo.tern.test.execute.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.execute.Execute;
import com.eternalinfo.tern.test.execute.db.DefaultDbExecute;
import com.eternalinfo.tern.test.execute.db.NewDbExecute;


/**
 * @author 王诚沣
 * @下午2:59:18
 * @description
 * @version
 */
public class ExecuteFactory {
	private static ExecuteFactory executeFactory = new ExecuteFactory();
	private static Map<String, Execute> executePack = new ConcurrentHashMap<String, Execute>();
	static {
		executePack.put("DefaultDbExecute",new DefaultDbExecute());
		executePack.put("NewDbExecute", new NewDbExecute());
	}
	public static ExecuteFactory getInstance() {
		return executeFactory;
	}
	public Execute createExecute(String executeType) throws QualityExecption {
		return executePack.get(executeType);
	}
}
