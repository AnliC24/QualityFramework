package com.module.frame.alarm.test.arithmetic;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.examination.DefaultDbObject;
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
	
	//把检核对象提取为抽象类属性 算子是否已经能正常运行
	@Test
	public void testDefaultDataFormat() throws QualityExecption, ExecuteException, IOException{
		DefaultDbObject bean = new DefaultDbObject();
		bean.setCheck(checkObject);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		int oldSize = bean.getCheck().get(0).size();
		ArithmeticFactory.getInstance().creator(bean);
		assertEquals(oldSize, bean.getCheck().get(0).size());
	}	
}
