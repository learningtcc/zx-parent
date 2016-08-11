/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.redis.client.Yedis;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.core.dao.IMerchantChnDao;
import com.ink.msgcenter.core.dao.IMerchantInfoDao;
import com.ink.msgcenter.core.dao.ISmsChannelDao;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.service.ISmsChannelManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsChannelManager")
@Transactional
public class SmsChannelManagerImpl extends BaseManager<SmsChannel,Long> implements ISmsChannelManager{

	@Autowired
	private ISmsChannelDao smsChannelDao;
	@Autowired
	private IMerchantChnDao merchantChnDao;
	@Autowired
	private IMerchantInfoDao merchantInfoDao;
	@Autowired
	private Yedis yedis ;

	public EntityDao<SmsChannel, Long> getEntityDao() {
		return this.smsChannelDao;
	}

	/**
	 * 更新状态
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateStatus(SmsChannel smsChannel) throws Exception {

		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setChnId(smsChannel.getId());
		merchantChn.setChnType("1");
		int num = 0;
		if ("2".equals(smsChannel.getStatus())){//对优先级重新排序
			smsChannel.setPriorityLevel(-1);
			num = smsChannelDao.updateInfo(smsChannel);
			smsChannelDao.resetUpdatePriorityLevel();

			merchantChnDao.deleteInfo(merchantChn);
		}else{
			num = smsChannelDao.updateInfo(smsChannel);
			//对商户通道中间表操作
			List<MerchantInfo> merchantList = merchantInfoDao.findMerchantForChannel(merchantChn);
			for(MerchantInfo merchantInfo : merchantList){
				String status = merchantInfo.getStatus();
				if ("1".equals(smsChannel.getStatus()) && "1".equals(status)){//都停用
					merchantChn.setStatus("2");
				}else if ( "1".equals(smsChannel.getStatus()) && "0".equals(status)){//通道停用 商户正常
					merchantChn.setStatus("2");
				}else if ( "0".equals(smsChannel.getStatus()) && "0".equals(status)){//通道正常 商户正常
					merchantChn.setStatus("0");
				}else if ( "0".equals(smsChannel.getStatus()) && "1".equals(status)){
					merchantChn.setStatus("1");
				}
				merchantChn.setMerctId(merchantInfo.getId());
				merchantChnDao.updateInfo(merchantChn);
			}
		}
		return num;
	}

	/**
	 * @return
	 */
	@Override
	public Map<String, Long> getMaxSmsChannelCode() {
		Map<String, Long> maxCodeMap = smsChannelDao.getMaxSmsChannelCode();
		if (null == maxCodeMap || maxCodeMap.isEmpty()){
			maxCodeMap = new HashMap<String,Long>();
			maxCodeMap.put("chnCode", 501l);
			maxCodeMap.put("priorityLevel",1l);
		}else{
			long chnCode = maxCodeMap.get("chnCode");
			chnCode = chnCode + 1;

			long priorityLevel = maxCodeMap.get("priorityLevel");
			if (priorityLevel == -1)
				priorityLevel = priorityLevel + 1;
			priorityLevel = priorityLevel + 1;

			maxCodeMap.put("chnCode",chnCode);
			maxCodeMap.put("priorityLevel",priorityLevel);
		}

		return maxCodeMap;
	}

	/**
	 * 获取邮件通道信息
	 *
	 * @return
	 */
	@Override
	public List findSmsChannelTree() throws Exception {
		return smsChannelDao.findSmsChannelTree();
	}

	/**
	 * 保存优先级调整
	 *
	 * @param smsChannel
	 * @param channelLevel
	 */
	@Override
	public int saveLevelOrder(SmsChannel smsChannel, String channelLevel) throws Exception {
		if (StringUtils.isBlank(channelLevel)){
			return 0;
		}
		channelLevel = channelLevel.substring(1);
		String[] channelLevels = channelLevel.split(";");
		for (int i = 0; i < channelLevels.length; i++) {
			String[] smsChannelInfos = channelLevels[i].split(",");
			smsChannel.setId(Long.valueOf(smsChannelInfos[0]));
			smsChannel.setPriorityLevel(Integer.valueOf(smsChannelInfos[1]));
			smsChannelDao.updateInfo(smsChannel);

			SmsChannel smsChannel1 = smsChannelDao.getById(Long.valueOf(smsChannelInfos[0]));
			yedis.deleteObject(CacheConstant.MSG_CHANNEL,smsChannel1.getChnCode());
		}
		return channelLevels.length;
	}

	@Override
	public List<SmsChannel> findSmsChannels(SmsChannel record) {
		return smsChannelDao.findSmsChannels(record);
	}
}
