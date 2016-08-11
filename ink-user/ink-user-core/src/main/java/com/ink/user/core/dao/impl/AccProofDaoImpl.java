package com.ink.user.core.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.core.dao.IAccProofDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccProof;
@Repository("accProofDao")
public class AccProofDaoImpl extends BaseIbatisDao<AccProof, Long> implements
		IAccProofDao {
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccProof";
	}

	@Override
	protected void prepareObjectForSave(AccProof entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public void insertAccProof(AccAcc accAcc, String txnCode) {
		AccProof accProof = new AccProof();
		BeanCopier copier = BeanCopier.create(AccAcc.class, AccProof.class,
				false);
		copier.copy(accAcc, accProof, null);
		accProof.setTxnCode(txnCode);
		accProof.setBeforeBal(accAcc.getLstBal());
		accProof.setCurBal(accAcc.getCurBal());
		accProof.setModifyBal(accAcc.getTmpBal());
		accProof.setId(Long.valueOf(idCodeGenerator.getId()));
		accProof.setCreateTime(new Date());
		getSqlSession().insert("AccProof.insertSelective", accProof);
	}

	@Override
	public int insert(AccProof record) {
		return getSqlSession().insert("AccProof.insert",record);
	}

	@Override
	public int insertSelective(AccProof record) {
		return getSqlSession().insert("AccProof.insertSelective",record);
	}

}
