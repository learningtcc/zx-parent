/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import com.ink.msgcenter.core.po.MerchantChn;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IMerchantInfoDao;
import com.ink.msgcenter.core.po.MerchantInfo;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("merchantInfoDao")
public class MerchantInfoDaoImpl extends BaseIbatisDao<MerchantInfo,Long> implements IMerchantInfoDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MerchantInfo";
	}
	
	@Override
	protected void prepareObjectForSave(MerchantInfo entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateInfo(MerchantInfo merchantInfo) {
		return this.getSqlSession().update(getIbatisSqlMapNamespace()+".updateInfo",merchantInfo);
	}

	@Override
	public List<MerchantInfo> findMerchantForChannel(MerchantChn merchantChn) {
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace() + ".findMerchantForChannel", merchantChn);
	}
}
