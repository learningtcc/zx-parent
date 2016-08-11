package com.ink.user.core.dao.impl;

import com.ink.user.core.po.AccCardProof;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.dao.IAccCardProofDao;

@Repository("accCardProofDao")
public class AccCardProofDaoImpl extends BaseIbatisDao<AccCardProof,Integer> implements IAccCardProofDao{

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccCardProof";
	}
	
	@Override
	protected void prepareObjectForSave(AccCardProof entity) {
        if( entity.getId()==null) {
            entity.setId(IdGenerator.genUUIDStr().longValue());
        }
	}
	
	@Override
	public void insertAccCardProof(AccCardProof accCard, String txnCode) {
		getSqlSession().insert("AccCardProof.insert",accCard);
	}

	@Override
	public int insert(AccCardProof record) {
		return getSqlSession().insert("AccCardProof.insert",record);
	}

	@Override
	public int insertSelective(AccCardProof record) {
		return getSqlSession().insert("AccCardProof.insertSelective",record);
	}
}
