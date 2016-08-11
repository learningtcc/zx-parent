/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.base.IBaseManager;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMerchantChnManager extends IBaseManager<MerchantChn, Long>{

 /**
  * 选择邮件通道，当商户已选择通道时，checked=1
  * @param id
  * @return
  */
 List selectEmailChannelList(Long id);
 /**
  * 选择短信通道，当商户已选择通道时，checked=1
  * @param id
  * @return
  */
 List selectSmsChannelList(Long id);

 /**
  * 创建商户邮件通道日志表
  * @param merchantCode 商户编号
  */
 void createTableForEmailMerchantLog(String merchantCode);

 /**
  * 创建商户短信通道日志表
  * @param merchantCode 商户编号
  */
 void createTableForSmsMerchantLog(String merchantCode);

 /**
  * 根据供应商删除通道
  * @param merchantCode
  * @param channelType
  */
 int deleteForMerchantCode(String merchantCode, String channelType);
 
 /**
  * @Description: 根据条件查询列表
  * @param chn
  * @return
  * @throws
  */
 List<MerchantChn> findChannels(MerchantChn mc);

 /**
  *获取商户选择邮件通道
  * @param merchId
  * @return
  */
 List findEmailChannelsByMerchId(Long merchId);

 /**
  * 获取商户选择短信通道
  * @param merchId
  * @return
  */
 List findSmsChannelsByMerchId(Long merchId);
}
