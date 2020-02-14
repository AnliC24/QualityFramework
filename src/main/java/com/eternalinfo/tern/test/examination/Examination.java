package com.eternalinfo.tern.test.examination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author 王诚沣
 * @下午3:07:02
 * @description	检核对象
 * @version
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@Type(value = DefaultDbObject.class,name = "db"),
		@Type(value = FileObject.class,name = "file")
})
public abstract class Examination {
	
	@JsonProperty("TYPE_FACTORY")
	public String typeFactory;
	
	@JsonProperty("TYPE_ARITHMETIC")
	public String typeArithmetic;
	
	
	public String resourceUrl = "";
	 
	public String sqlType = "";
	
	@JsonProperty("STRATEGY")
	public String strategy = "";

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getTypeFactory() {
		return typeFactory;
	}

	public void setTypeFactory(String typeFactory) {
		this.typeFactory = typeFactory;
	}

	public String getTypeArithmetic() {
		return typeArithmetic;
	}

	public void setTypeArithmetic(String typeArithmetic) {
		this.typeArithmetic = typeArithmetic;
	}
}
