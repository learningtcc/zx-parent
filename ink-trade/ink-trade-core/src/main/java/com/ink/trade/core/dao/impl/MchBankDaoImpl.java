/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IMchBankDao;
import com.ink.trade.core.po.MchBank;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("mchBankDao")
public class MchBankDaoImpl extends BaseIbatisDao<MchBank,java.lang.Long> implements IMchBankDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MchBank";
	}
	
	@Override
	protected void prepareObjectForSave(MchBank entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public MchBank getByMchIdBankShort(String mchId, String bankShort) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mchId", mchId);
        param.put("bankShort", bankShort);
		MchBank mchbank=(MchBank)this.getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace().concat(".getByMchIdBankShort"), param);
		return mchbank;
	}
	@Override
	public int updateNotNull(MchBank mchBank){
		return getSqlSession().update("MchBank.updateNotNull",mchBank);
	}
}
