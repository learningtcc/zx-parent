/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.route.api.model.po.BankcardBin;
import com.ink.route.dao.IBankcardBinDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("bankcardBinDao")
public class BankcardBinDaoImpl extends BaseIbatisDao<BankcardBin,Long> implements IBankcardBinDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "BankcardBin";
	}
	
	@Override
	protected void prepareObjectForSave(BankcardBin entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	public BankcardBin getByCardBin(String bankNo) {
		return (BankcardBin)getSqlSessionSlave().selectOne("BankcardBin.getByCardBin",bankNo);
	}	
	public BankcardBin getByCardBinByCardBinLen(BankcardBin entity) {
		try {
			return (BankcardBin)getSqlSessionSlave().selectOne("BankcardBin.getByCardBinByCardBinLen",entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	@Override
	public int updateNotNull(BankcardBin bankcardBin){
		return getSqlSession().update("BankcardBin.updateNotNull",bankcardBin);
	}
}
