/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import java.util.Date;

import com.ink.user.core.po.ReqLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.core.dao.IReqLogDao;
import com.ink.user.core.service.IReqLogManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("reqLogManager")
@Transactional
public class ReqLogManagerImpl extends BaseManager<ReqLog,java.lang.Long> implements IReqLogManager{
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IReqLogDao reqLogDao;

	public EntityDao<ReqLog, java.lang.Long> getEntityDao() {
		return this.reqLogDao;
	}

	@Override
	public ReqLog checkReqLog(String ordId, String txnCode, String mchId) {
		return reqLogDao.checkReqLog(ordId, txnCode, mchId);
	}

	@Override
	public int insert(ReqLog reqLog) throws DataAccessException {
		reqLog.setId(Long.valueOf(idCodeGenerator.getId()));
		reqLog.setOrdTime(new Date());
		reqLog.setDelFlag(0);
		reqLog.setVersion(1);
		reqLog.setCreateTime(new Date());
		return reqLogDao.save(reqLog);
	}
	
}
