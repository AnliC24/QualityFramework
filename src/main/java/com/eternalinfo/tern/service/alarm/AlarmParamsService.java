package com.eternalinfo.tern.service.alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eternalinfo.tern.alarm.sql.AlarmSql;
import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.jdbc.support.Page;
import com.eternalinfo.tern.jdbc.support.Record;

/**
 * @author 王诚沣
 * @下午4:56:27
 * @description
 * @version
 */
@Service
public class AlarmParamsService {
	
	@Autowired
	AlarmSql sql;
	
	public Page<Record> listTemplateView(int pageNumber, int pageSize, Map<String, Object> param,String orderBy)
			throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		List<Object> params = new ArrayList<>();
		Page<Record> paginate = jdbcTemplate.paginate(pageNumber, pageSize,
				sql.searchAlarmParams(param,params).toString(),orderBy, params.toArray());
		return paginate;
	}
}
