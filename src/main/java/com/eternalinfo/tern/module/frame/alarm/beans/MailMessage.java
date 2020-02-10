package com.eternalinfo.tern.module.frame.alarm.beans;

import java.io.Serializable;

/**
 * @author 王诚沣
 * @上午11:05:25
 * @description
 * @version
 */
public class MailMessage extends AlarmMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6182898052688019912L;
	private final String jobCNName;
	private final String jobName;
	private final String ruleCount;
	private final String ruleError;
	private final String ruleFail;
	private final String ruleMessage;
	
	
	public static class Builder extends AlarmMessage.Builder<Builder>{
		private String jobCNName = "";
		private String jobName = "";
		private String ruleCount = "";
		private String ruleError = "";
		private String ruleFail = "";
		private String ruleMessage = "";
		public Builder JobCNName(String val) {
			jobCNName = val; return this;
		}
		public Builder JobName(String val) {
			jobName = val;return this;
		}
		public Builder RuleCount(String val) {
			ruleCount = val;return this;
		}
		public Builder RuleError(String val) {
			ruleError = val;return this;
		}
		public Builder RuleFail(String val) {
			ruleFail = val;return this;
		}
		public Builder RuleMessage(String val) {
			ruleMessage = val;return this;
		}

		@Override
		public MailMessage build() {
			return new MailMessage(this);
		}

		@Override
		protected Builder self() {return this;}
		
	}
	
	private MailMessage(Builder builder) {
		super(builder);
		jobCNName = builder.jobCNName;
		jobName = builder.jobName;
		ruleCount = builder.ruleCount;
		ruleError = builder.ruleError;
		ruleFail = builder.ruleFail;
		ruleMessage = builder.ruleMessage;
	}
	
	
	public String getJobCNName() {
		return jobCNName;
	}

	public String getJobName() {
		return jobName;
	}

	public String getRuleCount() {
		return ruleCount;
	}

	public String getRuleError() {
		return ruleError;
	}

	public String getRuleFail() {
		return ruleFail;
	}

	public String getRuleMessage() {
		return ruleMessage;
	}

	@Override
	public String toString() {
		return new StringBuffer("【数据治理平台】- ").append(this.jobCNName).append("(")
				.append(this.jobName).append(")，共检查")
				.append(this.ruleCount).append("个规则，其中规则检查异常")
				.append(this.ruleError).append("个，规则检查错误")
				.append(this.ruleFail).append("个。\n").append(this.ruleMessage).toString();
	}
	
}
