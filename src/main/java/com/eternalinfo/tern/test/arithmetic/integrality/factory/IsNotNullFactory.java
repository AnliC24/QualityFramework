package com.eternalinfo.tern.test.arithmetic.integrality.factory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.Arithmetic;
import com.eternalinfo.tern.test.arithmetic.factory.Factory;
import com.eternalinfo.tern.test.arithmetic.integrality.DefaultIsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.FileIsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.IsNotNull;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午7:12:26
 * @description	非空算子工厂
 * @version
 */
public class IsNotNullFactory extends Factory{
	private static IsNotNullFactory  factory = new IsNotNullFactory();
	private static Map<String, IsNotNull> isNotNullPack = new ConcurrentHashMap<String, IsNotNull>();
	
	static {
		isNotNullPack.put("DefaultIsNotNull", new DefaultIsNotNull());
		isNotNullPack.put("FileIsNotNull", new FileIsNotNull());
	}
	
	public static IsNotNullFactory getInstance() {
		return factory;
	}
	
	public void createArithmetic(String type) throws QualityExecption, ExecuteException, IOException {
		LOG.warn("检核对象为空");
		doExecute(null,type);
	}
	
	public void createArithmetic(Examination bean,String type) throws QualityExecption, ExecuteException, IOException {
		doExecute(bean,type);
	}
	
	private void doExecute(Examination bean,String type) throws QualityExecption, ExecuteException, IOException {
		if(!isNotNullPack.containsKey(type)) {
			throw new ArithmeticException("非空检查无"+type+"类型算子");
		}
		Arithmetic arithmetic = (Arithmetic) isNotNullPack.get(type);
		arithmetic.setExamination(bean);
		arithmetic.execute();
	}

	@Override
	public void remove(String type) {
		isNotNullPack.remove(type);
	}

	public void registry(String type, IsNotNull arithmetic) {
		isNotNullPack.put(type, arithmetic);
	}
	
	public IsNotNull getIsNotNull(String type) {
		if(!isNotNullPack.containsKey(type)) {
			return null;
		}
		return isNotNullPack.get(type);
	}
	
}
