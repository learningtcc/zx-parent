package com.ink.route.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.route.api.model.po.AsileBank;
import com.ink.route.dao.IAsileBankDao;

@Repository("asileBankDao")
public class AsileBankDaoImpl extends BaseIbatisDao<AsileBank, Long> implements
		IAsileBankDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBank";
	}

	@Override
	protected void prepareObjectForSave(AsileBank entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public List<AsileBank> findBankByLimitAndCodeAndTime(
			AsileBank record, Date tradeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asileCrashLimit", record.getAsileCrashLimit());
		map.put("bankCode", record.getBankCode());
		if (tradeDate == null) {
			tradeDate = new Date();
		}
		map.put("tradeDate", tradeDate);
		return getSqlSessionSlave().selectList(
				"AsileBank.findBankByLimitAndCodeAndTime", map);
	}

	@Override
	public AsileBank findAsileBankByAsileCode(String asileCode, String bankCode,String payType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asileCode", asileCode);
		map.put("bankCode", bankCode);
		map.put("asilePayType", payType);
		return getSqlSessionSlave().selectOne("AsileBank.findAsileBankByAsileCode",
				map);
	}

	@Override
	public long findAsileBankPriorityMaxSum(String bankCode) {
		return getSqlSessionSlave().selectOne(
				"AsileBank.findAsileBankPriorityMaxSum", bankCode);
	}
	
	@Override
	public int update(AsileBank record) {
		return getSqlSession().update("AsileBank.update", record);
	}
	
	@Override
	public int updateNotNull(AsileBank record) {
		return getSqlSession().update("AsileBank.updateNotNull", record);
	}

	@Override
	public List<AsileBank> getAsileBankBybankShort(String bankShort) {
		return getSqlSessionSlave().selectList("AsileBank.getAsileBankBybankShort", bankShort);
	}

	@Override
	public List<AsileBank> getAsileBankByBankShortAndAmtLimit(AsileBank asileBank) {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getAsileBankByBankShortAndAmtLimit"),asileBank);
	}

	@Override
	public List<AsileBank> getAll() {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getAll"));
	}


}
