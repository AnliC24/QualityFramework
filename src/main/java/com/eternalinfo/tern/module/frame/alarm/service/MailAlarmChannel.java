package com.eternalinfo.tern.module.frame.alarm.service;

import com.eternalinfo.tern.module.frame.alarm.beans.MailAlarm;

/**
 * @author 王诚沣
 * @下午3:54:11
 * @description
 * @version 邮件告警 基于MailUtil的处理框架 服务提供具体实现
 */
public class MailAlarmChannel implements AlarmChannel{
	
	

	@Override
	public boolean execute(Object o) {
		if(o instanceof MailAlarm) {
			MailAlarm mail = (MailAlarm)o;
			if(mail.getReceivers().size()>0) {
				//mailUtil.batchSendMail(mail.getReceivers(), mail.getSubject(), mail.getContent());
				return true;
			}
		}
		return false;
	}
}
