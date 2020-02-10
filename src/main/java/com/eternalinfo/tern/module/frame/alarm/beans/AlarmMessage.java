package com.eternalinfo.tern.module.frame.alarm.beans;



/**
 * @author windc
 * @description 告警模板
 * */
public abstract  class AlarmMessage {

	@SuppressWarnings("unused")
	private final String messageType;
	
	abstract static  class Builder<T extends Builder<T>>{
		private String messageType = "";
		public T messageType(String val) {
			messageType = val; return self();
		}
		abstract AlarmMessage build();
		protected abstract T self();
	}
	
	AlarmMessage(Builder<?> builder){
		messageType = builder.messageType;
	}
}
