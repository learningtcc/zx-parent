package com.ink.user.core.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ink.user.core.dao.ITnsLogDao;
import com.ink.user.core.po.TnsLog;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

@Repository("tnsLogDao")
public class TnsLogDaoImpl extends BaseIbatisDao<TnsLog, Integer> implements
        ITnsLogDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "TnsLog";
	}

	@Override
	protected void prepareObjectForSave(TnsLog entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("TnsLog.deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(TnsLog record) {
		return getSqlSession().insert("TnsLog.insertSelective", record);
	}

	@Override
	public TnsLog selectByPrimaryKey(Long id) {
		return getSqlSessionSlave().selectOne("TnsLog.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnsLog record) {
		return getSqlSession().update("TnsLog.updateByPrimaryKeySelective",
				record);
	}

	@Override
	public int updateByPrimaryKey(TnsLog record) {
		return getSqlSession().update("TnsLog.updateByPrimaryKey", record);
	}

	@Override
	public TnsLog findTnsLogbyOriOrdId(String oriOrdId, Long mchId,
			Date oriOrdDate, String oriTxnCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oriOrdId", oriOrdId);
		map.put("mchId", mchId);
		map.put("oriOrdDate", oriOrdDate);
		map.put("oriTxnCode", oriTxnCode);
		return getSqlSessionSlave().selectOne("TnsLog.findTnsLogbyOriOrdId", map);
	}

	@Override
	public TnsLog findTnsLogbyOrdId(String ordId, Long mchId, Date ordDate,
			String txnCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordId", ordId);
		map.put("mchId", mchId);
		map.put("ordDate", ordDate);
		map.put("txnCode", txnCode);
		return getSqlSessionSlave().selectOne("TnsLog.findTnsLogbyOrdId", map);
	}

	@Override
	public TnsLog selectTnsLog(Long mchId, String ordId, Date ordDate,
			String id, String depositType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordId", ordId);
		map.put("mchId", mchId);
		map.put("ordDate", ordDate);
		map.put("depositType", depositType);
		return getSqlSessionSlave().selectOne("TnsLog.selectTnsLog", map);
	}

	@Override
	public TnsLog selectTnsLogByOrdId(String ordId, String txnCode, String mchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordId", ordId);
		map.put("mchId", mchId);
		map.put("txnCode", txnCode);
		return getSqlSessionSlave().selectOne("TnsLog.selectTnsLogByOrdId", map);
	}

	@Override
	public TnsLog findTnsLogByAccUnfrozen(String ordId, BigDecimal amt,
			BigDecimal custFee, Date ordDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ordId", ordId);
		map.put("amt", amt);
		map.put("custFee", custFee);
//		map.put("ordDate", ordDate);
		return getSqlSessionSlave().selectOne("TnsLog.findTnsLogByAccUnfrozen", map);
	}

	@Override
	public TnsLog getTnsLogbyId(String id) {
		return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getTnsLogbyId",id);
	}

}
