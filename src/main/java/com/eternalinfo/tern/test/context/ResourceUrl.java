package com.eternalinfo.tern.test.context;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;

public enum ResourceUrl {

	IS_NOT_NULL("IsNotNull","com/eternalinfo/tern/test/resource/is-not-null.properties"),
	DATA_FORMAT("DataFormat","com/eternalinfo/tern/test/resource/data-format.properties");
	
	private String type;
	private String url;
	
	private ResourceUrl(String type,String url) {
		this.type = type;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static String getUrlType(String type) throws QualityExecption {
		for(ResourceUrl url:ResourceUrl.values()) {
			if(url.getType().equals(type)) {
				return url.getUrl();
			}
		}
		throw new QualityExecption("未找到"+type+"对应文件路径");
	}
}
