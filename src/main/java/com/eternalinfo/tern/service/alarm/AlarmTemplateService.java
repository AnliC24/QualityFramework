package com.eternalinfo.tern.service.alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eternalinfo.tern.alarm.sql.AlarmSql;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.jdbc.support.Page;
import com.eternalinfo.tern.jdbc.support.Record;

/**
 * @author 王诚沣
 * @上午12:02:30
 * @description
 * @version
 */
@Service
public class AlarmTemplateService {
	
	@Autowired
	AlarmSql sql;
	
	public Page<Record> listTemplateView(int pageNumber, int pageSize, Map<String, Object> param,String orderBy)
			throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		List<Object> params = new ArrayList<>();
		Page<Record> paginate = jdbcTemplate.paginate(pageNumber, pageSize,
				sql.getAlarmTemplateListSql(param,params).toString(),orderBy, params.toArray());
		return paginate;
	}
	
	@Transactional
	public boolean addTemplate(Map<String, Object> params) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		List<Object[]> param = new ArrayList<>();
		int result[] = jdbcTemplate.batchUpdate(sql.addTemplateSql(params, param).toString(),param);
		return Arrays.toString(result).contains("-1")?false:true;
	}
	
	@Transactional
	public boolean deleteTemplate(String templateId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate.update(sql.deleteTemplateSql().toString(),templateId) != -1?true:false;
	}
}
