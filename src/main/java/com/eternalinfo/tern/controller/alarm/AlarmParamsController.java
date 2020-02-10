package com.eternalinfo.tern.controller.alarm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eternalinfo.tern.controller.PublicController;
import com.eternalinfo.tern.jdbc.support.Page;
import com.eternalinfo.tern.jdbc.support.Record;
import com.eternalinfo.tern.kit.AjaxResult;
import com.eternalinfo.tern.service.alarm.AlarmParamsService;

/**
 * @author 王诚沣
 * @下午4:49:38
 * @description 告警参数配置
 * @version
 */
@RestController
@RequestMapping("/alarm/alarmParams")
public class AlarmParamsController extends PublicController{
	
	@Autowired
	AlarmParamsService AlarmParamsService;
	
	@GetMapping("/alarmParamsSearch")
	public AjaxResult alarmParamsSearch(int pageNumber, int pageSize, @RequestParam Map<String, Object> param,String orderBy) throws Exception {
		Page<Record> result = AlarmParamsService.listTemplateView(pageNumber, pageSize, param, orderBy);
		return success("查询成功").setData(result);
	}
}
