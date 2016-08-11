package com.ink.user.core.dao.impl;

import com.ink.user.core.dao.IAccCustProofDao;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.po.AccCustProof;
@Repository("accCustProofDao")
public class AccCustProofDaoImpl extends BaseIbatisDao<AccCustProof, Integer>
		implements IAccCustProofDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccCustProof";
	}

	@Override
	protected void prepareObjectForSave(AccCustProof entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
	
	public void insertAccCustProof(AccCustProof accCustProof, String txnCode) {
		getSqlSession().insert("AccCustProof.insert",accCustProof);
	}

	@Override
	public int insert(AccCustProof record) {
		return getSqlSession().insert("AccCustProof.insert",record);
	}

	@Override
	public int insertSelective(AccCustProof record) {
		return getSqlSession().insert("AccCustProof.insertSelective",record);
	}
}
