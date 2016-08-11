package com.ink.asile.core.dao.impl;

import com.ink.asile.core.dao.IAsileBankRuntimeLogDao;
import com.ink.asile.core.po.AsileBankRuntimeLog;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

public class AsileBankRuntimeLogDaoImpl extends
		BaseIbatisDao<AsileBankRuntimeLog, Long> implements
		IAsileBankRuntimeLogDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBankRuntime";
	}

	@Override
	protected void prepareObjectForSave(AsileBankRuntimeLog entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
}
