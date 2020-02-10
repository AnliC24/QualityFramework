package com.eternalinfo.tern.arithmetic.integrality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;
import com.eternalinfo.tern.arithmetic.integrality.beans.DefaultIsNotNullCheckBean;
import com.eternalinfo.tern.examination.beans.DbCheckBean;
import com.eternalinfo.tern.examination.beans.FileCheckBean;
import com.eternalinfo.tern.execute.DbExecute;
import com.eternalinfo.tern.execute.Execute;
/**
 * @author 王诚沣
 * @下午1:01:24
 * @description 非空默认实现 sql
 * @version
 */
public  class DefaultIsNotNullCheck implements IsNotNullCheck{
	
	protected  Logger LOG = LogManager.getLogger(this.getClass());
	
	private Execute execute;
	
	public DefaultIsNotNullCheck(DbCheckBean bean) throws QualityExecption  {
		this.check(bean);
	}
	
	public DefaultIsNotNullCheck(FileCheckBean b) throws QualityExecption{
		this.check(b);
	}
	

	@Override
	public void check(DbCheckBean a) throws QualityExecption {
		LOG.info("执行数据源类型非空检查");
		execute = new DbExecute(new DefaultIsNotNullCheckBean(a));
		execute.executeDB();
	}

	@Override
	public void check(FileCheckBean a) throws QualityExecption {
		LOG.info("执行文件类型非空检查");
		//文件类型这里还需要有解析器 parse 解析文件 然后丢 构造成对象 丢入执行环境内执行
	}
	
	public Object toReport() {
		return execute.toReport();
	}
}
