package com.eternalinfo.tern.controller.alarm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eternalinfo.tern.controller.PublicController;
import com.eternalinfo.tern.jdbc.support.Page;
import com.eternalinfo.tern.jdbc.support.Record;
import com.eternalinfo.tern.kit.AjaxResult;
import com.eternalinfo.tern.service.alarm.AlarmTemplateService;

/**
 * @author 王诚沣
 * @下午2:04:10
 * @description
 * @version
 */
@RestController
@RequestMapping("/alarm/alarmMould")
public class AlarmMouldController extends PublicController{
	
	@Autowired
	AlarmTemplateService alarmTemplateService;
	
	
	@GetMapping("/getMouldView")
	public AjaxResult getMouldView(int pageNumber, int pageSize, @RequestParam Map<String, Object> param,String orderBy) throws Exception {
		Page<Record> result = alarmTemplateService.listTemplateView(pageNumber, pageSize, param, orderBy);
        return success("查询成功").setData(result);
	}
	
	/**
	 * 	告警模板新增
	 * */
	@PostMapping("/templateMouldAdd")
	public AjaxResult templateMouldAdd(@RequestBody Map<String, Object> params) {
		 return alarmTemplateService.addTemplate(params)?success("新增成功").setData(null).setCode(200):warn("新增失败").setCode(-1);
	}
	
	/**
	 * 	模板注销
	 * */
	@DeleteMapping("/templateDelete")
	public AjaxResult templateDelete(String templateId) {
		return alarmTemplateService.deleteTemplate(templateId)?success("注销成功").setCode(200):warn("注销失败").setCode(-1);
	}
	
}
