package com.ink.msgcenter.mq.listener;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.cache.ChannelUtil;
import com.ink.msgcenter.cache.MerchantUtil;
import com.ink.msgcenter.cache.TemplateUtil;
import com.ink.msgcenter.cache.object.EmailChnCache;
import com.ink.msgcenter.cache.object.MerchantCache;
import com.ink.msgcenter.cache.object.TemplateCache;
import com.ink.msgcenter.core.po.EmailAnalyze;
import com.ink.msgcenter.core.po.EmailLog;
import com.ink.msgcenter.core.service.IEmailAnalyzeManager;
import com.ink.msgcenter.core.service.IEmailLogManager;
import com.ink.msgcenter.external.email.EmailMsg;
import com.ink.msgcenter.external.email.EmailSender;
import com.ink.msgcenter.mq.EmailDTO;
import com.ink.msgcenter.mq.MQConstant;
import com.ink.msgcenter.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 邮件监听器
 * @author zongtt
 *	2016年5月13日13:15:23
 */
public class EmailListener {
	
	private TemplateUtil templateUtil;
	
	private MerchantUtil merchantUtil;
	
	private ChannelUtil channelUtil;
	
	private EmailSender emailSender;
	
	@Autowired
    private AmqpTemplate amqpTemplate;// rabbitMq
	
	private IEmailLogManager emailLogManager;
	
	private IEmailAnalyzeManager emailAnalyzeManager;
	
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	private void init(){
		templateUtil = (TemplateUtil)SpringApplicationContext.getBean("templateUtil");
		merchantUtil = (MerchantUtil)SpringApplicationContext.getBean("merchantUtil");
		channelUtil = (ChannelUtil)SpringApplicationContext.getBean("channelUtil");
		emailSender = (EmailSender)SpringApplicationContext.getBean("emailSender");
		emailLogManager = (IEmailLogManager)SpringApplicationContext.getBean("emailLogManager");
		emailAnalyzeManager = (IEmailAnalyzeManager)SpringApplicationContext.getBean("emailAnalyzeManager");
	}
	
	/**
	 * 邮件发送监听器
	 * @param emailMsg
	 */
	public void sendEmail(EmailDTO md){
		
		if(templateUtil == null){
			init();
		}
		EmailLog log = null;
		boolean isSuccess  = false;
		try{
			MerchantCache mc = merchantUtil.getMerchantInfo(md.getMerctCode());

			String result = "";

			log = new EmailLog();

			//发送邮箱
			log.setEmail(md.getEmail());
			//业务单号
			log.setInfoCode(md.getBizId());
			//业务系统提交时间
			log.setSubmitTime(md.getSubmitTime());
			log.setEmailId(md.getMsgId());
			log.setEmailType("0");

			if(StringUtils.isBlank(mc.getMailChnCode())){
				result = "商户未配置通道";
			}else{
				//设置商户代码
				log.setMerctCode(mc.getMerctSn());
				//设置商户ID
				log.setMerctId(String.valueOf(mc.getId()));

				String[] chnCodeArray = mc.getMailChnCode().split(",");

				EmailChnCache ecc = chooseHighLevelChn(chnCodeArray);

				if(ecc == null){
					result = "邮件通道未查到";
				}else {

					//设置通道代码
					log.setChnCode(ecc.getChnCode());
					//设置通道ID
					log.setChnId(ecc.getId());

					TemplateCache tc = templateUtil.getTemplateInfo(md.getTempId(), "2");

					if(tc == null){
						result = "邮件模板为空";
					}else{
						//设置模板ID
						log.setTempId(tc.getId());
						//设置主题
						log.setSubject(tc.getSubject());

						String content = templateUtil.generateContent(tc, md.getParamJson());

						if(StringUtils.isBlank(content)){
							result = "模板解析失败";
						}else{

							EmailMsg msg = new EmailMsg();
							msg.setMailChn(ecc.getChnCode());
							msg.setMailFrom(ecc.getMail());
							msg.setEmail(md.getEmail());
							msg.setMailHost(ecc.getHost());
							msg.setMailPassword(ecc.getPassword());
							msg.setMailPort(ecc.getPort());
							msg.setMailUserName(ecc.getUserName());
							msg.setSubject(tc.getSubject());
							msg.setContent(content);

							result = emailSender.send(msg);

							log.setMailMsg(content);
							log.setSendTime(new Date());
						}
					}
				}
			}

			if(StringUtils.isBlank(result)){
				//发送成功
				log.setSendStatus("1");
				isSuccess = true;
			}else{
				//发送失败
				log.setSendStatus("2");
			}
			log.setSendException(result);
			amqpTemplate.convertAndSend(MQConstant.MQ_EMAIL_EXCHANGE, MQConstant.MQ_EMAIL_STORE_KEY, log);

			logger.info(YkDataContant.MODULE_EAMIL_CODE, YkDataContant.INFO_EAMIL_SEND_CODE, "邮件发送状态： " + isSuccess +"，邮箱信息:" + md);
		}catch (Exception e){
			logger.error(YkDataContant.MODULE_EAMIL_CODE,YkDataContant.INFO_EAMIL_SEND_CODE,"发送邮箱异常，邮箱信息:"+md+",异常信息：",e,"");
		}
	}
	
	/**
	 * 存储日志数据
	 * @param log
	 */
	public void storeEmail(EmailLog log){
		
		if(templateUtil == null){
			init();
		}

		try{
			emailLogManager.save(log);

			if(log.getSendStatus().equalsIgnoreCase("1")){//发送成功
				recordEmailAnalyze(log,1,1,0);
			}else{
				recordEmailAnalyze(log,1,0,1);
			}

			/**
			 * 通知业务系统
			 */
			MerchantCache mc = merchantUtil.getMerchantInfo(log.getMerctCode());

			if(mc != null && StringUtils.isNotBlank(mc.getEmailNotifyUrl())){

				Map<String, String> paraMap = new HashMap<String,String>();

				paraMap.put("msgId", log.getEmail());
				if(log.getSendStatus().equalsIgnoreCase("1")){//发送成功
					paraMap.put("status", MsgCode.EMAIL_SEND_SUCCESS);
				}else{
					paraMap.put("status", MsgCode.EMAIL_SEND_FAIL);
				}
				paraMap.put("sendTime", DateUtil.formatToYYYYMMDDMMHHSS(log.getSendTime()));

				HttpUtil.sendHttpPost(mc.getEmailNotifyUrl(), paraMap);
			}
			logger.info(YkDataContant.MODULE_EAMIL_CODE, YkDataContant.INFO_EAMIL_SEND_CODE, "邮箱日志保存成功");
		}catch (Exception e){
			logger.error(YkDataContant.MODULE_EAMIL_CODE,YkDataContant.INFO_EAMIL_SEND_CODE,"发送邮箱日志存储异常，日志信息" + log
					+ "，异常信息："+ e.getMessage(),e,"");
		}

		
//		String[] emailArray = log.getEmail().split(",");
//		
//		BeanCopier copier = BeanCopier.create(EmailLog.class, EmailLog.class,
//                false);
		
//		if(emailArray.length > 1){
//			List<EmailLog> storeList = new ArrayList<EmailLog>();
//			BeanCopier copier = BeanCopier.create(EmailLog.class, EmailLog.class,
//	                 false);
//			for (String email : emailArray) {
//				EmailLog storeLog = new EmailLog();
//				copier.copy(log, storeLog, null);
//				storeLog.setEmail(email);
//				storeList.add(storeLog);
//			}
//			
//			emailLogManager.saveBatch(storeList);
//		}else {
//			emailLogManager.save(log);
//		}
		
//		for (String email : emailArray) {
//			EmailLog storeLog = new EmailLog();
//			copier.copy(log, storeLog, null);
//			storeLog.setEmail(email);
//			emailLogManager.save(storeLog);
//		}
		
//		logger.info("end:"+System.currentTimeMillis());
		
		//TODO结果通知
		
	}

	private void recordEmailAnalyze(EmailLog log, int sendCount,int successCount,int failCount) {
		int count = updateEmailAnalyze(log, sendCount, successCount, failCount);
		
		if(count == 0){
			EmailAnalyze analyze = new EmailAnalyze();
			analyze.setAnalyzeDateString(DateUtil.formatToYYYYMMDD(new Date()));
			analyze.setChnCode(log.getChnCode());
			analyze.setChnId(log.getChnId());
			analyze.setSendCount(Long.valueOf(1));
			analyze.setSuccessCount(Long.valueOf(successCount));
			analyze.setFailCount(Long.valueOf(failCount));
			
			try {
				emailAnalyzeManager.save(analyze);
			} catch (SqlSessionException e) {
				updateEmailAnalyze(log, sendCount, successCount, failCount);
			}
			
		}
	}

	private int updateEmailAnalyze(EmailLog log, int sendCount, int successCount, int failCount) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("chnId", log.getChnId());
		paramMap.put("chnCode", log.getChnCode());
		paramMap.put("analyzeDate", DateUtil.formatToYYYYMMDD(new Date()));
		paramMap.put("sendCount", sendCount);
		paramMap.put("successCount", successCount);
		paramMap.put("failCount", failCount);
//		if(log.getSendStatus().equalsIgnoreCase("1")){//发送成功
//			paramMap.put("successCount", 1);
//			paramMap.put("failCount", 0);
//		}else{
//			paramMap.put("successCount", 0);
//			paramMap.put("failCount", 1);
//		}
		
		int count = emailAnalyzeManager.updateEmailStatistics(paramMap);
		return count;
	}
	
	private EmailChnCache chooseHighLevelChn(String[] chnCodeArray){
		
		List<EmailChnCache> chnList = new ArrayList<EmailChnCache>();
		
		int level = -1;
		int index = 0;
		
		for (int i = 0; i < chnCodeArray.length; i++) {
			EmailChnCache ecc = channelUtil.getEmailChannel(chnCodeArray[i]);
			
			chnList.add(ecc);
			
			if(ecc == null){
				continue;
			}
			
			if(level < 0 || level > ecc.getPriority()){
				level = ecc.getPriority();
				index = i;
			}
			
		}
		
		if(chnList.size() > 0){
			return chnList.get(index);
		}
		
		return null;
	}

	public void setTemplateUtil(TemplateUtil templateUtil) {
		this.templateUtil = templateUtil;
	}

	public void setMerchantUtil(MerchantUtil merchantUtil) {
		this.merchantUtil = merchantUtil;
	}

	public void setChannelUtil(ChannelUtil channelUtil) {
		this.channelUtil = channelUtil;
	}

	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
}
