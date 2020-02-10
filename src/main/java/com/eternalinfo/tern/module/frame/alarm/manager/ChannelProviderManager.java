package com.eternalinfo.tern.module.frame.alarm.manager;

import java.util.Map;

import com.eternalinfo.tern.module.frame.alarm.beans.TernProvider;

/**
 * @author 王诚沣
 * @version 1.0
 * @description 渠道提供 管理者
 * */
public interface ChannelProviderManager {
	
	public  void registryProvider(String name,TernProvider provider);
	
	public  void deleteProvider(String name);

	public  Map<String, TernProvider> getServiceManager();
	
	public  String toString();
}
