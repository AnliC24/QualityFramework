package com.module.frame.alarm.test.arithmetic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.arithmetic.normalization.Range;
import com.eternalinfo.tern.test.examination.RangeCheck;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.eternalinfo.tern.test.strategy.StrategyFactory;
import com.eternalinfo.tern.test.strategy.db.RangeDbStrategy;
import com.module.frame.alarm.test.BaseTest;

/**
 * @author 王诚沣
 * @下午6:02:51
 * @description
 * @version
 */
@SpringBootTest
public class RangeArithmeticTest extends BaseTest{
	
	private String TYPE_FACTORY = "DataFormat";
	private String TYPE_ARITHMETIC = "Range";
	
	private String TEST_TYPE_FACTORY = "IsNotNull";
	private String TEST_TYPE_ARITHMETIC = "DefaultIsNotNull";
	
	private String STRATEGY = "RangeDbStrategy";
	
	protected static  List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
	
	static {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("RELATION", "");
		aMap.put("COMPARE_TYPE", "=");
		aMap.put("COMPARE_VALUE", "1,2,3");
		params.add(aMap);
	}
	
	public void registryArithmetic() throws QualityExecption {
		ArithmeticFactory.getInstance().creator(TYPE_FACTORY).registry(TYPE_ARITHMETIC, new Range());
	}
	
	public void registryStrategy() {
		StrategyFactory.getInstance().registry(STRATEGY, new RangeDbStrategy());
	}
	//把检核对象提取为抽象类属性 算子是否已经能正常运行
	@Test
	public void testDefaultDataFormat() throws QualityExecption, ExecuteException, IOException{
		registryArithmetic();
		registryStrategy();
		RangeCheck bean = new RangeCheck();
		bean.setCheck(checkObject);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		ArithmeticFactory.getInstance().creator(bean);
	}
	
	//测试 使用 DefaultIsNotNull 修改策略为Range 是否可以执行 range算子
	@Test
	public void testDefaultIsNotNullUseRangeDbStrategy() throws QualityExecption, ExecuteException, IOException {
		registryArithmetic();
		registryStrategy();
		RangeCheck bean = new RangeCheck();
		bean.setCheck(checkObject);
		bean.setTypeArithmetic(TEST_TYPE_ARITHMETIC);
		bean.setTypeFactory(TEST_TYPE_FACTORY);
		bean.setStrategy(STRATEGY);
		ArithmeticFactory.getInstance().creator(bean);
	}
	
	//测试 取值范围 执行sql 是否能正确生成  生成sql 在控制台打印出来
	@Test
	public void testCreateSql() throws QualityExecption, ExecuteException, IOException {
		registryArithmetic();
		registryStrategy();
		RangeCheck bean = new RangeCheck();
		bean.setCheck(checkObject);
		//bean.setCompareParams(params);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		ArithmeticFactory.getInstance().creator(bean);
	}
}
