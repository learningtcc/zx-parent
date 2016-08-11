package com.ink.route.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.dao.IAsileInfoDao;

@Repository("asileInfoDao")
public class AsileInfoDaoImpl extends BaseIbatisDao<AsileInfo, Long> implements
		IAsileInfoDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileInfo";
	}

	@Override
	protected void prepareObjectForSave(AsileInfo entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}

	@Override
	public List<AsileInfo> findAsileInfos(AsileInfo record) {
		return getSqlSessionSlave().selectList("AsileInfo.findAsileInfos", record);
	}
	@Override
	public AsileInfo findAsileInfoByName(String name){
	    return getSqlSessionSlave().selectOne("AsileInfo.findAsileInfoByName",name);
	}
	@Override
	public int updateNotNull(AsileInfo asileInfo){
		return getSqlSession().update("AsileInfo.updateNotNull",asileInfo);
	}
}
