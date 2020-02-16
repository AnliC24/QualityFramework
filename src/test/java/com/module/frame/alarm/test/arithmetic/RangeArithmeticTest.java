package com.module.frame.alarm.test.arithmetic;

import java.io.IOException;
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
	
	private String STRATEGY = "RangeDbStrategy";
	
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
		bean.setCheckInfo(checkObject);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		ArithmeticFactory.getInstance().creator(bean);
	}
}
