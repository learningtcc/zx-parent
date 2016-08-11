/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.route.api.model.po.BasicBank;
import com.ink.route.dao.IBasicBankDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("basicBankDao")
public class BasicBankDaoImpl extends BaseIbatisDao<BasicBank,java.lang.Long> implements IBasicBankDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "BasicBank";
	}
	
	@Override
	protected void prepareObjectForSave(BasicBank entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}
	@Override
	public int updateNotNull(BasicBank basicBank){
		return getSqlSession().update("BasicBank.updateNotNull",basicBank);
	}
}
