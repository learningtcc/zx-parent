package com.ink.asile.core.dao.impl;

import com.ink.asile.core.dao.IAsileDayTimeDao;
import com.ink.asile.core.po.AsileDayTime;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

public class AsileDayTimeDaoImpl extends BaseIbatisDao<AsileDayTime, Long>
		implements IAsileDayTimeDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileDayTime";
	}

	@Override
	protected void prepareObjectForSave(AsileDayTime entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
}
