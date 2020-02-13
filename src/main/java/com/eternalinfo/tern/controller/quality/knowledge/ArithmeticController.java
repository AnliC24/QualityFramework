package com.eternalinfo.tern.controller.quality.knowledge;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.controller.PublicController;
import com.eternalinfo.tern.kit.AjaxResult;
import com.eternalinfo.tern.test.arithmetic.factory.ArithmeticFactory;
import com.eternalinfo.tern.test.arithmetic.integrality.DefaultIsNotNull;
import com.eternalinfo.tern.test.arithmetic.integrality.factory.IsNotNullFactory;
import com.eternalinfo.tern.test.examination.Examination;
import com.eternalinfo.tern.test.exception.ExecuteException;

/**
 * @author 王诚沣
 * @上午10:13:26
 * @description	算子库
 * @version
 */
@RestController
@RequestMapping("/quality/arithmetic")
public class ArithmeticController extends PublicController {
	
	
	/**
	 * 	算子信息查询
	 * */
	public AjaxResult arithmeticListSearch() {
		return success().setMessage("算子信息查询").setCode(200).setData(null);
	}
	
	/**
	 * 	算子详情查询
	 * */
	public AjaxResult arithmeticListDetailSearch() {
		return success().setMessage("算子详情查询").setCode(200).setData(null);
	}
	
	/**
	 * 	算子新增
	 * */
	public AjaxResult arithmeticAdd() {
		return success().setCode(200).setMessage("算子新增");
	}
	
	
	/**
	 * 	算子编辑修改
	 * */
	public AjaxResult arithmeticEdit() {
		return success().setCode(200).setMessage("算子编辑保存");
	}
	
	/**
	 * 	算子注销
	 * */
	public AjaxResult arithmeticDelete() {
		return success().setCode(200).setMessage("算子注销");
	}
	
	/**
	 * 	算子发布
	 * */
	public AjaxResult arithmeticWorkFlow() {
		return success().setCode(200).setMessage("算子发布");
	}
	
	/**
	 * 	测试
	 * @throws QualityExecption 
	 * @throws ExecuteException 
	 * @throws IOException 
	 * */
	@PostMapping("/testIsNotNull")
	public void testIsNotNull(@RequestBody Examination bean) throws QualityExecption, ExecuteException, IOException {
		ArithmeticFactory.getInstance().creator(bean,bean.getTypeFactory(),bean.getTypeArithmetic());
		assertEquals(bean, bean);
	}
	
	@PostMapping("/testChangeDefaultSqlIsNotNull")
	public void testChangeDefaultSqlIsNotNull(@RequestBody Examination object) throws QualityExecption, ExecuteException, IOException {
		DefaultIsNotNull bean = (DefaultIsNotNull)IsNotNullFactory.getInstance().getIsNotNull("DefaultIsNotNull");
		bean.setSqlType("testIsNotNULL");
		IsNotNullFactory.getInstance().registry("DefaultIsNotNull", bean);
		IsNotNullFactory.getInstance().createArithmetic(object,"DefaultIsNotNull");
		assertEquals(object, object);
	}
	
	@PostMapping("/testDataFormat")
	public void testDataFormat(@RequestBody Examination bean) throws QualityExecption, ExecuteException, IOException {
		ArithmeticFactory.getInstance().creator(bean,bean.getTypeFactory(),bean.getTypeArithmetic());
		assertEquals(bean, bean);
	}
	
}
