/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.base.IBaseManager;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsChannelManager extends IBaseManager<SmsChannel, Long>{

 /**
  *更新状态
  * @param id
  * @param status
  * @return
  * @throws Exception
  */
 int updateStatus(SmsChannel smsChannel) throws Exception;

 /**
  *获取最大code 及 优先级
  * @return
  */
 Map<String,Long> getMaxSmsChannelCode();

 /**
  * 获取邮件通道信息
  * @return
  */
 List findSmsChannelTree()throws Exception;

 /**
  * 保存优先级调整
  * @param smsChannel
  * @param channelLevel
  */
 int saveLevelOrder(SmsChannel smsChannel, String channelLevel)throws Exception;

 /**
  * @Description: 查询短信通道列表
  * @param record
  * @return List<SmsChannel>
  * @throws
  */
 public List<SmsChannel> findSmsChannels(SmsChannel record);
}
