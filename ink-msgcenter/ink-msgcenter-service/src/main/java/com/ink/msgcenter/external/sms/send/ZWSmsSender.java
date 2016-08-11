package com.ink.msgcenter.external.sms.send;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.util.DateConvertUtils;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.external.sms.SmsMsg;
import com.ink.msgcenter.mq.SmsDTO;
import com.ink.msgcenter.util.HttpUtil;
import com.ink.msgcenter.util.XmlAnalysisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 筑望发送短信接口
 * Created by aiyungui on 2016/5/24.
 */
@Component("smsSender002")
public class ZWSmsSender implements  SmsSender{

    /**
     * 发送短信
     * @param smsDTO
     * @param content
     * @return
     * @throws Exception
     */
    @Override
    public String send(SmsDTO smsDTO,String content)throws Exception{

        SmsMsg smsMsg = new SmsMsg();
        smsMsg.setContent(content);
        smsMsg.setMobile(smsDTO.getMobile());
        smsMsg.setSendTime(smsDTO.getSendTime());
        smsMsg.setChnParam(smsDTO.getSmsChnCache().getChnParam());
        smsMsg.setExtno(smsDTO.getExtNo());

        Map<String,String> sourceMap = getSourceUrl(smsMsg);
        String sourceUrl = sourceMap.get("sourceUrl");
        sourceMap.remove("sourceUrl");
        return HttpUtil.sendHttpPost(sourceUrl,sourceMap);
    }

    /**
     * 获取smsLog
     * @param smsDTO
     * @param content
     * @param result
     * @return
     * @throws Exception
     */
    @Override
    public SmsLog getSmsLog(SmsDTO smsDTO, String content, String result) throws Exception {
        SmsLog smsLog = new SmsLog();
        smsLog.setChnId(smsDTO.getSmsChnCache().getId());
        if (StringUtils.isNotBlank(smsDTO.getChnCode())){
            smsLog.setChnCode(smsDTO.getChnCode());
        }else{
            smsLog.setChnCode(smsDTO.getSmsChnCache().getChnCode());
        }
        smsLog.setMerctId(smsDTO.getMerchantCache().getId());
        smsLog.setMerctCode(smsDTO.getMerctCode());
        smsLog.setTempId(Long.valueOf(smsDTO.getTempId()));
        smsLog.setMobile(smsDTO.getMobile());
        String sendStatus = "2";
        smsLog.setSmsMsg(content);
        Map<String,String> resultMap = XmlAnalysisUtil.analysisXml(result);
        String taskId = null;
        String responseCode;
        String sendException = null;
        if (!resultMap.isEmpty()){
            taskId = resultMap.get("taskID");
            responseCode = resultMap.get("returnstatus");
            if ("Faild".equalsIgnoreCase(responseCode)){
                sendException = resultMap.get("message");
            }else{
                sendStatus = "1";
            }
        }else{
            sendException = result;
            responseCode = result.split(",")[0];
            if (responseCode.length()>15){
                responseCode = responseCode.substring(0,16);
            }
            if (sendException.length()>400){
                sendException = sendException.substring(0,400);
            }
        }
        smsLog.setTaskId(taskId);
        smsLog.setResponseCode(responseCode);
        smsLog.setSendException(sendException);
        smsLog.setSmsType("1");
        smsLog.setSendType(smsDTO.getSendType());
        smsLog.setSmsId(smsDTO.getMsgId());
        smsLog.setSmsCode(smsDTO.getExtNo());
        smsLog.setExtInfo(smsDTO.getExtInfo());
        smsLog.setSendStatus(sendStatus);
        smsLog.setInfoCode(smsDTO.getBizId());
        smsLog.setSubmitTime(smsDTO.getSubmitTime());
        smsLog.setFixTime(smsDTO.getSendTime());
        smsLog.setReportUrl(smsDTO.getReportUrl());
        if (StringUtils.isBlank(smsLog.getReportUrl())){
            smsLog.setReportUrl(smsDTO.getMerchantCache().getSmsReportUrl());
        }
        smsLog.setUpUrl(smsDTO.getUpUrl());
        if (StringUtils.isBlank(smsLog.getUpUrl())){
            smsLog.setUpUrl(smsDTO.getMerchantCache().getSmsUpUrl());
        }

        smsLog.setSendTime(smsDTO.getRequestTime());
        return smsLog;
    }


    /**
     * 获取资源地址
     * @param smsMsg
     * @return
     * @throws Exception
     */
    public Map<String,String> getSourceUrl(SmsMsg smsMsg)throws Exception{
        Map<String,String> sourceMap = new HashMap<String,String>();

        JSONObject chnParamJson = JSONObject.parseObject(smsMsg.getChnParam());
        String clientUrl = chnParamJson.getString("clientUrl");
//        String action = chnParamJson.getString("action");
        String userId = chnParamJson.getString("userId");
        String account = chnParamJson.getString("account");
        String password = chnParamJson.getString("password");
        String sendTime = DateConvertUtils.format(smsMsg.getSendTime(), "yyyy-MM-dd HH:mm:ss");
        if (null == sendTime){
            sendTime = "";
        }
        String extNo = "";
        if (StringUtils.isNotBlank(smsMsg.getExtno())){
            extNo = smsMsg.getExtno();
        }

        sourceMap.put("sourceUrl","http://"+ clientUrl + "/sms.aspx");
        sourceMap.put("action","send");
        sourceMap.put("userid",userId);
        sourceMap.put("account",account);
        sourceMap.put("password",password);
        sourceMap.put("mobile",smsMsg.getMobile());
        sourceMap.put("content",smsMsg.getContent());
        sourceMap.put("sendTime",sendTime);
        sourceMap.put("extno",extNo);

        return sourceMap;
    }
}
