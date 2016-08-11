package com.ink.user.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.core.po.TnsAuth;
import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.dao.ITnsAuthDao;

@Repository("tnsAuthDao")
public class TnsAuthDaoImpl extends BaseIbatisDao<TnsAuth, Integer> implements
		ITnsAuthDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TnsAuth";
	}

	@Override
	protected void prepareObjectForSave(TnsAuth entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("TnsAuth.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(TnsAuth record) {
		return getSqlSession().insert("TnsAuth.insertSelective",record);
	}

	@Override
	public TnsAuth selectByPrimaryKey(Long id) {
		return getSqlSessionSlave().selectOne("TnsAuth.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnsAuth record) {
		return getSqlSession().update("TnsAuth.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(TnsAuth record) {
		return getSqlSession().update("TnsAuth.updateByPrimaryKey",record);
	}

	@Override
	public TnsAuth selectByTnsLogId(Long tnsLogId) {
		return getSqlSessionSlave().selectOne("TnsAuth.selectByTnsLogId",tnsLogId);
	}

	@Override
	public int updateByTnsLogId(TnsAuth tnsAuth) {
		return getSqlSession().update("TnsAuth.updateByTnsLogId",tnsAuth);
	}

	@Override
	public List<TnsAuth> getUnfrozenTnsAuth(Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return getSqlSessionSlave().selectList("TnsAuth.getUnfrozenTnsAuth", map);
	}

}
