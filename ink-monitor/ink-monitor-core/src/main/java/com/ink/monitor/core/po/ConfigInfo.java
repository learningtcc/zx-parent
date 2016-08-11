package com.ink.monitor.core.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;

/**
 * 读取Spring加载的配置文件属性
 * Created by aiyungui on 2016/5/26.
 */
@Component("configInfo")
public class ConfigInfo {

    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;

    /*模块监控邮件模板ID*/
    private String moduleEmailTemplateId;
    /*模块监控短信模版ID*/
    private String moduleSmsTemplateId;
    /*邮件模板ID*/
    private String ServiceEmailTemplateId;
    /*短信模版ID*/
    private String ServiceSmsTemplateId;
    /*商户Code*/
    private String merchantCode;


    public String getModuleEmailTemplateId() {
        return customizedPropertyConfigurer.getProperty("monitor.module.email.template.id");
    }

    public String getModuleSmsTemplateId() {
        return customizedPropertyConfigurer.getProperty("monitor.module.sms.template.id");
    }

    public String getServiceEmailTemplateId() {
        return customizedPropertyConfigurer.getProperty("monitor.service.email.template.id");
    }

    public String getServiceSmsTemplateId() {
        return customizedPropertyConfigurer.getProperty("monitor.service.sms.template.id");
    }

    public String getMerchantCode() {
        return customizedPropertyConfigurer.getProperty("monitor.merchant.code");
    }
}
