package com.ink.asile.core.dao.impl;

import com.ink.asile.core.dao.IAsileFeeDao;
import com.ink.asile.core.po.AsileFee;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

public class AsileFeeDaoImpl extends BaseIbatisDao<AsileFee, Long> implements
		IAsileFeeDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileFee";
	}

	@Override
	protected void prepareObjectForSave(AsileFee entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
}
