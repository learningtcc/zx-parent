/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao;

import com.ink.user.core.po.ReqLog;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IReqLogDao extends EntityDao<ReqLog, java.lang.Long>{

	ReqLog checkReqLog(String ordId, String txnCode, String mchId);

}