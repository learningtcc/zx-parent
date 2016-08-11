package com.ink.monitor.dubbo;

import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.SmsService;
import org.springframework.stereotype.Service;

/**
 * 短信发送
 * Created by aiyungui on 2016/5/26.
 */
@Service("smsSender")
public class SmsSender {

//    YinkerLogger loger = YinkerLogger.getLogger(SmsSender.class);
//    @Autowired
//    private SmsService smsService;
//    @Autowired
//    private ConfigInfo configInfo;

    public void sendSms(String mobile,String paramJson,boolean isModule){
//        try{
//            SmsInput smsInput = new SmsInput();
//            smsInput.setMerctCode(configInfo.getMerchantCode());
//            if (isModule){
//                smsInput.setTempId(configInfo.getModuleSmsTemplateId());
//            }else{
//                smsInput.setTempId(configInfo.getServiceSmsTemplateId());
//            }
//
//            smsInput.setParamJson(paramJson);
//            smsInput.setMobile(mobile);
//            MsgOutput output = smsService.sendSms(smsInput);
//
//            if(!output.isSuccess()){
//            	loger.error("短息发送失败,"+output.getRetMsg());
//            }
//        }catch (Exception e){
//            loger.error("调用短信发送接口出现以后常",e);
//            e.printStackTrace();
//        }
    }

}
