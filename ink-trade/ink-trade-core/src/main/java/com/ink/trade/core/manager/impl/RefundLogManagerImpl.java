package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IRefundLogDao;
import com.ink.trade.core.manager.IRefundLogManager;
import com.ink.trade.core.po.RefundLog;

@Service("refundLogManager")
@Transactional
public class RefundLogManagerImpl extends BaseManager<RefundLog, Long>
		implements IRefundLogManager {

	@Autowired
	private IRefundLogDao refundLogDao;

	@Override
	protected EntityDao<RefundLog, Long> getEntityDao() {
		return refundLogDao;
	}

}
