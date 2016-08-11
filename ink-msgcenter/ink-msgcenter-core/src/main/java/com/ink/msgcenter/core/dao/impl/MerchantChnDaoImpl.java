/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import com.ink.msgcenter.core.po.MerchantInfo;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IMerchantChnDao;
import com.ink.msgcenter.core.po.MerchantChn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("merchantChnDao")
public class MerchantChnDaoImpl extends BaseIbatisDao<MerchantChn,Long> implements IMerchantChnDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MerchantChn";
	}
	
	@Override
	protected void prepareObjectForSave(MerchantChn entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List selectEmailChannelList(Long id) {
		Map param=new HashMap();
		param.put("id",id);
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".selectEmailChannelList",param);
	}

	@Override
	public List selectSmsChannelList(Long id) {
		Map param=new HashMap();
		param.put("id",id);
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".selectSmsChannelList",param);
	}

	@Override
	public int deleteInfo(MerchantChn merchantChn) {
		return this.getSqlSession().delete(getIbatisSqlMapNamespace() + ".deleteInfo", merchantChn);
	}

	@Override
	public int updateInfo(MerchantChn merchantChn) {
		return this.getSqlSession().delete(getIbatisSqlMapNamespace() + ".updateInfo", merchantChn);
	}

	@Override
	public List<Map<String, Object>> findChannelForMerchant(MerchantChn merchantChn) {
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".findChannelForMerchant",merchantChn);
	}

	@Override
	public List<MerchantChn> findChannels(MerchantChn mc) {
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".findChannels",mc);
	}

	@Override
	public List findEmailChannelsByMerchId(Long merchId) {
		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setMerctId(merchId);
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".findEmailChannelsByMerchId",merchantChn);
	}

	@Override
	public List findSmsChannelsByMerchId(Long merchId) {
		MerchantChn merchantChn = new MerchantChn();
		merchantChn.setMerctId(merchId);
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace()+".findSmsChannelsByMerchId",merchantChn);
	}

}
