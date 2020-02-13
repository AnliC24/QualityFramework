package com.eternalinfo.tern.test.arithmetic.normalization.factory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.Arithmetic;
import com.eternalinfo.tern.test.arithmetic.factory.Factory;
import com.eternalinfo.tern.test.arithmetic.normalization.DataFormat;
import com.eternalinfo.tern.test.arithmetic.normalization.DefaultDataFormat;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午4:28:39
 * @description
 * @version
 */
public class DataFormatFactory extends Factory{
	
	private static DataFormatFactory factory = new DataFormatFactory();
	private static Map<String, DataFormat> dataFormatPack = new ConcurrentHashMap<String, DataFormat>();
	
	static {
		try {
			dataFormatPack.put("DefaultDataFormat", new DefaultDataFormat());
		} catch (QualityExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createArithmetic(String type) throws QualityExecption, ExecuteException, IOException {
		LOG.warn("检核对象为空");
		doExecute(null);
	}
	
	@Override
	public void createArithmetic(Examination bean) throws QualityExecption, ExecuteException, IOException {
		doExecute(bean);
	}

	@Override
	public void remove(String type) {
		dataFormatPack.remove(type);
	}
	
	public static DataFormatFactory getInstance() {
		return factory;
	}
	
	private void doExecute(Examination bean) throws QualityExecption, ExecuteException, IOException {
		if(!dataFormatPack.containsKey(bean.getTypeArithmetic())) {
			throw new ArithmeticException("数据格式检查无"+bean.getTypeArithmetic()+"类型算子");
		}
		Arithmetic arithmetic = (Arithmetic) dataFormatPack.get(bean.getTypeArithmetic());
		arithmetic.setExamination(bean);
		arithmetic.execute();
	}
	
}
