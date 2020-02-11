package com.module.frame.alarm.test.arithmetic;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.module.frame.alarm.test.BaseTest;

/**
 * @author 王诚沣
 * @下午4:36:28
 * @description	数据格式检查算子
 * @version
 */
@SpringBootTest
public class DataFormatTest extends BaseTest{
	
	private String JDBC = "METADB";
	
	private String TYPE_FACTORY = "DataFormat";
	private String TYPE_ARITHMETIC = "DefaultDataFormat";
	
	//ArithmeticFactory 工厂是否能生成 DataFormat 算子   true
	@Test
	@Ignore
	public void getDataFormateArithmetic() throws QualityExecption, ExecuteException, IOException {
		ArithmeticFactory.getInstance().creator(TYPE_FACTORY, TYPE_ARITHMETIC);
	}
	
}
