package com.eternalinfo.tern.module.frame.alarm.provider;

import com.eternalinfo.tern.module.frame.alarm.beans.TernProvider;
import com.eternalinfo.tern.module.frame.alarm.service.MailAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.service.SoapInvokeAlarmChannel;

/**
 * @author 王诚沣
 * @version 1.0
 * @description 渠道提供者
 * */
public interface AlarmChannelProvider extends TernProvider{
	
	//邮件服务
	MailAlarmChannel getMailAlarmChannel();
	
	//Soap 服务
	SoapInvokeAlarmChannel getSoapInvokeAlarmChannel();
}
