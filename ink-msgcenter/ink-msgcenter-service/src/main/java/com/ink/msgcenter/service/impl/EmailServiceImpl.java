package com.ink.msgcenter.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.api.model.input.EmailInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.EmailService;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.cache.TemplateUtil;
import com.ink.msgcenter.cache.object.TemplateCache;
import com.ink.msgcenter.mq.EmailDTO;
import com.ink.msgcenter.mq.MQConstant;
import com.ink.msgcenter.service.check.MsgCheck;
import com.ink.msgcenter.util.CoderUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	YinkerLogger loger = YinkerLogger.getLogger(EmailServiceImpl.class);
	@Autowired
	private TemplateUtil templateUtil;
	@Autowired
    private AmqpTemplate amqpTemplate;// rabbitMq

	@Override
	public MsgOutput sendEmail(EmailInput email) {
		
		MsgOutput output = null;
		try{
			//参数检验
			output = checkEmailInput(email);

			if(output.isSuccess()){
				EmailDTO md = genMsgDto(email);
				output.setMsgId(md.getMsgId());
				amqpTemplate.convertAndSend(MQConstant.MQ_EMAIL_EXCHANGE, MQConstant.MQ_EMAIL_SEND_KEY, md);
			}
		}catch (Exception e){
			if (null == output){
				output =  new MsgOutput();
				output.setRetCode(MsgCode.EMAIL_SEND_FAIL);
				output.setRetMsg("执行发送邮件异常，请联系消息中心");
			}
			loger.error(YkDataContant.MODULE_EAMIL_CODE,YkDataContant.INFO_EAMIL_SEND_CODE,"邮件发送异常:" +e.getMessage(),e,"");
		}
		loger.info(YkDataContant.MODULE_EAMIL_CODE,YkDataContant.INFO_EAMIL_SEND_CODE,output.toString());
		return output;
	}
	
	private MsgOutput checkEmailInput(EmailInput email){

		//验证头消息
		MsgOutput output = MsgCheck.checkMsgInput(email, "2");
		
		if(!output.isSuccess()){
			return output;
		}
		
		if(StringUtils.isBlank(email.getTempId())){
			output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
			output.setRetMsg("模板ID为空");
			
			return output;
		}
		
		TemplateCache tc = templateUtil.getTemplateInfo(email.getTempId(), "2");
		
		if(tc == null){
			output.setRetCode(MsgCode.TEMPLATE_NOT_EXISTS);
			output.setRetMsg("模板不存在");
			
			return output;
		}
		
		if(!tc.getMerctCode().equalsIgnoreCase(email.getMerctCode())){
			output.setRetCode(MsgCode.TEMPLATE_MERCHANT_NOT_MATCH);
			output.setRetMsg("模板不属于本商户");
			
			return output;
		}
		
		if(email.getEmail().split("@").length != 2){
			output.setRetCode(MsgCode.PARAM_NOT_MATCH_DOC);
			output.setRetMsg("邮箱数量超出限制");
		}
		
		return output;
		
	}
	
	private EmailDTO genMsgDto(EmailInput input){
		EmailDTO md = new EmailDTO();
		md.setBizId(input.getBizId());
		md.setEmail(input.getEmail());
		md.setMerctCode(input.getMerctCode());
		md.setMsgId(CoderUtil.genMsgId());
		md.setParamJson(input.getParamJson());
		md.setTempId(input.getTempId());
		md.setSubmitTime(new Date());
		return md;
	}
}