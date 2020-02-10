package com.eternalinfo.tern.module.frame.alarm.beans;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 王诚沣
 * @下午4:28:12
 * @description
 * @version
 */
public class MailAlarm {
	
	private final List<String> receivers;  //邮件接收者
	private final String subject;	//邮件标题
	private final String content;   //邮件内容
	
	public static class Builder {
		private List<String> receivers = new ArrayList<String>();
		private String subject = "";
		private String content = "";
		public Builder receivers(List<String> val) {
			receivers = val; return this;
		} 
		public Builder subject(String val) {
			subject = val; return this;
		}
		public Builder content(String val) {
			content = val; return this;
		}
		public MailAlarm build() {
			return new MailAlarm(this);
		}
	}
	
	private MailAlarm(Builder builder) {
		receivers = builder.receivers;
		subject = builder.subject;
		content = builder.content;
	}


	public List<String> getReceivers() {
		return receivers;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}	
}
