package com.eternalinfo.tern.arithmetic.utils;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eternalinfo.tern.arithmetic.exception.QualityExecption;



/**
 * 数据质量检查工具类
 *
 * @author 王建亮
 * @since 2018/3/20
 **/
public class QualityUtil {

    /**
     * 校验函数必填参数
     * @param configMap 配置内容集合
     * @param paramList 函数参数列表
     * @throws QualityExecption 质量异常类
     */
    public static void validateFunConfig(Map<String, Object> configMap,List<Map<String, Object>> paramList) throws QualityExecption {
        for(Map<String,Object> paramMap:paramList){
            String paramName = paramMap.get("PARAM_NAME").toString();
            String paramCnName = paramMap.get("PARAM_CNNAME").toString();
            if(configMap.getOrDefault(paramName,null) == null){
                throw new QualityExecption(paramCnName+"不能为空!");
            }
        }
    }

    /**
     * freemarker模版变量转换 将${变量}装换成具体值
     * @param sourceStr 源字符串
     * @param sourceMap 源对象
     * @return  转换后的字符串
     * @throws QualityExecption
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String transVariableToString(String sourceStr,Map<String,Object> sourceMap) throws QualityExecption {
        StringWriter stringWriter = new StringWriter();
        try {
        	Map resMap = getDateMap();
        	sourceMap.putAll(resMap);
            Template template = new Template(null, sourceStr,FreemarkerConfiguration.getInstance());
            DefaultObjectWrapper wrapper = new DefaultObjectWrapper(FreemarkerConfiguration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            wrapper.setUseAdaptersForContainers(true);
            template.process(sourceMap,stringWriter,wrapper);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new QualityExecption(sourceStr+"变量替换失败1!"+e);
        } catch (TemplateException e) {
        	String errorVarName = e.getBlamedExpressionString();
            throw new QualityExecption(sourceStr+"变量${"+errorVarName+"}替换失败!原因:变量不存在或者数据为空！");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws QualityExecption {
    	String sss = "select ${col} from ${ETL_DT_YYYYMMDD} from dual";
    	Map tmpMap = new HashMap();
    	tmpMap.put("col", "code");
    	String res = transVariableToString(sss, tmpMap);
    	System.out.println(res);
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map getDateMap(){
    	Map resMap = new HashMap();
    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("YYYYMMdd");
		String ETL_DT = sdf_yyyy_MM_dd.format(date);
		String ETL_DT_YYYYMMDD=sdf_yyyyMMdd.format(date);
		String ETL_DT_YYYYMM=ETL_DT_YYYYMMDD.substring(0, 6);
		String ETL_DT_MMDD=ETL_DT_YYYYMMDD.substring(4, 8);
		//后一天 +1
		calendar.add(Calendar.DAY_OF_MONTH,1);
		String ETL_NEXT_DAY=sdf_yyyyMMdd.format(calendar.getTime());
		//前一天  -1
		calendar.setTime(date);//初始化时间
		calendar.add(Calendar.DAY_OF_MONTH,-1);
		String ETL_LAST_DAY=sdf_yyyyMMdd.format(calendar.getTime());
		//月初第一天
		calendar.setTime(date);//初始化时间
		calendar.set(Calendar.DAY_OF_MONTH,1);
		String ETL_MONTH_BEGIN= sdf_yyyyMMdd.format(calendar.getTime());
		//下个月第一天
		calendar.setTime(date);//初始化时间
		calendar.add(Calendar.MONTH,1);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		String ETL_NEXT_MONTH_BEGIN=sdf_yyyyMMdd.format(calendar.getTime());
		//上个月第一天 -1
		calendar.setTime(date);//初始化时间
		calendar.add(Calendar.MONTH,-1);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		String ETL_LAST_MONTH_BEGIN=sdf_yyyyMMdd.format(calendar.getTime());
		//上月最后一天
		calendar.setTime(date);//初始化时间
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		String ETL_LAST_MONTH_END=sdf_yyyyMMdd.format(calendar.getTime());
		//当月最后一天
		calendar.setTime(date);//初始化时间
		calendar.add(Calendar.MONTH,1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		String ETL_MONTH_END=sdf_yyyyMMdd.format(calendar.getTime());
		//下月最后一天
		calendar.setTime(date);//初始化时间
		calendar.add(Calendar.MONTH,2);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		String ETL_NEXT_MONTH_END=sdf_yyyyMMdd.format(calendar.getTime());
		
		resMap.put("ETL_DT",ETL_DT);
		resMap.put("ETL_DT_YYYYMMDD",ETL_DT_YYYYMMDD);
		resMap.put("ETL_DT_YYYYMM",ETL_DT_YYYYMM);
		resMap.put("ETL_DT_MMDD",ETL_DT_MMDD);
		resMap.put("ETL_NEXT_DAY",ETL_NEXT_DAY);
		resMap.put("ETL_LAST_DAY",ETL_LAST_DAY);
		resMap.put("ETL_MONTH_BEGIN",ETL_MONTH_BEGIN);
		resMap.put("ETL_NEXT_MONTH_BEGIN",ETL_NEXT_MONTH_BEGIN);
		resMap.put("ETL_LAST_MONTH_BEGIN",ETL_LAST_MONTH_BEGIN);
		resMap.put("ETL_MONTH_END",ETL_MONTH_END);
		resMap.put("ETL_NEXT_MONTH_END",ETL_NEXT_MONTH_END);
		resMap.put("ETL_LAST_MONTH_END",ETL_LAST_MONTH_END);
		return resMap; 
	}
}
