package com.ink.monitor.dubbo;

import com.ink.base.log.util.YinkerLogger;
import com.ink.msgcenter.api.model.input.EmailInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.EmailService;
import org.springframework.stereotype.Service;

/**
 * 邮件发送
 * Created by aiyungui on 2016/5/26.
 */
@Service("emailSender")
public class EmailSender {

    YinkerLogger loger = YinkerLogger.getLogger(EmailSender.class);
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private ConfigInfo configInfo;

    public void sendEmail(String userEmail, String paramJson, boolean isModule){
//        try{
//            EmailInput emailInput = new EmailInput();
//            emailInput.setMerctCode(configInfo.getMerchantCode());
//            if (isModule){
//                emailInput.setTempId(configInfo.getModuleEmailTemplateId());
//            }else {
//                emailInput.setTempId(configInfo.getServiceEmailTemplateId());
//            }
//
//            emailInput.setEmail(userEmail);
//            emailInput.setParamJson(paramJson);
//
//            MsgOutput output = emailService.sendEmail(emailInput);
//
//            if(!output.isSuccess()){
//            	loger.error("邮件发送失败,"+output.getRetMsg());
//            }
//
//        }catch (Exception e){
//            loger.error("调用邮件发送接口出现以后常",e);
//            e.printStackTrace();
//        }

    }
}
