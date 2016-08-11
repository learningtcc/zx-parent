package com.ink.msgcenter.external.sms.zw.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.Yedis;
import com.ink.base.util.DateConvertUtils;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.po.SmsReport;
import com.ink.msgcenter.core.service.ISmsLogManager;
import com.ink.msgcenter.core.service.ISmsReportManager;
import com.ink.msgcenter.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 获得短信状态报告后，对日志进行处理，并通知商户
 * Created by aiyungui on 2016/5/27.
 */
@Service("zwSmsReportLogService")
public class ZwSmsReportLogService {

    private YinkerLogger logger = YinkerLogger.getLogger(getClass());
    @Autowired
    private ISmsLogManager smsLogManager;
    @Autowired
    private ISmsReportManager smsReportManager;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private Yedis yedis;

    /**
     * 保存至缓存
     * @param smsResult
     */
    public void saveSmsReportStatus(Map<String, String> smsResult,String chnType) {
        try{
            Map<String,Object>  resultMap = new HashMap<String,Object>();
            resultMap.putAll(smsResult);
            JSONObject jsonObject = new JSONObject(resultMap);

            yedis.sadd(CacheConstant.MSG_STATUS_REPORT_LIST + "_" + chnType,jsonObject.toJSONString());
        }catch (Exception e){
            logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_STATUS_REPORT_SEND_CODE ,
                "保存短信状态报告至redis缓存异常" + smsResult,e,"");
        }
    }

    /**
     * 更新短息日志
     * @param smsReport
     * @param smsResult
     */
    public void operateSmsReportLog(SmsReport smsReport,Map<String, String> smsResult) {

        try{
            String logId = smsReport.getLogId();
            String smsType = smsReport.getSmsType();
            String reportStatus = getStatus(smsResult);
            String reportReceiveTime = smsResult.get("receivetime");

            // 2、更新短信日志记录
            if ("1".equals(smsType)){//普通短信，更新数据库
                SmsLog smsLog = new SmsLog();
                smsLog.setId(Long.valueOf(logId));
                smsLog.setMerctCode(smsReport.getMerctCode());
                smsLog.setReportStatus(reportStatus);
                smsLog.setReportTime(DateConvertUtils.parse(reportReceiveTime, "yyyy/MM/dd HH:mm:ss"));
                smsLogManager.updateSmsReportInfo(smsLog);
            }else{//营销短信，更新数据库
                updateMongoLog(smsReport,smsResult);
            }
            //3、删除短信报告表记录
            smsReportManager.deleteInfo(smsReport);

        }catch (Exception e){
            logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_STATUS_REPORT_SEND_CODE,
                    "短信状态报告更新异常" + smsReport,e,"");
        }
    }



    /**
     * 通知业务系统
     * @param reportListMap
     */
    public void notifyBizSystem(Map<String, List<Map<String, Object>>> reportListMap) {

        Set<String> taskIdSet = reportListMap.keySet();
        for (String taskId : taskIdSet){
            List<Map<String,String>> notifyList = new ArrayList<Map<String,String>>();
            String reportUrl = null;

            List<Map<String, Object>> reportMapList =  reportListMap.get(taskId);
            for (Map<String,Object> reportMap : reportMapList){
                Map<String,String> smsResult = (Map<String, String>) reportMap.get("smsResult");
                SmsReport smsReport = (SmsReport) reportMap.get("smsReport");
                notifyList.add(getNotifyBizSystemInfo(smsResult,smsReport));
                if (StringUtils.isBlank(reportUrl)){
                    reportUrl = smsReport.getReportUrl();
                }
            }
            if (StringUtils.isNotBlank(reportUrl)){
                Map<String,String> paraMap = new HashMap<String,String>();
                paraMap.put("message", JSON.toJSONString(notifyList));
                HttpUtil.sendHttpPost(reportUrl, paraMap);
            }else{
                logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_STATUS_REPORT_SEND_CODE,
                        "短信状态报告url为空，通知信息：" + notifyList,null);
            }

        }
    }

    /**
     * 更新短信mongo日志
     * @param smsReport
     * @param smsResult
     */
    private void updateMongoLog(SmsReport smsReport, Map<String, String> smsResult) {

        String reportStatus = getStatus(smsResult);
        String reportReceivetime = smsResult.get("receivetime");

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(smsReport.getLogId())));

        Update update = new Update();
        update.addToSet("reportStatus",reportStatus);
        update.addToSet("reportTime",reportReceivetime);
        mongoTemplate.updateFirst(query,update,"sms_log_"+smsReport.getMerctCode());
    }

    /**
     * 获取执行结果信息
     * @param smsResult
     */
    private Map<String,String> getNotifyBizSystemInfo(Map<String,String> smsResult,SmsReport smsReport) {

        String status = smsResult.get("status");
        String retCode;
        Map<String,String> paraMap = new HashMap<String,String>();
        if ("10".equals(status)){
            retCode = MsgCode.SMS_RECV_SUCCESS;
        }else{
            retCode = MsgCode.SMS_RECV_FAIL;
        }

        paraMap.put("msgId", smsReport.getSmsId());
        paraMap.put("retCode",retCode);
        paraMap.put("mobile",smsReport.getMobile());
        paraMap.put("submitTime",smsResult.get("receivetime"));
        return paraMap;
    }

    /**
     * 转换筑望短信状态
     * @param smsResult
     * @return
     */
    private String getStatus(Map<String, String> smsResult) {
        String status = smsResult.get("status");
        if ("10".equals(status)){
            return "1";
        }else{
            return "0";
        }
    }
}
