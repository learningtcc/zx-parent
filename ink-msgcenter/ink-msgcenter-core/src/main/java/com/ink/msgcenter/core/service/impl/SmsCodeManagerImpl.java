/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsCode;
import com.ink.msgcenter.core.service.ISmsCodeManager;
import com.ink.msgcenter.core.dao.ISmsCodeDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsCodeManager")
@Transactional
public class SmsCodeManagerImpl extends BaseManager<SmsCode,java.lang.Long> implements ISmsCodeManager{

	@Autowired
	private ISmsCodeDao smsCodeDao;

	public EntityDao<SmsCode, java.lang.Long> getEntityDao() {
		return this.smsCodeDao;
	}

	@Override
	public SmsCode getSmsCode(SmsCode smsCode) {
		return smsCodeDao.getSmsCode(smsCode);
	}

	@Override
	public int deleteInvalidData() {
		return smsCodeDao.deleteInvalidData();
	}

}
