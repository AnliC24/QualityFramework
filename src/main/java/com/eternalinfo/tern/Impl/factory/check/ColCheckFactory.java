package com.eternalinfo.tern.Impl.factory.check;

/**
 * @author 王诚沣
 * @下午1:31:37
 * @description  字段核验工厂
 * @version
 */
public interface ColCheckFactory {
	
	//字段类型核验
	public void checkColType();
	
	//字段长度核验
	public void checkColLen();
	
	//字段精度核验
//	public void checkColFlo();
}
