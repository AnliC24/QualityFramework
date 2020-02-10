package com.eternalinfo.tern.module.frame.alarm.provider;

import com.eternalinfo.tern.module.frame.alarm.service.MailAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.service.SoapInvokeAlarmChannel;

/**
 * @author 王诚沣
 * @下午4:23:53
 * @description 告警渠道 服务接口提供者
 * @version
 */
public class AlarmChannelImplProvider implements AlarmChannelProvider{

	@Override
	public MailAlarmChannel getMailAlarmChannel() {
		return new MailAlarmChannel();
	}

	@Override
	public SoapInvokeAlarmChannel getSoapInvokeAlarmChannel() {
		return new SoapInvokeAlarmChannel();
	}
	
}
