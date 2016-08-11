package com.ink.user.core.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.dao.IAccMchProofDao;
import com.ink.user.core.po.AccMchProof;

@Repository("accMchProofDao")
public class AccMchProofDaoImpl extends BaseIbatisDao<AccMchProof, Integer>
		implements IAccMchProofDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccMchProof";
	}

	@Override
	protected void prepareObjectForSave(AccMchProof entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr());
		}
	}

	@Override
	public List<AccMchProof> findAccMchProofs(AccMchProof record) {

		return getSqlSessionSlave().selectList(
				getIbatisSqlMapNamespace()
						+ ".findAccMchProofs",record);
	}

	@Override
	public BigDecimal getAmtByMchIdAndSacType(AccMchProof record) {
		return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getAmtByMechIdAndSacType",record);
	}
	
	@Override
	public BigDecimal getAmtByMchIdAndSacTypeAndTime(AccMchProof record, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("mchId", record.getMchId());
		map.put("sacType", record.getSacType());
		logger.info("getAmtByMchIdAndSacTypeAndTime, startDate : " + startDate + ", endDate : " + endDate);
		return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getAmtByMechIdAndSacTypeAndTime",map);
	}

}
