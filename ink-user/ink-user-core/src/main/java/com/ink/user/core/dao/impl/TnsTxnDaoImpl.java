package com.ink.user.core.dao.impl;

import java.util.List;

import com.ink.user.core.dao.ITnsTxnDao;
import com.ink.user.core.po.TnsTxn;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

@Repository("tnsTxnDao")
public class TnsTxnDaoImpl extends BaseIbatisDao<TnsTxn, Long> implements
        ITnsTxnDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "TnsTxn";
	}

	@Override
	protected void prepareObjectForSave(TnsTxn entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("TnsTxn.deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(TnsTxn record) {
		return getSqlSession().insert("TnsTxn.insertSelective", record);
	}

	@Override
	public TnsTxn selectByPrimaryKey(Long id) {
		return getSqlSessionSlave().selectOne("TnsTxn.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnsTxn record) {
		return getSqlSession().update("TnsTxn.updateByPrimaryKeySelective",
				record);
	}

	@Override
	public int updateByPrimaryKey(TnsTxn record) {
		return getSqlSession().update("TnsTxn.updateByPrimaryKey", record);
	}

	@Override
	public List<TnsTxn> selectAll() {
		return getSqlSessionSlave().selectList("TnsTxn.selectAll");
	}

	@Override
	public TnsTxn findByTxnCode(String txnCode) {
		return getSqlSessionSlave().selectOne("TnsTxn.findByTxnCode", txnCode);
	}

}
