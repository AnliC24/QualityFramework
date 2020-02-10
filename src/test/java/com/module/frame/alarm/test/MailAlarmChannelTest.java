package com.module.frame.alarm.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eternalinfo.tern.module.frame.alarm.beans.MailAlarm;
import com.eternalinfo.tern.module.frame.alarm.manager.ChannelProviderImplManager;
import com.eternalinfo.tern.module.frame.alarm.manager.ChannelProviderManager;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelImplProvider;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelProvider;
import com.eternalinfo.tern.module.frame.alarm.service.MailAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.service.SoapInvokeAlarmChannel;
import com.eternalinfo.tern.module.frame.alarm.support.AlarmChannelImplSupport;
import com.eternalinfo.tern.module.frame.alarm.support.AlarmChannelSupport;
import com.eternalinfo.tern.module.frame.alarm.utils.MailUtil;


/**
 * @author 王诚沣
 * @下午5:06:46
 * @description 邮件告警渠道  测试
 * @version 告警框架单元测试
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailAlarmChannelTest{
	
	
	public MailUtil mailUtil;
	
	static List<String> receivers = new ArrayList<String>();
	static {
		receivers.add("cfwang@eternalinfo.cn");
	}
	String subject = "日志";
	String content = "123456";
	String callAlarmChannel = "mail";
	
	//beans mailAlarm test
	@Test
	public void mailAlarmTest() {
		MailAlarm mailAlarm = new MailAlarm.Builder()
									.receivers(receivers)
									.subject(subject)
									.content(content).build();
		assertNotNull(mailAlarm);
	}
	
	//what's state  mailUtil sended? test
	@Test
	public void mailUtilTest() {
		mailUtil.batchSendMail(receivers,subject,content);
	}
	
	//what's state of mail sended? test
	@Test
	public void mailAlarmChannelTest() {
		MailAlarmChannel mailAlarmChannel = new MailAlarmChannel();
		MailAlarm mailAlarm = new MailAlarm.Builder()
									.receivers(receivers)
									.subject(subject)
									.content(content).build();
		assertNotEquals(false, mailAlarmChannel.execute(mailAlarm));
	}
	
	// AlarmChannelProvider whether can provider intance? 
	@Test
	public void alarmChannelProviderTest() {
		AlarmChannelProvider provider = new AlarmChannelImplProvider(); 
		assertNotNull(provider.getMailAlarmChannel());
		assertNotEquals(MailAlarmChannel.class, provider.getMailAlarmChannel());
	}
	
	// whether channelProviderManager  can registry provider by public?
	@Test
	public void channelProviderManagerTestByPublic() {
//		AlarmChannelProvider provider = new AlarmChannelImplProvider();
//		ChannelProviderImplManager.ServiceManager.put("AlarmChannelProvider",provider);
//		assertNotEquals(AlarmChannelProvider.class, ChannelProviderImplManager.ServiceManager.get("AlarmChannelProvider"));
	}
	
	// whether channelProviderManager can registry provider by reflection?
	@Test
	public void channelProviderManagerTestByProvide() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		AlarmChannelProvider provider = new AlarmChannelImplProvider();
		ChannelProviderManager mananger = new ChannelProviderImplManager();
		mananger.registryProvider("AlarmChannelProvider",provider);
		AlarmChannelSupport support = new AlarmChannelImplSupport();	
		//assertNotEquals(Boolean.TRUE,support.getChannel("AlarmChannelProvider",callAlarmChannel) instanceof AlarmChannel);
		//assertNotEquals(Boolean.TRUE,support.getChannel("AlarmChannelProvider",callAlarmChannel) instanceof MailAlarmChannel);
		assertNotEquals(Boolean.TRUE,support.getChannel("AlarmChannelProvider",callAlarmChannel) instanceof SoapInvokeAlarmChannel);
	}
	
}
