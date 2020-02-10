package com.eternalinfo.tern.module.frame.alarm.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eternalinfo.tern.module.frame.alarm.beans.TernProvider;



/**
 * @author 王诚沣
 * @下午4:34:45
 * @description
 * @version
 */
public class ChannelProviderImplManager implements ChannelProviderManager{
	
	//服务框架管理器
	private static final Map<String,TernProvider> ServiceManager = new ConcurrentHashMap<>();
	
	@Override
	public void registryProvider(String name, TernProvider provider) {
		ServiceManager.put(name,provider);
	}

	@Override
	public void deleteProvider(String name) {
		ServiceManager.remove(name);
	}

	@Override
	public Map<String, TernProvider> getServiceManager() {
		return ServiceManager;
	}
	
	
}
