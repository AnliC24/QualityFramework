package com.eternalinfo.tern.arithmetic;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.arithmetic.integrality.DefaultIsNotNullCheck;
import com.eternalinfo.tern.examination.beans.DbCheckBean;
import com.eternalinfo.tern.examination.beans.FileCheckBean;





/**
 * @author 王诚沣
 * @下午5:01:44
 * @description 算子工厂 
 * @version
 */
public class ArithmeticFactory {
	private static ArithmeticFactory  factory = new ArithmeticFactory();
	private ArithmeticFactory() {}
	
	public Object creator(Object bean) throws QualityExecption {
		if(bean instanceof DbCheckBean) {
			return new DefaultIsNotNullCheck((DbCheckBean)bean).toReport();
		}else if(bean instanceof FileCheckBean){
			return new DefaultIsNotNullCheck((FileCheckBean)bean);
		}
		throw new QualityExecption("暂不支持其他类型检核对象");
	}
	
	public static ArithmeticFactory getInstance() {
		return factory;
	}
	
	public Object creator(Object bean,String type) throws QualityExecption {
		if(type.equals("IsNotNullCheck")) {
			return creator(bean);
		}else if("DataFormat".equals(type)) {
			
		}
		throw new QualityExecption("暂不支持"+type+"类型算子");
	}
}
