package com.ink.trade.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IRefundDao;
import com.ink.trade.core.po.Refund;

@Repository("refundDao")
public class RefundDaoImpl extends BaseIbatisDao<Refund, Long> implements
		IRefundDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "Refund";
	}

	@Override
	protected void prepareObjectForSave(Refund entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

}
