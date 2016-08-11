/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.dao.IMerchantChnDao;
import com.ink.msgcenter.core.dao.impl.DynamicTableMetadataDaoImpl;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.service.IMerchantChnManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("merchantChnManager")
@Transactional
public class MerchantChnManagerImpl extends BaseManager<MerchantChn,Long> implements IMerchantChnManager{

	@Autowired
	private IMerchantChnDao merchantChnDao;
	@Autowired
	private DynamicTableMetadataDaoImpl dynamicTableMetadataDao;

	public EntityDao<MerchantChn, Long> getEntityDao() {
		return this.merchantChnDao;
	}

	/**
	 * 选择邮件通道，当商户已选择通道时，checked=1
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List selectEmailChannelList(Long id) {
		return merchantChnDao.selectEmailChannelList(id);
	}

	/**
	 * 选择短信通道，当商户已选择通道时，checked=1
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List selectSmsChannelList(Long id) {
		return merchantChnDao.selectSmsChannelList(id);
	}

	/**
	 * 创建商户邮件通道日志表
	 *1、检查表当前商户日志表是否存在
	 *2、如存在则不做操作，反之创建
	 * @param merchantCode 商户编号
	 */
	@Override
	public void createTableForEmailMerchantLog(String merchantCode) {

		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("merchantCode",merchantCode);
		try {
			dynamicTableMetadataDao.createTableForEmailMerchantLog(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建商户短信通道日志表
	 *1、检查表当前商户日志表是否存在
	 *2、如存在则不做操作，反之创建
	 * @param merchantCode 商户编号
	 */
	@Override
	public void createTableForSmsMerchantLog(String merchantCode) {
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("merchantCode",merchantCode);
		try {
			dynamicTableMetadataDao.createTableForSmsMerchantLog(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据供应商删除通道
	 *
	 * @param merchantCode
	 * @param channelType
	 */
	@Override
	public int deleteForMerchantCode(String merchantCode, String channelType) {
		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setMerctCode(merchantCode);
		merchantChn.setChnType(channelType);
		return merchantChnDao.deleteInfo(merchantChn);
	}

	@Override
	public List<MerchantChn> findChannels(MerchantChn mc) {
		return merchantChnDao.findChannels(mc);
	}

	/**
	 * 获取商户选择邮件通道
	 *
	 * @param merchId
	 * @return
	 */
	@Override
	public List findEmailChannelsByMerchId(Long merchId) {
		return merchantChnDao.findEmailChannelsByMerchId(merchId);
	}

	/**
	 * 获取商户选择短信通道
	 *
	 * @param merchId
	 * @return
	 */
	@Override
	public List findSmsChannelsByMerchId(Long merchId) {
		return merchantChnDao.findSmsChannelsByMerchId(merchId);
	}
}
