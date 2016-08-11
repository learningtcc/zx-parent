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
import com.ink.msgcenter.core.dao.IEmailChannelDao;
import com.ink.msgcenter.core.dao.IMerchantChnDao;
import com.ink.msgcenter.core.dao.IMerchantInfoDao;
import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.service.IEmailChannelManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("emailChannelManager")
@Transactional
public class EmailChannelManagerImpl extends BaseManager<EmailChannel,Long> implements IEmailChannelManager{

	@Autowired
	private IEmailChannelDao emailChannelDao;
	@Autowired
	private IMerchantChnDao merchantChnDao;
	@Autowired
	private IMerchantInfoDao merchantInfoDao;
	@Autowired
	private Yedis yedis ;

	public EntityDao<EmailChannel, Long> getEntityDao() {
		return this.emailChannelDao;
	}

	/**
	 * 更新状态
	 * @return 更新行数
	 * @throws Exception
	 */
	@Override
	public int updateStatus(EmailChannel emailChannel) throws Exception {

		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setChnId(emailChannel.getId());
		merchantChn.setChnType("2");
		int num = 0;
		if ("2".equals(emailChannel.getStatus())){//对优先级重新排序
			emailChannel.setPriorityLevel(-1);
			num = emailChannelDao.updateInfo(emailChannel);
			emailChannelDao.resetUpdatePriorityLevel();

			merchantChnDao.deleteInfo(merchantChn);
		}else{
			num = emailChannelDao.updateInfo(emailChannel);
			//对商户通道中间表操作
			List<MerchantInfo> merchantList = merchantInfoDao.findMerchantForChannel(merchantChn);
			for(MerchantInfo merchantInfo : merchantList){
				String status = merchantInfo.getStatus();
				if ("1".equals(emailChannel.getStatus()) && "1".equals(status)){//都停用
					merchantChn.setStatus("2");
				}else if ( "1".equals(emailChannel.getStatus()) && "0".equals(status)){//通道停用 商户正常
					merchantChn.setStatus("2");
				}else if ( "0".equals(emailChannel.getStatus()) && "0".equals(status)){//通道正常 商户正常
					merchantChn.setStatus("0");
				}else if ( "0".equals(emailChannel.getStatus()) && "1".equals(status)){
					merchantChn.setStatus("1");
				}
				merchantChn.setMerctId(merchantInfo.getId());
				merchantChnDao.updateInfo(merchantChn);
			}
		}
		return num;
	}

	/**
	 * 获取最大邮件通道CODE
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,Long> getMaxEmailChannelCode() throws Exception {
		Map<String, Long> maxCodeMap = emailChannelDao.getMaxEmailChannelCode();
		if (null == maxCodeMap || maxCodeMap.isEmpty()){
			maxCodeMap = new HashMap<String,Long>();
			maxCodeMap.put("chnCode", 101l);
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
	public List findEmailChannelTree() throws Exception{
		return emailChannelDao.findEmailChannelTree();
	}

	/**
	 * 保存优先级调整
	 *
	 * @param emailChannel
	 * @param channelLevel
	 */
	@Override
	public int saveLevelOrder(EmailChannel emailChannel, String channelLevel) throws Exception{
		if (StringUtils.isBlank(channelLevel)){
			return 0;
		}
		channelLevel = channelLevel.substring(1);
		String[] channelLevels = channelLevel.split(";");
		for (int i = 0; i < channelLevels.length; i++) {
			String[] emailChannelInfos = channelLevels[i].split(",");
			emailChannel.setId(Long.valueOf(emailChannelInfos[0]));
			emailChannel.setPriorityLevel(Integer.valueOf(emailChannelInfos[1]));
			emailChannelDao.updateInfo(emailChannel);

			EmailChannel emailChannel1 = emailChannelDao.getById(Long.valueOf(emailChannelInfos[0]));
			yedis.deleteObject(CacheConstant.MSG_CHANNEL,emailChannel1.getChnCode());
		}
		return channelLevels.length;
	}

}
