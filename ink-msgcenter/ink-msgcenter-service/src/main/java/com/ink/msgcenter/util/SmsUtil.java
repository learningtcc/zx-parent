package com.ink.msgcenter.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.cache.ChannelUtil;
import com.ink.msgcenter.cache.object.MerchantCache;
import com.ink.msgcenter.cache.object.SmsChnCache;
import com.ink.msgcenter.mq.SmsDTO;

/**
 * 短信工具类
 * Created by aiyungui on 2016/5/20.
 */
public class SmsUtil {

    /**
     * 选择最高优先级短信通道
     * @param chnCodeArray
     * @return
     */
    public static SmsChnCache chooseHighLevelChn(String[] chnCodeArray,ChannelUtil channelUtil){

        List<SmsChnCache> chnList = new ArrayList<SmsChnCache>();

        int level = -1;
        int index = 0;

        for (int i = 0; i < chnCodeArray.length; i++) {
            SmsChnCache smsChannel = channelUtil.getSmsChannel(chnCodeArray[i]);

            chnList.add(smsChannel);
            
            if(smsChannel == null){
                continue;
            }

            if(level < 0 || level > smsChannel.getPriorityLevel()){
                level = smsChannel.getPriorityLevel();
                index = i;
            }
        }

        if(chnList.size() > 0){
            return chnList.get(index);
        }

        return null;
    }

    /**
     * 设置SmsDTO
     * @param smsInput
     * @param merchantCache
     * @param smsChnCache
     * @return
     */
    public static SmsDTO genMsgDto(SmsInput smsInput,MerchantCache merchantCache,SmsChnCache smsChnCache){
        SmsDTO md = new SmsDTO();
        md.setBizId(smsInput.getBizId());
        md.setMobile(smsInput.getMobile());
        md.setMerctCode(smsInput.getMerctCode());
        md.setMsgId(CoderUtil.genMsgId());
        md.setParamJson(smsInput.getParamJson());
        md.setTempId(smsInput.getTempId());
        md.setSendTime(smsInput.getSendTime());
        md.setMerchantCache(merchantCache);
        md.setSmsChnCache(smsChnCache);
        md.setSubmitTime(new Date());
        md.setSendType("1");
        md.setChnCode(smsInput.getChnCode());
        md.setReportUrl(smsInput.getReportUrl());
        return md;
    }

    /**
     * 设置群发短信SmsDTO
     * @param smsMassInput
     * @param merchantCache
     * @param smsChnCache
     * @return
     */
    public static SmsDTO genMassMsgDto(SmsMassInput smsMassInput,MerchantCache merchantCache,SmsChnCache smsChnCache){
        SmsDTO md = new SmsDTO();
        md.setBizId(smsMassInput.getBizId());
        md.setMerctCode(smsMassInput.getMerctCode());
        md.setMsgId(CoderUtil.genMsgId());
        md.setParamJson(smsMassInput.getParamJson());
        md.setTempId(smsMassInput.getTempId());
        md.setSendTime(smsMassInput.getSendTime());
        md.setMerchantCache(merchantCache);
        md.setSmsChnCache(smsChnCache);
        md.setSubmitTime(new Date());
        md.setSendType("2");
        md.setChnCode(smsMassInput.getChnCode());
        md.setReportUrl(smsMassInput.getReportUrl());

        return md;
    }
}