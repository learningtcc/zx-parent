/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.ink.user.core.po.ReqLog;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.dao.IReqLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Repository("reqLogDao")
public class ReqLogDaoImpl extends BaseIbatisDao<ReqLog, java.lang.Long> implements IReqLogDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "ReqLog";
	}

	@Override
	protected void prepareObjectForSave(ReqLog entity) {
		// if(entity.getId()) == null) {
		// entity.setId(IdGenerator.genUUIDStr().longValue());
		// }
	}

	@Override
	public ReqLog checkReqLog(String ordId, String txnCode, String mchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordId", ordId);
		map.put("txnCode", txnCode);
		map.put("mchId", mchId);
		return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace() + ".checkReqLog", map);
	}

}
