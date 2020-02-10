package com.eternalinfo.tern.alarm.sql;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eternalinfo.tern.kit.FuncKit;
import com.eternalinfo.tern.kit.UidKit;

/**
 * @author 王诚沣
 * @下午11:56:54
 * @description
 * @version
 */
@Service
public class AlarmSql {
	
	public StringBuffer getAlarmTemplateListSql(Map<String, Object> param,List<Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tat.ALARM_TEMPLATE_ID,tat.ALARM_TEMPLATE_NAME,tat.ALARM_TEMPLATE_TEXT,tat.ALARM_TEMPLATE_STATE AS STATE FROM TN_ALARM_TEMPLATE tat WHERE 1=1 ");
		
		if(FuncKit.collectKey(param, "MOULD_NAME")){
			sql.append(" AND ALARM_TEMPLATE_NAME LIKE CONCAT('%',?,'%')  ");
			params.add(param.get("MOULD_NAME").toString().trim());
		}
		
		return sql;
	}
	
	
	public StringBuffer addTemplateSql(Map<String, Object> params,List<Object[]> param) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO TN_ALARM_TEMPLATE(ALARM_TEMPLATE_ID,ALARM_TEMPLATE_NAME,ALARM_TEMPLATE_TEXT,ALARM_TEMPLATE_STATE)");
		sql.append(" values(?,?,?,?)");
		
		Object[] obj = new Object[]{ 
			UidKit.genUuid(),
			params.get("ALARM_TEMPLATE_NAME").toString(),
			params.get("ALARM_TEMPLATE_TEXT").toString(),
			"00"
        };
		param.add(obj);
		
		return sql;
	}
	
	public StringBuffer deleteTemplateSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TN_ALARM_TEMPLATE SET ALARM_TEMPLATE_STATE = '01'  WHERE ALARM_TEMPLATE_ID = ? ");
		return sql;
	}
	
	
	public StringBuffer searchAlarmParams(Map<String, Object> param,List<Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tqvi.VAR_NAME,tqvi.VAR_CNNAME,tqvi.VAR_STATE FROM tn_qm_variable_info tqvi WHERE tqvi.VAR_TYPE = 'ALARM' ");
		return sql;
	}
}
