package com.eternalinfo.tern.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eternalinfo.tern.Impl.beans.DmodColCheckResult;
import com.eternalinfo.tern.Impl.sql.SqlHandler;
import com.eternalinfo.tern.annotation.Log;
import com.eternalinfo.tern.controller.PublicController;

import com.eternalinfo.tern.jdbc.JdbcTemplate;
import com.eternalinfo.tern.kit.FuncKit;

/**
 * @author 王诚沣
 * @下午1:31:37
 * @description  元模型核验接口
 * @version
 */
@RequestMapping("/alarm/dmodcheckImpl")
@RestController
public class DmodColCheckImpl extends PublicController{
	
	@Autowired
	SqlHandler Sql;															//标准sql
	
	boolean isFind = false;													//是否匹配到标准
	
	Map<String, Object> params = new HashMap<String, Object>();				//参数信息
	
	List<Map<String,Object>> stdTypeAllMap = new ArrayList<>();				//字段标准映射表
	
	List<Map<String, Object>> CHECK_DMOD_COL_INFO = new ArrayList<>();		//检查模型字段信息
	

	/**
	 * 1.基础数据核验
	 * 2.指标数据核验
	 * */
	@PostMapping(value = "/dmodCheckTest")
    @Log(moduleName = "模型核验管理", operName = "模型核验接口查询")
    public DmodColCheckResult listDmodCheckJob(@RequestBody Map<String, Object> checkDmodInfo) throws Exception {
		JdbcTemplate  jdbc = new JdbcTemplate();
		preHandler(checkDmodInfo);
		colAppendStdType(jdbc);
		coreCompareStd(jdbc);
	    coreCompareDmodCol();
		return  new DmodColCheckResult.Builder()
				.CHECK_SCOPE(params.get("SCOPE").toString())
				.CHECK_SOURCE_SYSTEM(params.get("SOURCE_SYSTEM").toString())
				.CHECK_RESULT(compareJobFinallyResult())
				.CHECK_DMOD_RESULT(CHECK_DMOD_COL_INFO)
				.build();
    }
	
	
	@SuppressWarnings("unchecked")
	private void preHandler(Map<String, Object> checkDmodInfo) {
		params.clear();
		CHECK_DMOD_COL_INFO.clear();
		params.putAll(checkDmodInfo);
		CHECK_DMOD_COL_INFO.addAll((List<Map<String, Object>>)params.get("CHECK_DMOD_INFO"));
	}
	
	/**
	 * 核心核验步骤
	 * */
	private void coreCompareStd(JdbcTemplate  jdbc) {
		stdBaseCheck(jdbc);
		stdKmpgCheck(jdbc);
		isFind = false;
	}
	
	/**
	 * 字段核验结果
	 * */
	private void coreCompareDmodCol(){
		compareColFinallyResult();
	}
	
	/**
	 *  字段类型标准映射
	 * */
	private void colAppendStdType(JdbcTemplate  jdbc) {
		stdTypeAllMap.clear();
		stdTypeAllMap.addAll(getStdTypeAll(jdbc));
		colTypeToStdType();
	}
	
	/**
	 * 	基础数据核验
	 * */
	private void stdBaseCheck(JdbcTemplate  jdbc){
		List<Object> baseParams = new ArrayList<>();
		//取所有基础标准信息
		List<Map<String, Object>> baseList = jdbc.queryForList(getStdBaseAllList(baseParams), baseParams.toArray());
		//值域字段长度修复
		computeRangeDataLength(baseList);
		compareStdBaseResult(baseList);
	}
	
	/**
	 * 	指标数据核验
	 * */
	private void stdKmpgCheck(JdbcTemplate  jdbc){
		List<Object> kpmgParams = new ArrayList<>();
		//取所有指标标准信息
		List<Map<String, Object>> kpmgList = jdbc.queryForList(getKpmgBaseAllList(kpmgParams), kpmgParams.toArray());		
		//值域字段长度修复
		computeRangeDataLength(kpmgList);	
		compareKpmgBaseResult(kpmgList);
	}
	
	/**
	 * 	基础数据标准sql
	 * */
	private String getStdBaseAllList(List<Object> baseParams) {
		StringBuffer baseSql = Sql.getStdBaseAllListSql();
		appendScope(baseSql,baseParams);
		appendSourceSystem(baseSql,baseParams);
		return baseSql.toString();
	}
	
	
	/**
	 *  基础比对
	 * */
	private void compareStdBaseResult(List<Map<String, Object>> stdList){		
		for(Map<String, Object> checkMap : CHECK_DMOD_COL_INFO) {
	     	for (Map<String, Object> stdMap : stdList) {
    			BaseColCheck baseColCheck =	new BaseColCheck.Builder()
    					.stdMap(stdMap).checkMap(checkMap).build();
    			isFind = baseColCheck.isFind();
    			if(isFind)break;
			}
        	if(!isFind) {
        		checkMap.put("base_errorMsg", "字段未匹配到基础标准");
        		checkMap.put("BASE_CHECK_STATE", "2");
        	}
		}
	}
	
	
	/**
	 * 	指标数据标准sql
	 * */
	private String getKpmgBaseAllList(List<Object> kpmgParams) {	
        StringBuffer kpmgSql = Sql.getStdKpmgAllListSql();
        appendScope(kpmgSql,kpmgParams);
		appendSourceSystem(kpmgSql, kpmgParams);
		return kpmgSql.toString();
	}
	
	
	/**
	 *  指标比对
	 * */
	private void compareKpmgBaseResult(List<Map<String, Object>> kpmgList){	
		for(Map<String, Object> checkMap : CHECK_DMOD_COL_INFO) {
	     	for (Map<String, Object> KPMGMap : kpmgList) {	     			
         		KpmgColCheck kpmgColCheck = new KpmgColCheck.Builder()
         				.stdMap(KPMGMap).checkMap(checkMap).build();
         		isFind = kpmgColCheck.isFind();
         		if(isFind)break;
			}
	     	if(!isFind) {
	     		checkMap.put("kpmg_errorMsg", "字段未匹配到指标标准");
	     		checkMap.put("KPMG_CHECK_STATE", "2");
	     	}
		}
	}
	
	/**
	 * 值域数据长度 修正
	 * */
	private void computeRangeDataLength(List<Map<String, Object>> list) {
		list.stream().forEach(item->item.compute("RANGE_DATA_LENGTH",((key,value)->{
			if(org.springframework.util.StringUtils.isEmpty(value)){
     	        return "0";
     	    }
     	    return value;
		})).toString());
	}
	
	
	/**
	 * 值域字段类型 转标准类型映射
	 * */
	private List<Map<String,Object>> changeColTypeToStdType(List<Map<String,Object>> stdTypeAllMap,String dmodDbType) {
		return stdTypeAllMap.stream().filter(item ->dmodDbType.equalsIgnoreCase(item.get("DATABASE_NAME").toString())).collect(Collectors.toList());
	}
	
	/**
	 *  获取字段标准映射  tn_std_type_map 
	 * */
	private List<Map<String,Object>> getStdTypeAll(JdbcTemplate  jdbc){
		StringBuilder stdTypeMap = new StringBuilder("SELECT TSTM.DATABASE_NAME,TSTM.DATA_TYPE,TSTM.STD_TYPE FROM TN_STD_TYPE_MAP TSTM  ");
		return jdbc.queryForList(stdTypeMap.toString());
	}
	
	/**
	 *  字段最终结果比对
	 * */
	private void compareColFinallyResult() {
		CHECK_DMOD_COL_INFO.stream().map(item->{
			if((org.springframework.util.StringUtils.isEmpty(item.get("base_errorMsg").toString()))
					&&org.springframework.util.StringUtils.isEmpty(item.get("kpmg_errorMsg").toString())){
				item.put("colCheckResult","核验通过");
			}else {
				item.put("colCheckResult","核验不通过");
			}
			return item;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 	任务最终结果统计
	 * */
	private String compareJobFinallyResult() {
		return CHECK_DMOD_COL_INFO.stream().filter(item->"核验不通过".equalsIgnoreCase(item.get("colCheckResult").toString())).collect(Collectors.toList()).size()>0?"核验失败":"核验成功";
	}
	
	/**
	 *  	子类类型标准映射
	 * */
	private void colTypeToStdType() {
		CHECK_DMOD_COL_INFO.stream().map(item->{
				item.put("base_errorMsg", "");
				item.put("base_successMsg", "");
				item.put("kpmg_errorMsg", "");
				item.put("kpmg_successMsg", "");
				return item.put("ColTypeToStdType",changeColTypeToStdType(stdTypeAllMap,item.get("DB_TYPE").toString()));
		}).collect(Collectors.toList());
	}
	
	
	
	private void appendScope(StringBuffer sql,List<Object> param) {
		if(FuncKit.collectKey(params, "SCOPE")){
			sql.append(" AND SCOPE = ?  ");
			param.add(params.get("SCOPE").toString().trim());
		}
	}
	
	
	private void appendSourceSystem(StringBuffer sql,List<Object> param) {
		if(FuncKit.collectKey(params, "SOURCE_SYSTEM")) {  
			String[] sourceSystemList = params.get("SOURCE_SYSTEM").toString().split(",");
			if(sourceSystemList.length >0 ){
				sql.append(" and (");
				String flag = "";
				for(int i = 0; i < sourceSystemList.length; i++){
					sql.append(flag + "locate(?, SOURCE_SYSTEM)>0 ");
					flag = " or ";
					param.add(sourceSystemList[i]);
				}
				sql.append(")");
			}
		}
	}

}
