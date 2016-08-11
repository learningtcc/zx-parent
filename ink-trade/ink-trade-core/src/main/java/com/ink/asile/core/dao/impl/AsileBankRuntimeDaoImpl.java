package com.ink.asile.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.asile.core.dao.IAsileBankRuntimeDao;
import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

@Repository("asileBankRuntimeDao")
public class AsileBankRuntimeDaoImpl extends
		BaseIbatisDao<AsileBankRuntime, Long> implements IAsileBankRuntimeDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBankRuntime";
	}

	@Override
	protected void prepareObjectForSave(AsileBankRuntime entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public List<AsileBankRuntime> findAsileBankRuntimes(AsileBankRuntime record) {
		return getSqlSessionSlave().selectList(
				"AsileBankRuntime.findAsileBankRuntimes", record);
	}

	@Override
	public List<AsileBankRuntime> getNotAvailableChannel(AsileBankRuntime queryParam) {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getNotAvailableChannel"),queryParam);
	}

	@Override
	public List<AsileBankRuntime> getAll() {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getAll"));
	}
	@Override
	public int updateNotNull(AsileBankRuntime asileBankRuntime){
		return getSqlSession().update("AsileBankRuntime.updateNotNull",asileBankRuntime);
	}
}
