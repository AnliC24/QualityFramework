package com.module.frame.alarm.test.arithmetic;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.examination.DataFormat;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.StrategyFactory;
import com.eternalinfo.tern.test.strategy.db.DataFormatDbStrategy;
import com.module.frame.alarm.test.BaseTest;

/**
 * @author 王诚沣
 * @下午4:36:28
 * @description	数据格式检查算子
 * @version
 */
@SpringBootTest
public class DataFormatTest extends BaseTest{
	
	private String TYPE_FACTORY = "DataFormat";
	private String TYPE_ARITHMETIC = "DefaultDataFormat";
	
	String TEST_EXECUTE_STRATEGY = "DataFormatStrategy";
	
	protected static  List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
	
	static {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("TYPE", "COL_LEN");
		aMap.put("COMPARE_TYPE", "=");
		aMap.put("COMPARE_VALUE", "1,2,3");
		params.add(aMap);
	}
	
	
	public void registryStrategy() {
		StrategyFactory.getInstance().registry(TEST_EXECUTE_STRATEGY, new DataFormatDbStrategy());
	}
	
	//把检核对象提取为抽象类属性 算子是否已经能正常运行
	@Test
	public void testDefaultDataFormat() throws QualityExecption, ExecuteException, IOException{
		registryStrategy();
		DataFormat bean = new DataFormat();
		bean.setCheck(checkObject);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		bean.setStrategy(TEST_EXECUTE_STRATEGY);
		bean.setCompareParams(params);
		int oldSize = bean.getCheck().get(0).size();
		ArithmeticFactory.getInstance().creator(bean);
		assertEquals(oldSize, bean.getCheck().get(0).size());
	}	
	
	
}
