package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IRefundDao;
import com.ink.trade.core.manager.IRefundManager;
import com.ink.trade.core.po.Refund;

@Service("refundManager")
@Transactional
public class RefundManagerImpl extends BaseManager<Refund, Long> implements
		IRefundManager {

	@Autowired
	private IRefundDao refundDao;

	@Override
	protected EntityDao<Refund, Long> getEntityDao() {
		return refundDao;
	}

}
