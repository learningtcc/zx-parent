package com.ink.user.ext.core.batch.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.ext.api.constant.UserLogConstant;
import com.ink.user.ext.api.constant.UserMessageConstant;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.log.util.MDCLog;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.SmsService;
import com.ink.user.ext.core.batch.item.UserMsgItem;
import com.ink.user.ext.core.dao.IBatchLogDao;
import com.ink.user.ext.core.po.BatchLog;

/**
 * 用户批量发送短信的writer
 * @author yangchen
 * @date 2016年6月22日 下午2:05:38
 */
public class UserMsgJobWriter implements ItemWriter<UserMsgItem>{
	
	private YinkerLogger logger = YinkerLogger.getLogger(UserMsgJobWriter.class);
	@Autowired
	private CustomizedPropertyConfigurer customizedPropertyConfigurer;
	@Autowired
	private SmsService smsService;
	@Autowired
	private IBatchLogDao batchLogDao;
	
	private String sendType;
	
	private Date sendTime;
	
	private String mchId;
	
	private Long fileId;
	
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public void write(List<? extends UserMsgItem> items) throws Exception {
		try{
			//清楚线程变量信息
			MDCLog.setThreadPoolLog();
			// 根据短信通道和短信模板分组
			Map<String, List<String>> groups = new HashMap<String, List<String>>();
			for(UserMsgItem item : items){
				String key = item.getMsgChannel() + "##" + item.getMsgTemplate();
				if(!groups.containsKey(key)){
					groups.put(key, new ArrayList<String>());
				}
				groups.get(key).add(item.getPhone());
			}
			// 调消息中心
			for(String key : groups.keySet()){
				String[] metaData = key.split("##");
				String msgChannel = metaData[0];
				String msgTemplate = metaData[1];
				// 封装调消息中心的参数
				SmsMassInput smsMassInput = initSmsMassInput(msgChannel,msgTemplate,groups.get(key));
				MsgOutput output = smsService.sendMassSms(smsMassInput);
				logger.info(output.toString());
				// 插入入batchInfo日志
				saveBatchInfo(smsMassInput);
			}
		}catch(Exception e){
			logger.error(UserLogConstant.ModuleCode ,"群发短信写入模块出错", e);
			throw e;
		}
	}
	
	private void saveBatchInfo(SmsMassInput smsMassInput) {
		BatchLog log = new BatchLog();
		log.setBatchId(smsMassInput.getBizId());
		Date now = new Date();
		log.setCreateTime(now);
		log.setId(Long.valueOf(idCodeGenerator.getId()));
		log.setFileId(fileId);
		log.setItemCount(smsMassInput.getMobileList().size());
		if(null == sendTime){
			log.setSendTime(now);
		}else{
			log.setSendTime(sendTime);
		}
		log.setSendType(sendType);
		log.setUpdateTime(now);
		log.setMsgChannel(smsMassInput.getChnCode());//短信通道
		log.setMsgTemplate(smsMassInput.getTempId());//短信模板
		log.setDelFlag(0);
		batchLogDao.save(log);		
		logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode,"向消息中心发送了一个批次 ： " + log);
	}

	private SmsMassInput initSmsMassInput(String msgChannel, String msgTemplate, List<String> list) {
		SmsMassInput input = new SmsMassInput();
		input.setBizId(idCodeGenerator.getId());
		input.setMerctCode(mchId);
		if(Integer.valueOf(sendType) == UserMessageConstant.SendType_Delay)
			input.setSendTime(sendTime);
		input.setTempId(msgTemplate);
		input.setMobileList(list);
		String notiryUrl = customizedPropertyConfigurer.getProperty("usermsgreporturl")
				+ fileId;
		input.setReportUrl(notiryUrl);// 通知地址
		input.setChnCode(msgChannel);// 渠道
		logger.info(UserLogConstant.ModuleCode, "SmsMassInput.ReportUrl : " + input.getReportUrl());
		return input;
	}
	
	public String getSendType() {
		return sendType;
	}
	
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	public String getMchId() {
		return mchId;
	}
	
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	
}
