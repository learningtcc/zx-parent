/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service;

import com.ink.user.core.po.ReqLog;
import org.springframework.dao.DataAccessException;

import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IReqLogManager extends IBaseManager<ReqLog, java.lang.Long>{

	ReqLog checkReqLog(String ordId, String txnCode, String mchId);
	
	public int insert(ReqLog reqLog) throws DataAccessException;
	
}
