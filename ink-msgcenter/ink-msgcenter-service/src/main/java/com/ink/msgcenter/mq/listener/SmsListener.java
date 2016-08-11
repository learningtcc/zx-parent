package com.ink.msgcenter.mq.listener;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.cache.TemplateUtil;
import com.ink.msgcenter.cache.object.TemplateCache;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.service.ISmsChannelManager;
import com.ink.msgcenter.external.sms.send.SmsSender;
import com.ink.msgcenter.mq.MQConstant;
import com.ink.msgcenter.mq.SmsDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;
import java.util.List;

/**
 * 筑望短信rabbitMq监听器
 * Created by aiyungui on 2016/5/18.
 */
public class SmsListener {

    private TemplateUtil templateUtil;
    private SmsSender smsSender;
    private AmqpTemplate amqpTemplate;
    private ISmsChannelManager smsChannelManager;

    private YinkerLogger logger = YinkerLogger.getLogger(getClass());

    private void init(){
        templateUtil = (TemplateUtil) SpringApplicationContext.getBean("templateUtil");
        amqpTemplate = (AmqpTemplate) SpringApplicationContext.getBean("amqpTemplate");

    }

    /**
     * 发送短信
     * @param smsDTO
     */
    public void sendMessage(SmsDTO smsDTO) {

        String result = "error,发送短信失败";
        String content = "";
        try {
            if(templateUtil == null){
                init();
            }
            String chnType = getChnType(smsDTO);
            if("-1".equals(chnType)){
                result = "通道代码不存在，chnCode:" + smsDTO.getChnCode();
                throw (new Exception(result));
            }
            if (null == smsSender){
                String beanName = "smsSender" + chnType;
                smsSender = (SmsSender) SpringApplicationContext.getBean(beanName);
            }
            TemplateCache tc = templateUtil.getTemplateInfo(smsDTO.getTempId(), "1");
            if(tc == null){
                result = "error,短信模板为空";
            }else{
                content = templateUtil.generateContent(tc, smsDTO.getParamJson());
                if(StringUtils.isBlank(content)){
                    result = "error,模板解析失败";
                }else{
                     /*设置短信提交供应商时间*/
                    smsDTO.setRequestTime(new Date());
                    result = smsSender.send(smsDTO,content);
                }
            }
        }catch (Exception e){
            logger.error(YkDataContant.MODULE_SMS_CODE, YkDataContant.INFO_SMS_DOWN_SEND_CODE, "发送短信失败，短信信息:" + smsDTO
                    +",异常信息：" + e.getMessage(), e, "");
        }

        sendSmsLogMq(smsDTO, content, result);
        logger.info(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_DOWN_SEND_CODE,"短信发送结果：" + result );
    }

    /**
     * 获取通道类型
     * @param smsDTO
     * @return
     */
    private String getChnType(SmsDTO smsDTO){
        String chnType = smsDTO.getSmsChnCache().getChnType();

        if (StringUtils.isNotBlank(smsDTO.getChnCode())){//不为空则使用传入通道
            smsChannelManager = (ISmsChannelManager) SpringApplicationContext.getBean("smsChannelManager");
            SmsChannel smsChannel = new SmsChannel();
            smsChannel.setChnCode(smsDTO.getChnCode());
            smsChannel.setStatus("0");
            List<SmsChannel> smsChannelList = smsChannelManager.findSmsChannels(smsChannel);
            if (null != smsChannelList && !smsChannelList.isEmpty()){
                smsChannel = smsChannelList.get(0);
                chnType = smsChannel.getChnType();
            }else{
                chnType = "-1";
            }
        }

        return chnType;
    }

    /**
     *发送日志队列
     * @param smsDTO
     * @param content
     * @param result
     */
    public void sendSmsLogMq(SmsDTO smsDTO,String content,String result){
        SmsLog smsLog = null;
        try{
            smsLog = smsSender.getSmsLog(smsDTO,content,result);
            amqpTemplate.convertAndSend(MQConstant.MQ_SMS_EXCHANGE, MQConstant.MQ_SMS_STORE_KEY, smsLog);
        }catch (Exception e){
            logger.error(YkDataContant.MODULE_SMS_CODE,YkDataContant.INFO_SMS_DOWN_SEND_CODE,
                    "发送日志队列失败,日志信息："+smsLog+" 异常信息：" + e.getMessage(),e,"");
        }
    }

}
