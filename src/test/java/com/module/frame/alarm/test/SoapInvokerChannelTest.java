package com.module.frame.alarm.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eternalinfo.tern.module.frame.alarm.beans.SoapInvokerAlarm;
import com.eternalinfo.tern.module.frame.alarm.manager.ChannelProviderImplManager;
import com.eternalinfo.tern.module.frame.alarm.manager.ChannelProviderManager;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelImplProvider;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelProvider;
import com.eternalinfo.tern.module.frame.alarm.service.AlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.service.MailAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.service.SoapInvokeAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.support.AlarmChannelImplSupport;
import com.eternalinfo.tern.module.frame.alarm.support.AlarmChannelSupport;
import com.eternalinfo.tern.module.frame.alarm.utils.SoapInvokeSMSUtil;

/**
 * @author 王诚沣
 * @下午7:24:32
 * @description 
 * @version 1.0 Soap 测试单例
 */
@SpringBootTest //extends BaseTestCase 
@RunWith(SpringJUnit4ClassRunner.class)
public class SoapInvokerChannelTest {
	
	
	@Autowired
	SoapInvokeSMSUtil soapInvokeSMSUtil;
	
	String alarmType = "wechat";
	String telphone = "13808547353";
	String message = "cesi";
	String callAlarmChannel = "wechat";
	SoapInvokerAlarm soap = null;
	//soapInvoker beans test
	public void soapInvokerAlarmBeanTest() {
		 soap = new SoapInvokerAlarm.Builder()
													.alarmType(telphone)
													.telphone(telphone)
													.message(message).build();
		assertNotNull(soap);
	}
	
	//soapInvokeSMSUtil sendMessageForTMobile success?
	@Test
	public void soapInvokeSMSUtilTest() throws DatatypeConfigurationException {
		//soapInvokeSMSUtil.sendMessageForTMobile(soap.getAlarmType(), soap.getTelphone(), soap.getMessage());
	}
	
	//whether SoapInvokeAlarmChannel can work?
	@Test
	public void soapInvokerAlarmChannelTest() {
		AlarmChannel soapChannel = new SoapInvokeAlarmChannel();
		assertNotEquals(AlarmChannel.class, soapChannel);
		assertNotEquals(Boolean.TRUE, soapChannel.execute(soap));
	}
	
	@Test
	public void alarmChannelProviderTest() {
		AlarmChannelProvider provider = new AlarmChannelImplProvider(); 
		assertNotNull(provider.getSoapInvokeAlarmChannel());
		assertNotEquals(Boolean.TRUE, provider.getSoapInvokeAlarmChannel() instanceof SoapInvokeAlarmChannel);
	}
	
	@Test
	public void alarmChannelSupportTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		AlarmChannelProvider provider = new AlarmChannelImplProvider();
		ChannelProviderManager mananger = new ChannelProviderImplManager();
		mananger.registryProvider("AlarmChannelProvider",provider);
		AlarmChannelSupport support = new AlarmChannelImplSupport();
		assertNotNull(support.getChannel("AlarmChannelProvider",callAlarmChannel));
		assertNotEquals(Boolean.TRUE, support.getChannel("AlarmChannelProvider",callAlarmChannel) instanceof MailAlarmChannel);
		assertNotEquals(Boolean.TRUE, support.getChannel("AlarmChannelProvider",callAlarmChannel) instanceof SoapInvokeAlarmChannel);
	}
	
}
