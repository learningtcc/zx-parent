package com.ink.trade.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IRefundLogDao;
import com.ink.trade.core.po.RefundLog;

@Repository("refundLogDao")
public class RefundLogDaoImpl  extends BaseIbatisDao<RefundLog, Long> implements IRefundLogDao{

	@Override
	public String getIbatisSqlMapNamespace() {
		return "RefundLog";
	}

	@Override
	protected void prepareObjectForSave(RefundLog entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
}
