package com.module.frame.alarm.test.arithmetic;

import static org.junit.Assert.assertNotEquals;

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
	static  List<Map<String, Object>> checkObject = new ArrayList<Map<String,Object>>();
	
	static {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("DMOD_EN_NAME", "tn_meta_dmod_col_info");
		aMap.put("COL_EN_NAME", "COL_LEN");
		checkObject.add(aMap);
	}
	
	@Test
	public void testExaminationFactory() throws QualityExecption, ExecuteException, IOException {
		DefaultDbObject bean = new DefaultDbObject();
		ArithmeticFactory.getInstance().creator(bean,TYPE_FACTORY,TYPE_ARITHMETIC);
		assertNotEquals(bean, bean);
	}
	
	@Test
	public void testNewDbExecute() throws QualityExecption, ExecuteException, IOException {
		ArithmeticFactory.getInstance().creator(TYPE_FACTORY,TEST_NEW_DBEXECUTE);
	}
}
