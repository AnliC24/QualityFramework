package com.eternalinfo.tern.arithmetic.utils;


import freemarker.template.Configuration;
import freemarker.template.SimpleScalar;
import org.apache.commons.lang.time.DateFormatUtils;
import java.util.Date;


/**
 * freemarker变量配置类
 *
 * @author 王建亮
 * @since 2018/3/20
 **/
public class FreemarkerConfiguration extends Configuration {

    private static FreemarkerConfiguration configuration = new FreemarkerConfiguration();

    private FreemarkerConfiguration() {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        SimpleScalar simpleScalar = new SimpleScalar(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        this.setSharedVariable("SYSDATE",simpleScalar);
        simpleScalar = new SimpleScalar(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        this.setSharedVariable("SYSTIME",simpleScalar);
        this.setNumberFormat("computer");//大数字纯数字格式
    }

    public static FreemarkerConfiguration getInstance(){
        return configuration;
    }
}
