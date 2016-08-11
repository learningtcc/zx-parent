package com.ink.msgcenter.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.api.model.input.SmsExtInput;
import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.api.model.input.SmsModel;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.SmsService;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.cache.ChannelUtil;
import com.ink.msgcenter.cache.MerchantUtil;
import com.ink.msgcenter.cache.TemplateUtil;
import com.ink.msgcenter.cache.object.MerchantCache;
import com.ink.msgcenter.cache.object.SmsChnCache;
import com.ink.msgcenter.cache.object.TemplateCache;
import com.ink.msgcenter.mq.MQConstant;
import com.ink.msgcenter.mq.SmsDTO;
import com.ink.msgcenter.service.check.MsgCheck;
import com.ink.msgcenter.util.SmsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短信发送接口实现类
 * Created by aiyungui on 2016/5/18.
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService{

    YinkerLogger loger = YinkerLogger.getLogger(SmsServiceImpl.class);
    @Autowired
    private TemplateUtil templateUtil;//模版工具类
    @Autowired
    private AmqpTemplate amqpTemplate;// rabbitMq
    @Autowired
    private ChannelUtil channelUtil;
    @Autowired
    private MerchantUtil merchantUtil;

    private MerchantCache merchantCache;
    private SmsChnCache smsChnCache;
    /**
     * 发送短信
     *
     * @param smsInput
     * @return
     */
    @Override
    public MsgOutput sendSms(SmsInput smsInput) {

        MsgOutput output = null;
        try{
            output = checkSmsInput(smsInput);   //参数检验
            if(output.isSuccess()){
                SmsDTO smsDTO = SmsUtil.genMsgDto(smsInput, merchantCache, smsChnCache);
                output.setMsgId(smsDTO.getMsgId());
                sendMq(smsDTO,"1");
            }
        }catch (Exception e){
            if (null == output){
                output =  new MsgOutput();
                output.setRetCode(MsgCode.SMS_SUBMIT_FAIL);
                output.setRetMsg("执行发送短信异常，请联系消息中心");
            }
            loger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_DOWN_SEND_CODE,"短信发送异常:" +e.getMessage(),e,"");
        }
        loger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_DOWN_SEND_CODE,output.toString());
        return output;
    }

    /**
     * 发送短信(含扩展接口)
     * @param smsExtInput
     * @return
     */
    @Override
    public MsgOutput sendSmsWithExt(SmsExtInput smsExtInput) {

        MsgOutput output = null;
        try{
            output = checkSmsExtInput(smsExtInput);

            if(output.isSuccess()){
                SmsDTO smsDTO = SmsUtil.genMsgDto(smsExtInput,merchantCache,smsChnCache);
                output.setMsgId(smsDTO.getMsgId());
                smsDTO.setExtNo(smsExtInput.getExtNo());
                smsDTO.setExtInfo(smsExtInput.getExtInfo());
                smsDTO.setUpUrl(smsExtInput.getUpUrl());
                sendMq(smsDTO,"1");
            }
        }catch (Exception e){
            if (null == output){
                output =  new MsgOutput();
                output.setRetCode(MsgCode.SMS_SUBMIT_FAIL);
                output.setRetMsg("执行发送短信异常，请联系消息中心");
            }
            loger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_EXT_SEND_CODE,"短信发送异常:" +e.getMessage(),e,"");
        }
        loger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_EXT_SEND_CODE,output.toString());
        return output;
    }

    /**
     * 短信群发接口
     *
     * @param smsMassInput
     * @return
     */
    @Override
    public MsgOutput sendMassSms(SmsMassInput smsMassInput) {

        MsgOutput output = null;
        try{
            output = checkSmsMassInput(smsMassInput);
            if(output.isSuccess()){
                SmsDTO smsDTO = SmsUtil.genMassMsgDto(smsMassInput, merchantCache, smsChnCache);
                output.setMsgId(smsDTO.getMsgId());
                List<String> mobileList = smsMassInput.getMobileList();
                int mark = 10000;
                float mobileSize = (float ) mobileList.size() /mark ;
                for (int i=0 ; i < mobileSize; i++){//每次最多只允许发送1w条
                    int down = (i * mark) ;
                    int upper = (i + 1) * mark;
                    if (upper > mobileList.size()){
                        upper =  mobileList.size();
                    }
                    StringBuilder mobileSb = new StringBuilder();
                    for (int j = down; j < upper; j++) {
                        mobileSb.append(",").append(mobileList.get(j));
                    }
                    smsDTO.setMobile(mobileSb.substring(1));
                    sendMq(smsDTO,"2");
                }
            }
        }catch (Exception e){
            if (null == output){
                output =  new MsgOutput();
                output.setRetCode(MsgCode.SMS_SUBMIT_FAIL);
                output.setRetMsg("执行发送短信异常，请联系消息中心");
            }
            loger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_MASS_SEND_CODE,"短信发送异常:" +e.getMessage(),e,"");
        }
        loger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_MASS_SEND_CODE,output.toString());
        return output;
    }

    /**
     * 发送至短信MQ
     * @param smsDTO
     */
    private void sendMq(SmsDTO smsDTO,String sendType){

        if ("1".equals(sendType)){//单手机号接口
            amqpTemplate.convertAndSend(MQConstant.MQ_SMS_EXCHANGE, MQConstant.MQ_SMS_KEY_SINGLE, smsDTO);
        }else if ("2".equals(sendType)){//群发接口
            amqpTemplate.convertAndSend(MQConstant.MQ_SMS_EXCHANGE, MQConstant.MQ_SMS_KEY_MASS, smsDTO);
        }

    }

    /**
     * 检查短信群发参数
     * @param smsMassInput
     * @return
     */
    private MsgOutput checkSmsMassInput(SmsMassInput smsMassInput){
        MsgOutput output = checkSmsModel(smsMassInput);

        if (null == smsMassInput.getMobileList() || smsMassInput.getMobileList().isEmpty()){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("手机号不能为空");
            return output;
        }

        int num = 50000;
        if(smsMassInput.getMobileList().size() > num){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("手机号数量超出限制,最多一次性发送" + num);
            return output;
        }

        for (int i =0; i < smsMassInput.getMobileList().size();i++){
            String mobile = smsMassInput.getMobileList().get(i);
            if(!mobile.matches("1\\d{10}")){
                output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
                output.setRetMsg("第" + (i+1) + "条手机号有问题，请检查。手机号为：" + mobile);
                return output;
            }
        }

        return output;
    }


    /**
     * 检查扩展子号参数信息
     * @param smsExtInput
     * @return
     */
    private MsgOutput checkSmsExtInput(SmsExtInput smsExtInput){
        MsgOutput output = checkSmsInput(smsExtInput);

        if(StringUtils.isBlank(smsExtInput.getExtNo())){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("扩展代码为空");
            return output;
        }
        if(smsExtInput.getExtNo().length()>5){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("扩展代码大于5位数");
            return output;
        }
        return output;
    }

    /**
     * 检查smsInput
     * @param smsInput
     * @return
     */
    private MsgOutput checkSmsInput(SmsInput smsInput){
        MsgOutput output = checkSmsModel(smsInput);

        if (StringUtils.isBlank(smsInput.getMobile())){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("手机号不能为空");
            return output;
        }

        if(!smsInput.getMobile().matches("1\\d{10}")){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("请输入正确的手机号码,此接口为单号发送");
            return output;
        }
        return output;
    }

    /**
     * 检查参数信息
     * @param smsModel
     * @return
     */
    private MsgOutput checkSmsModel(SmsModel smsModel){
        //验证头消息
        MsgOutput output = MsgCheck.checkMsgInput(smsModel, "1");

        if(!output.isSuccess()){
            return output;
        }

        if(StringUtils.isBlank(smsModel.getTempId())){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("模板ID为空");
            return output;
        }

        if(StringUtils.isBlank(smsModel.getTempId())){
            output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
            output.setRetMsg("模板ID为空");
            return output;
        }

        TemplateCache tc = templateUtil.getTemplateInfo(smsModel.getTempId(), "1");

        if(tc == null){
            output.setRetCode(MsgCode.TEMPLATE_NOT_EXISTS);
            output.setRetMsg("模板不存在");
            return output;
        }

        if(!tc.getMerctCode().equalsIgnoreCase(smsModel.getMerctCode())){
            output.setRetCode(MsgCode.TEMPLATE_MERCHANT_NOT_MATCH);
            output.setRetMsg("模板不属于本商户");
            return output;
        }

        //从缓存中获取商户信息
        merchantCache = merchantUtil.getMerchantInfo(smsModel.getMerctCode());
        if(StringUtils.isBlank(merchantCache.getSmsChnCode())){
            output.setRetCode(MsgCode.MERCHANT_NOT_FOUND);
            output.setRetMsg("商户未配置通道");
        }else{
            String[] chnCodeArray = merchantCache.getSmsChnCode().split(",");
            smsChnCache = SmsUtil.chooseHighLevelChn(chnCodeArray, channelUtil);
            if(smsChnCache == null){
                output.setRetCode(MsgCode.MERCHANT_CHN_NOT_EXISTS);
                output.setRetMsg("短信通道未查到");
            }

            if (StringUtils.isNotBlank(smsModel.getChnCode())){
                boolean isHave = false;
                for(String chnCode : chnCodeArray){
                    if (chnCode.equals(smsModel.getChnCode())){
                        isHave = true;
                        break;
                    }
                }
                if (!isHave){
                    output.setRetCode(MsgCode.MERCHANT_CHN_NOT_EXISTS);
                    output.setRetMsg("传入通道代码参数不存在");
                }
            }
        }

        return output;
    }

}
