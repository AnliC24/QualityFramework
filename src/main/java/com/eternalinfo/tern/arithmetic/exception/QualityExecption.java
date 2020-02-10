package com.eternalinfo.tern.arithmetic.exception;

/**
 * 数据质量异常类
 *
 * @author 王建亮
 * @since 2018/3/13
 **/
public class QualityExecption extends  Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1382662838145592820L;

	public QualityExecption(String message){
        super(message);
    }
}
