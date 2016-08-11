/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import com.ink.msgcenter.core.dao.IMerchantChnDao;
import com.ink.msgcenter.core.po.MerchantChn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.service.IMerchantInfoManager;
import com.ink.msgcenter.core.dao.IMerchantInfoDao;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("merchantInfoManager")
@Transactional
public class MerchantInfoManagerImpl extends BaseManager<MerchantInfo,Long> implements IMerchantInfoManager{

	@Autowired
	private IMerchantInfoDao merchantInfoDao;
	@Autowired
	private IMerchantChnDao merchantChnDao;

	public EntityDao<MerchantInfo, Long> getEntityDao() {
		return this.merchantInfoDao;
	}

	@Override
	public int updateStatus(MerchantInfo merchantInfo) {

		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setMerctId(merchantInfo.getId());
		if ("2".equals(merchantInfo.getStatus())){//删除
			merchantChnDao.deleteInfo(merchantChn);
		}else {
			//对商户通道中间表操作
			List<Map<String,Object>> channelList = merchantChnDao.findChannelForMerchant(merchantChn);
			for (Map<String,Object> channelMap : channelList){
				String status = (String) channelMap.get("status");
				if ("1".equals(merchantInfo.getStatus()) && "1".equals(status)){//都停用
					merchantChn.setStatus("2");
				}else if ( "1".equals(merchantInfo.getStatus()) && "0".equals(status)){//商户停用 通道正常
					merchantChn.setStatus("1");
				}else if ( "0".equals(merchantInfo.getStatus()) && "1".equals(status)){//商户正常 通道停用
					merchantChn.setStatus("2");
				}else if ( "0".equals(merchantInfo.getStatus()) && "0".equals(status)){//都正常
					merchantChn.setStatus("0");
				}
				merchantChn.setChnId((Long) channelMap.get("id"));
				merchantChn.setChnType((String) channelMap.get("channelType"));
				merchantChnDao.updateInfo(merchantChn);
			}
		}

		return merchantInfoDao.updateInfo(merchantInfo);
	}
}
