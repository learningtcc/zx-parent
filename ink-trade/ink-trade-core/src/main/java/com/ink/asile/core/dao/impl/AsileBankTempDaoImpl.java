package com.ink.asile.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.asile.core.dao.IAsileBankTempDao;
import com.ink.asile.core.po.AsileBankTemp;
import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

@Repository("asileBankTempDao")
public class AsileBankTempDaoImpl extends BaseIbatisDao<AsileBankTemp, Long>
		implements IAsileBankTempDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBankTemp";
	}

	@Override
	protected void prepareObjectForSave(AsileBankTemp entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public List<AsileBankTemp> findAsileBankTemps(AsileBankTemp record) {
		return getSqlSessionSlave().selectList("AsileBankTemp.findAsileBankTemps",
				record);
	}

//	@Override
//	public List<AsileBankTemp> findBankByLimitAndCodeAndTime(AsileBankTemp record,
//			Date tradeDate) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("asileCrashLimit", record.getAsileCrashLimit());
//		map.put("bankCode", record.getBankCode());
//		if (tradeDate == null) {
//			tradeDate = new Date();
//		}
//		map.put("tradeDate", tradeDate);
//		return getSqlSessionSlave().selectList(
//				"AsileBankTemp.findBankByLimitAndCodeAndTime", map);
//	}

	@Override
	public List<AsileBankTemp> getAll() {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getAll"));
	}
	@Override
	public int updateNotNull(AsileBankTemp asileBankTemp){
		return getSqlSession().update("AsileBankTemp.updateNotNull",asileBankTemp);
	}
}
