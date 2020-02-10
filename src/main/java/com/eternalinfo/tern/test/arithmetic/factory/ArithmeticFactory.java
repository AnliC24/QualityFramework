package com.eternalinfo.tern.test.arithmetic.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.integrality.factory.IsNotNullFactory;
import com.eternalinfo.tern.test.examination.Examination;


/**
 * @author 王诚沣
 * @下午2:42:25
 * @description  算子抽象工厂
 * @version
 */
public  class ArithmeticFactory {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	private static ArithmeticFactory factory = new ArithmeticFactory();
	private ArithmeticFactory() {}
	private static Map<String, Factory> arithmeticPack = new ConcurrentHashMap<String, Factory>();
	static {
		arithmeticPack.put("IsNotNull", IsNotNullFactory.getInstance());
	}
		
	public static ArithmeticFactory getInstance() {
		return factory;
	}
	
	public Factory creator(String typeFactory) throws QualityExecption {
		return  (Factory) arithmeticPack.get(typeFactory);
	}
	
	public void creator(Examination bean,String typeFactory,String typeArithmetic) throws QualityExecption {
		doCreator(bean,typeFactory,typeArithmetic);
	}
	
	public void creator(String typeFactory,String typeArithmetic) throws QualityExecption {
		LOG.warn("检核对象为空");
		doCreator(null,typeFactory,typeArithmetic);
	}
	
	private void doCreator(Examination bean,String typeFactory,String typeArithmetic) throws QualityExecption {
		Factory factory  = (Factory) arithmeticPack.get(typeFactory);
		factory.createArithmetic(bean, typeArithmetic);
	}
	
	public void remove(String typeFactory) {
		this.arithmeticPack.remove(typeFactory);
	}
	
	public void registry(String typeFactory,Factory factory) {
		this.arithmeticPack.put(typeFactory,factory);
	}
}
