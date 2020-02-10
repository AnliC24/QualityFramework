package com.eternalinfo.tern.module.frame.alarm.support;

import com.eternalinfo.tern.module.frame.alarm.beans.TernProvider;
import com.eternalinfo.tern.module.frame.alarm.service.AlarmChannel;

/**
 * @windc 
 * @author 获取渠道
 * */
public interface AlarmChannelSupport {
	
	AlarmChannel getChannel(String name,String callBeanName) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

	AlarmChannel reflectChannel(TernProvider provider,String name);
}
