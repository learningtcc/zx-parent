package com.ink.asile.core.dao.impl;

import com.ink.asile.core.dao.IAsileAreaCodeDao;
import com.ink.asile.core.po.AsileAreaCode;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

public class AsileAreaCodeDaoImpl extends BaseIbatisDao<AsileAreaCode, Long>
		implements IAsileAreaCodeDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileAreaCode";
	}

	@Override
	protected void prepareObjectForSave(AsileAreaCode entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
}
