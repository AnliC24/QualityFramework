package com.eternalinfo.tern.module.frame.alarm.beans;

/**
 * @author 王诚沣
 * @下午7:07:12
 * @description
 * @version
 */
public class SoapInvokerAlarm {
	
	private final String alarmType;
	private final String telphone;
	private final String message;
	
	public static class Builder{
		private String alarmType = "";
		private String telphone = "";
		private String message = "";
		public Builder alarmType(String val) {
			alarmType = val; return this;
		}
		public Builder telphone(String val) {
			telphone = val; return this;
		}
		public Builder message(String val) {
			message = val; return this;
		}
		public SoapInvokerAlarm build() {
			return new SoapInvokerAlarm(this);
		}
	}
	
	private SoapInvokerAlarm(Builder builder) {
		alarmType = builder.alarmType;
		telphone = builder.telphone;
		message = builder.message;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public String getTelphone() {
		return telphone;
	}

	public String getMessage() {
		return message;
	}
	
	
}
