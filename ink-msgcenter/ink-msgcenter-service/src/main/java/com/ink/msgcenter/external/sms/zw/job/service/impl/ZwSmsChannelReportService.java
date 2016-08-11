package com.ink.msgcenter.external.sms.zw.job.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.po.SmsReport;
import com.ink.msgcenter.core.query.SmsReportQuery;
import com.ink.msgcenter.core.service.ISmsReportManager;
import com.ink.msgcenter.external.sms.zw.job.service.SmsChannelReportService;
import com.ink.msgcenter.util.HttpUtil;
import com.ink.msgcenter.util.XmlAnalysisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户获取短信报告任务
 * Created by aiyungui on 2016/5/20.
 */
@Service("smsChannelReportService002")
public class ZwSmsChannelReportService implements SmsChannelReportService {

    private YinkerLogger logger = YinkerLogger.getLogger(getClass());
    private ISmsReportManager smsReportManager;
    private ZwSmsReportLogService zwSmsReportLogService;

    private void init(){
        smsReportManager = (ISmsReportManager) SpringApplicationContext.getBean("smsReportManager");
        zwSmsReportLogService = (ZwSmsReportLogService) SpringApplicationContext.getBean("zwSmsReportLogService");
    }

    public void operateSmsReport(SmsChannel smsChannel) {
        try{
            init();
            String sourceUrl = getSourceUrl(smsChannel.getChnParam());
            String result = HttpUtil.sendHttpPost(sourceUrl);

            logger.info("短信状态报告信息:" + result);
            List<Map<String,String>> mapList = XmlAnalysisUtil.analysisXmlForSecond(result);
            if (mapList.isEmpty()){
                throw(new Exception("请求短信状态报告接口，返回接口为"+result+"，url:" + sourceUrl));
            }

            Map<String,String> resultMap = mapList.get(0);
            if (StringUtils.isNotBlank(resultMap.get("error"))){
                throw(new Exception("获取短信通道状态报告出现错误，错误码:" + resultMap.get("error")
                        + "错误描述" + resultMap.get("remark")));
            }

            operateSmsReport(mapList,smsChannel);

        }catch (Exception e){
            logger.error(YkDataContant.MODULE_SMS_CODE, YkDataContant.INFO_SMS_STATUS_REPORT_SEND_CODE,
                    "获取短信状态报告失败，通道编号:" + smsChannel.getChnCode() + ";错误信息：" + e.getMessage(),"");
        }
    }

    private void operateSmsReport(List<Map<String, String>> mapList,SmsChannel smsChannel) {

        Map<String,List<Map<String,Object>>> reportListMap = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> reportMapList;
        for (Map<String,String> smsResult : mapList){
            String reportMobile = smsResult.get("mobile");
            String reportTaskId = smsResult.get("taskid");
            if (reportListMap.containsKey(reportTaskId)){
                reportMapList = reportListMap.get(reportTaskId);
            }else{
                reportMapList = new ArrayList<Map<String,Object>>();
            }
            //1、获取系统记录短信报告
            SmsReportQuery smsReportQuery = new SmsReportQuery();
            smsReportQuery.setMobile(reportMobile);
            smsReportQuery.setTaskId(reportTaskId);
            List<SmsReport> smsReports = smsReportManager.find(smsReportQuery);
            if (null == smsReports || smsReports.isEmpty()){//还木有存储到数据库，此时存储至redis
                zwSmsReportLogService.saveSmsReportStatus(smsResult, smsChannel.getChnType());
            }else{//直接进行操作
                SmsReport smsReport = smsReports.get(0);
                //操作短信状态报告日志信息
                zwSmsReportLogService.operateSmsReportLog(smsReport, smsResult);

                Map<String,Object> reportMap = new HashMap<String,Object>();
                reportMap.put("smsReport",smsReport);
                reportMap.put("smsResult",smsResult);
                reportMapList.add(reportMap);
                reportListMap.put(reportTaskId,reportMapList);
            }
        }
        //通知业务系统
        zwSmsReportLogService.notifyBizSystem(reportListMap);
    }

    /**
     * 获取资源地址
     * @return
     * @throws Exception
     */
    private String getSourceUrl(String chnParam)throws Exception{
        JSONObject chnParamJson = JSONObject.parseObject(chnParam);
        String clientUrl = chnParamJson.getString("clientUrl");
        String userId = chnParamJson.getString("userId");
        String account = chnParamJson.getString("account");
        String password = chnParamJson.getString("password");

        StringBuilder sourceUrl = new StringBuilder();
        sourceUrl.append("http://").append(clientUrl).append("/statusApi.aspx").append("?action=query")
                .append("&userid=").append(userId).append("&account=").append(account)
                .append("&password=").append(password);
        return sourceUrl.toString();
    }
}
