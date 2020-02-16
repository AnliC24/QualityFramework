package com.module.frame.alarm.test.arithmetic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.context.ExecuteSqlType;
import com.eternalinfo.tern.test.context.ExecuteStrategy;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.DefaultDbObject;
import com.eternalinfo.tern.test.exception.ExecuteException;
import com.module.frame.alarm.test.BaseTest;

/**
 * @author 王诚沣
 * @下午5:50:50
 * @description
 * @version
 */
@SpringBootTest
public class ArithmeticTest extends BaseTest{

	String JDBC = "ORACL";
	String TYPE_FACTORY = "IsNotNull";
	String TYPE_ARITHMETIC = "DefaultIsNotNull";
	String TEST_NEW_DBEXECUTE = "NewIsNotNull";
	String TEST_RESOURCE_URL = "com/eternalinfo/tern/test/resource/is-not-null.properties";
	String TEST_EXECUTE_SQL_TYPE = "defaultIsNotNull";
	String TEST_EXECUTE_STRATEGY = "DefaultDbExecute";
	
	
	/**
	 * 	变更算子 检核对象为 抽象类属性,测试是否可以正常执行
	 * 	变更算子execute 抽象方法 为 具体实现类 
	 * */
	@Test
	public void testExaminationFactory() throws QualityExecption, ExecuteException, IOException {
		DefaultDbObject bean = new DefaultDbObject();
		bean.setCheck(checkObject);
		bean.setTypeArithmetic(TYPE_ARITHMETIC);
		bean.setTypeFactory(TYPE_FACTORY);
		int oldSize = bean.getCheck().get(0).size();
		ArithmeticFactory.getInstance().creator(bean);
		assertEquals(oldSize, bean.getCheck().get(0).size());
	}
	
	//是否可以注入新增算子模块
	@Test
	@Ignore
	public void testAppendArithmeticIsNotNullFactory() {
//		IsNotNull bean = new NewIsNotNull();
//		IsNotNullFactory.getInstance().registry("newNotNull", bean);
//		assertNotNull(IsNotNullFactory.getInstance().getIsNotNull("newNotNull"));
	}
	
//	//是否可以变更算子配置  日志出现检核对象为空  true
//	@Test
//	@Ignore
//	public void testChangeDefaultSqlIsNotNull() throws QualityExecption, ExecuteException, IOException {
//		DefaultIsNotNull bean = (DefaultIsNotNull)IsNotNullFactory.getInstance().getIsNotNull("DefaultIsNotNull");
//		bean.setSqlType("testIsNotNULL");
//		IsNotNullFactory.getInstance().registry("DefaultIsNotNull", bean);
//		IsNotNullFactory.getInstance().createArithmetic("DefaultIsNotNull");
//	}
	
	//是否可以进行枚举类默认配置读取
	@Test
	public void testResourceUrl() throws QualityExecption {
		assertNotNull(ResourceUrl.getUrlType(TYPE_FACTORY));
		assertEquals(ResourceUrl.getUrlType(TYPE_FACTORY),TEST_RESOURCE_URL);
	}
	
	//是否可以进行枚举类默认执行Sql读取
	@Test
	public void testExecuteSqlType() throws QualityExecption {
		assertNotNull(ExecuteSqlType.getSqlType(TYPE_ARITHMETIC));
		assertEquals(ExecuteSqlType.getSqlType(TYPE_ARITHMETIC),TEST_EXECUTE_SQL_TYPE);
	}
	
	//是否可以通过枚举类取得默认执行策略
	@Test
	public void testExecuteStrategy() throws QualityExecption {
		assertNotNull(ExecuteStrategy.getExecuteStrategy(TYPE_ARITHMETIC));
		assertEquals(ExecuteStrategy.getExecuteStrategy(TYPE_ARITHMETIC), TEST_EXECUTE_STRATEGY);
	}
	
}
