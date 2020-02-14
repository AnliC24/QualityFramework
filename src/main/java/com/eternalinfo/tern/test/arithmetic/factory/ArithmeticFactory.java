package com.eternalinfo.tern.test.arithmetic.factory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.integrality.factory.IsNotNullFactory;
import com.eternalinfo.tern.test.arithmetic.normalization.factory.DataFormatFactory;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;


/**
 * @author 王诚沣
 * @下午2:42:25
 * @description  算子抽象工厂
 * @version
 */
public  class ArithmeticFactory {
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	private static ArithmeticFactory factory = new ArithmeticFactory();
	private ArithmeticFactory() {};
	private static Map<String, Factory> arithmeticPack = new ConcurrentHashMap<String, Factory>();
	static {
		arithmeticPack.put("IsNotNull", IsNotNullFactory.getInstance());
		arithmeticPack.put("DataFormat", DataFormatFactory.getInstance());
	}
		
	public static ArithmeticFactory getInstance() {
		return factory;
	}
	
	public Factory creator(String typeFactory) throws QualityExecption {
		if(!arithmeticPack.containsKey(typeFactory)) {
			throw new  ArithmeticException("无"+typeFactory+"类型算子工厂");
		}
		return  (Factory) arithmeticPack.get(typeFactory);
	}
	
	public void creator(Examination bean) throws QualityExecption, ExecuteException, IOException {
		doCreator(bean);
	}
	
	private void doCreator(Examination bean) throws QualityExecption, ExecuteException, IOException {
		if(bean == null) {
			throw new QualityExecption("检核对象为空");
		}
		Factory factory  = (Factory) arithmeticPack.get(bean.getTypeFactory());
		factory.createArithmetic(bean);
	}
	
	public void remove(String typeFactory) {
		arithmeticPack.remove(typeFactory);
	}
	
	public void registry(String typeFactory,Factory factory) {
		arithmeticPack.put(typeFactory,factory);
	}

}
