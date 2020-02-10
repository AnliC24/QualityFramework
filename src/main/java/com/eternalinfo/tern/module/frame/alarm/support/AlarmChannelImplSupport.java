package com.eternalinfo.tern.module.frame.alarm.support;

import com.eternalinfo.tern.module.frame.alarm.beans.TernProvider;
import com.eternalinfo.tern.module.frame.alarm.manager.ChannelProviderManager;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelImplProvider;
import com.eternalinfo.tern.module.frame.alarm.provider.AlarmChannelProvider;
import com.eternalinfo.tern.module.frame.alarm.service.AlarmChannel;

/**
 * @author 王诚沣
 * @下午4:41:13
 * @description  告警渠道框架
 * @version
 */
public class AlarmChannelImplSupport implements AlarmChannelSupport{

	@Override
	public AlarmChannel getChannel(String name,String callBeanName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		Class<?> managerClass = Class.forName("com.eternalinfo.tern.alarm.manager.ChannelProviderImplManager");
		ChannelProviderManager serviceManager =  (ChannelProviderManager) managerClass.newInstance();
		AlarmChannelProvider serviceProvider = (AlarmChannelImplProvider) serviceManager.getServiceManager().get(name);
		if(serviceProvider == null) {
			throw new IllegalArgumentException("No provider registered with name = "+name);
		}
		return this.reflectChannel(serviceProvider,callBeanName);
	}

	@Override
	public AlarmChannel reflectChannel(TernProvider provider,String name) {
		if(provider == null) {
			throw new NullPointerException(provider+" is null ");
		}
		if(provider instanceof AlarmChannelProvider) {
			AlarmChannelProvider serviceProvider = (AlarmChannelProvider)provider;
			return this.getAlarmChannel(serviceProvider,name);	 
		}
		throw new ClassCastException(provider+" can't cast "+AlarmChannelProvider.class);
	}
	
	private AlarmChannel getAlarmChannel(AlarmChannelProvider serviceProvider,String name) {
		if("mail".equalsIgnoreCase(name)) {
			return serviceProvider.getMailAlarmChannel();
		}else if("event".equalsIgnoreCase(name)) {
			
		}else if("IPTELPHONE".equalsIgnoreCase(name)) {
			
		}else if("WECHAT".equalsIgnoreCase(name)) {
			return serviceProvider.getSoapInvokeAlarmChannel();
		}else if("MESSAGE".equalsIgnoreCase(name)) {
			return serviceProvider.getSoapInvokeAlarmChannel();
		}
		throw new RuntimeException("暂不提供"+name+"告警方式");
	}
	
}
