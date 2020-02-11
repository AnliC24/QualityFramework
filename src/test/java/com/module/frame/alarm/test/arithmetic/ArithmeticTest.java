package com.module.frame.alarm.test.arithmetic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.arithmetic.integrality.DefaultIsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.IsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.NewIsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.factory.IsNotNullFactory;
import com.eternalinfo.tern.test.context.ResourceUrl;
import com.eternalinfo.tern.test.examination.DefaultDbObject;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @下午5:50:50
 * @description
 * @version
 */
@SpringBootTest
public class ArithmeticTest {

	protected  Logger LOG = LogManager.getLogger(this.getClass());
	String JDBC = "ORACL";
	String TYPE_FACTORY = "IsNotNull";
	String TYPE_ARITHMETIC = "DefaultIsNotNull";
	String TEST_NEW_DBEXECUTE = "NewIsNotNull";
	String TEST_RESOURCE_URL = "com/eternalinfo/tern/test/resource/arithmetic.properties";
	static  List<Map<String, Object>> checkObject = new ArrayList<Map<String,Object>>();
	
	static {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("DMOD_EN_NAME", "tn_meta_dmod_col_info");
		aMap.put("COL_EN_NAME", "COL_LEN");
		checkObject.add(aMap);
	}
	
	//是否可以执行对象工厂
	@Test
	public void testExaminationFactory() throws QualityExecption, ExecuteException, IOException {
		DefaultDbObject bean = new DefaultDbObject();
		ArithmeticFactory.getInstance().creator(bean,TYPE_FACTORY,TYPE_ARITHMETIC);
		assertNotEquals(bean, bean);
	}
	
	//是否可以执行算子策略
	@Test
	public void testNewDbExecute() throws QualityExecption, ExecuteException, IOException {
		ArithmeticFactory.getInstance().creator(TYPE_FACTORY,TEST_NEW_DBEXECUTE);
	}
	
	//是否可以注入新增算子模块
	@Test
	public void testAppendArithmeticIsNotNullFactory() {
		IsNotNull bean = new NewIsNotNull();
		IsNotNullFactory.getInstance().registry("newNotNull", bean);
		assertNotNull(IsNotNullFactory.getInstance().getIsNotNull("newNotNull"));
	}
	
	//是否可以变更算子配置  日志出现检核对象为空  true
	@Test
	public void testChangeDefaultSqlIsNotNull() throws QualityExecption, ExecuteException, IOException {
		DefaultIsNotNull bean = (DefaultIsNotNull)IsNotNullFactory.getInstance().getIsNotNull("DefaultIsNotNull");
		bean.setSqlType("testIsNotNULL");
		IsNotNullFactory.getInstance().registry("DefaultIsNotNull", bean);
		IsNotNullFactory.getInstance().createArithmetic("DefaultIsNotNull");
	}
	
	//是否可以进行枚举类默认配置读取
	@Test
	public void testResourceUrl() throws QualityExecption {
		assertNotNull(ResourceUrl.getUrlType(TYPE_FACTORY));
		assertEquals(ResourceUrl.getUrlType(TYPE_FACTORY),TEST_RESOURCE_URL);
	}
}
