package com.ink.msgcenter.external.sms.zw.job.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.po.SmsCode;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.query.SmsLogQuery;
import com.ink.msgcenter.core.service.ISmsCodeManager;
import com.ink.msgcenter.core.service.ISmsLogManager;
import com.ink.msgcenter.external.sms.zw.job.service.SmsUpLinkService;
import com.ink.msgcenter.util.HttpUtil;
import com.ink.msgcenter.util.XmlAnalysisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信上行信息处理
 * Created by aiyungui on 2016/7/4.
 */
@Service("smsUpLinklService002")
public class ZwSmsUpLinklService implements SmsUpLinkService {

    private YinkerLogger logger = YinkerLogger.getLogger(getClass());
    @Autowired
    private ISmsCodeManager smsCodeManager;
    @Autowired
    private ISmsLogManager smsLogManager;
    @Override
    public void operateUpLink(SmsChannel smsChn) {

        if (StringUtils.isBlank(smsChn.getChnParam())) {
            return;
        }

        try{
            String result = getUpSmsInfo(smsChn);
            List<Map<String, String>> maplist = XmlAnalysisUtil.analysisXmlForSecond(result);
            // 如果有上行短信,查询Sms_Code
            for (Map<String, String> map : maplist) {
                try{
                    if (null != map.get("error")){
                        logger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_UP_SEND_CODE,"短信上行请求有误，请检查。返回信息：" + map);
                        continue;
                    }
                    String mobile = map.get("mobile");
                    String taskid = map.get("taskid");
                    String content = map.get("content");
                    String receivetime = map.get("receivetime");
                    //
                    SmsCode smsCode = new SmsCode();
                    smsCode.setMobile(mobile);
                    smsCode.setTaskId(taskid);
                    SmsCode smsCodeCheck = smsCodeManager.getSmsCode(smsCode);
                    //
                    if (smsCodeCheck != null && "0".equals(smsCodeCheck.getStatus())) {
                        //更新sysCode
                        smsCodeCheck.setStatus("1");
                        smsCodeManager.update(smsCodeCheck);
                        //记录日志
                        saveSmsLog(smsCodeCheck,map);
                        // 通知业务系统
                        notificationSystem(receivetime, content, smsCodeCheck);
                    }else{
                        logger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_UP_SEND_CODE,
                                "没有找到smsCode信息,短信上行通知信息为：" + map);
                    }
                }catch (Exception e){
                    logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_UP_SEND_CODE,"短信上行异常,短信信息："+map
                            +"异常信息："+e.getMessage(),e,"");
                }
            }
        } catch (Exception e) {
            logger.error(YkDataContant.MODULE_SMS_CODE, YkDataContant.INFO_SMS_UP_SEND_CODE, "短信上行异常,通道信息：" + smsChn
                    + "异常信息：" + e.getMessage(), e, "");
        }
    }

    /**
     * 获取上行短信信息
     * @param smsChn
     * @return
     */
    private String getUpSmsInfo(SmsChannel smsChn) {
        Map m = JSONObject.parseObject(smsChn.getChnParam());
        String clientUrl = String.valueOf(m.get("clientUrl"));
        String sourceUrl = "http://"+clientUrl+"/callApi.aspx";
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("userid", String.valueOf(m.get("userId")));
        paramsMap.put("account", String.valueOf(m.get("account")));
        paramsMap.put("password", String.valueOf(m.get("password")));
        paramsMap.put("action", "query");
        return HttpUtil.sendHttpPost(sourceUrl, paramsMap);
    }

    /**
     * 保存日志
     * @param smsCode
     * @param resultMap
     */
    private void saveSmsLog(SmsCode smsCode, Map<String, String> resultMap) {

        try {
            SmsLogQuery smsLogQuery =  new SmsLogQuery();
            smsLogQuery.setTaskId(smsCode.getTaskId());
            smsLogQuery.setMobile(smsCode.getMobile());
            smsLogQuery.setMerctCode(smsCode.getMerctCode());
            List<SmsLog> smsLogList = smsLogManager.find(smsLogQuery);
            if (null == smsLogList || smsLogList.isEmpty()){
                return ;
            }

            SmsLog smsLog = smsLogList.get(0);
            smsLog.setId(null);
            smsLog.setSendStatus("1");
            smsLog.setSmsType("2");
            smsLog.setReportStatus("1");
            smsLog.setSmsMsg(resultMap.get("content"));
            smsLog.setExtInfo(resultMap.get("extno"));
            smsLog.setFixTime(DateUtils.parseDate(resultMap.get("receivetime"), "yyyy/MM/dd HH:mm:ss"));
            smsLog.setReportTime(smsLog.getFixTime());
            smsLog.setSubmitTime(smsLog.getFixTime());
            smsLogManager.save(smsLog);
        } catch (ParseException e) {
            logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_UP_SEND_CODE,"短信上行异常,短信信息："+resultMap
                    +"异常信息："+e.getMessage(),e,"");
        }

    }

    /**
     * 通知业务系统
     * @param receivetime
     * @param content
     * @param smsCode
     */
    private void notificationSystem(String receivetime, String content, SmsCode smsCode) {
        String upUrl = smsCode.getUpUrl();

        Map<String, String> paraMap = new HashMap<String, String>();
        // 消息ID 对应下行短信的ID
        paraMap.put("msgId", smsCode.getSmsId());
        //手机号
        paraMap.put("mobile", smsCode.getMobile());
        // 回复内容
        paraMap.put("upMsg", content);
        paraMap.put("extCode",smsCode.getSmsCode());
        // 扩展信息
        paraMap.put("extInfo", smsCode.getExtInfo());
        // 回复时间
        paraMap.put("upTime", receivetime);
        HttpUtil.sendHttpPost(upUrl, paraMap);
    }

}
