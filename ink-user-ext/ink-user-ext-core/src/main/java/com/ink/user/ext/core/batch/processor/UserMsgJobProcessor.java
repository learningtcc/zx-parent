package com.ink.user.ext.core.batch.processor;

import java.util.Date;

import com.ink.user.ext.api.constant.UserLogConstant;
import com.ink.user.ext.api.constant.UserMessageConstant;
import com.ink.user.ext.core.dao.IUserInfoDao;
import com.ink.user.ext.core.po.UserInfo;
import com.ink.user.ext.core.po.UserMessageLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.log.util.MDCLog;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.ext.core.batch.item.UserMsgItem;
import com.ink.user.ext.core.dao.IUserMessageLogDao;

/**
 * 条目处理
 * @author yangchen
 * @date 2016年6月22日 下午2:04:02
 */
public class UserMsgJobProcessor implements ItemProcessor<UserMsgItem,UserMsgItem> {
	private YinkerLogger logger = YinkerLogger.getLogger(UserMsgJobProcessor.class);

	@Autowired
	private IUserInfoDao userInfoDao;
	@Autowired
	private IUserMessageLogDao userMessageLogDao;
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	
	private Long fileId;
	
	@Override
	public UserMsgItem process(UserMsgItem item) throws Exception {
		try{
			//清楚线程变量信息
			MDCLog.setThreadPoolLog();
			UserInfo entity = new UserInfo();
			entity.setCustId(item.getCustId());
			// 查找客户信息是否已经存在
			UserInfo userInfo = userInfoDao.getUserInfoByCustIdMchId(item.getCustId(), item.getMchId());
			// 如果不存在插入userInfo表
			if(userInfo == null){
				
				insertUserInfo(item);
			}else{// 如果已经存在，更新当前用户信息为最新
				updateUserInfo(item, userInfo);
			}
			// 插入发送日志
			insertUserMessageLog(item);
			return item;
		}catch(Exception e){
			logger.error(UserLogConstant.ModuleCode, "群发短信处理模块，处理条目失败", e);
			throw e;
		}
	}
	
	/**
	 * 插入发送日志表
	 * @param item
	 */
	private void insertUserMessageLog(UserMsgItem item) {
		UserMessageLog log = new UserMessageLog();
		Date now = new Date();
		log.setCreateTime(now);
		log.setEventInfo(item.getEventInfo());
		log.setFileId(fileId);
		log.setId(Long.valueOf(idCodeGenerator.getId()));
		log.setMchId(item.getMchId());
		log.setMsgChannel(item.getMsgChannel());
		log.setMsgTemplate(item.getMsgTemplate());
		log.setName(item.getName());
		log.setPhone(item.getPhone());
		log.setUpdateTime(now);
		log.setUserId(item.getCustId());
		log.setStatus(UserMessageConstant.USERMESSAGELOG_STATUS_SENT);
		log.setDelFlag(0);
		userMessageLogDao.save(log);
		logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode, "群发短信处理模块，插入发送日志 ： " + log);
	}

	/**
	 * 更新userInfo
	 * @param item
	 * @param userInfo 
	 */
	private void updateUserInfo(UserMsgItem item, UserInfo userInfo) {
		userInfo.setAddress(item.getAddress());
		userInfo.setCustId(item.getCustId());
		userInfo.setEmail(item.getEmail());
		userInfo.setIdNo(item.getIdNo());
		userInfo.setIdType(item.getIdType());
		userInfo.setMchId(item.getMchId());
		userInfo.setName(item.getName());
		userInfo.setNickName(item.getNickName());
		userInfo.setPhone(item.getPhone());
		userInfo.setSex(item.getSex());
		userInfo.setUpdateTime(new Date());
		userInfo.setZipcode(item.getZipcode());
		userInfoDao.update(userInfo);
		logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode,"群发短信处理模块，更新用户信息 ： " + userInfo);
	}

	/**
	 * 插入userInfo
	 * @param item
	 */
	private void insertUserInfo(UserMsgItem item) {
		UserInfo userInfo = new UserInfo();
		Date now = new Date();
		userInfo.setAddress(item.getAddress());
		userInfo.setCreateTime(now);
		userInfo.setCustId(item.getCustId());
		userInfo.setEmail(item.getEmail());
		userInfo.setId(Long.valueOf(idCodeGenerator.getId()));
		userInfo.setIdNo(item.getIdNo());
		userInfo.setIdType(item.getIdType());
		userInfo.setMchId(item.getMchId());
		userInfo.setName(item.getName());
		userInfo.setNickName(item.getNickName());
		userInfo.setPhone(item.getPhone());
		userInfo.setSex(item.getSex());
		userInfo.setUpdateTime(now);
		userInfo.setZipcode(item.getZipcode());
		userInfo.setStatus(String.valueOf(UserMessageConstant.USERINFO_STATUS_NORMAL));
		userInfoDao.save(userInfo);
		logger.info(UserLogConstant.ModuleCode, UserLogConstant.SendMassMsg_BusCode,"群发短信处理模块，插入新用户 ： " + userInfo);
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	
	

}
