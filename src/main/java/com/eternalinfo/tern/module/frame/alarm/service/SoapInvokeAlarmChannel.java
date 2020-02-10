package com.eternalinfo.tern.module.frame.alarm.service;

import com.eternalinfo.tern.module.frame.alarm.beans.SoapInvokerAlarm;
import com.eternalinfo.tern.module.frame.alarm.utils.SoapInvokeSMSUtil;


/**
 * @author 王诚沣
 * @下午7:05:41
 * @description
 * @version
 */
public class SoapInvokeAlarmChannel implements AlarmChannel{
	

	SoapInvokeSMSUtil soapInvokeSMSUtil;
	
	@Override
	public boolean execute(Object o) {
		if(o instanceof SoapInvokerAlarm) {
			SoapInvokerAlarm soap = (SoapInvokerAlarm)o;
			if(!soap.getAlarmType().isEmpty()&&!soap.getTelphone().isEmpty()) {
				//soapInvokeSMSUtil.sendMessageForTMobile(soap.getAlarmType(), soap.getTelphone(), soap.getMessage());
				return true;
			}
		}
		return false;
	}
	
}
