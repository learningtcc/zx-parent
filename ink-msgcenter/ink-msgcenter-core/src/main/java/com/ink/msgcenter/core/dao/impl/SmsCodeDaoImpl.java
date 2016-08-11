/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsCodeDao;
import com.ink.msgcenter.core.po.SmsCode;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsCodeDao")
public class SmsCodeDaoImpl extends BaseIbatisDao<SmsCode,java.lang.Long> implements ISmsCodeDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsCode";
	}
	
	@Override
	protected void prepareObjectForSave(SmsCode entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public SmsCode getSmsCode(SmsCode record) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getSmsCode",record);
	}

	@Override
	public int deleteInvalidData() {
		return getSqlSession().delete(getIbatisSqlMapNamespace() + ".deleteInvalidData");
	}

}
