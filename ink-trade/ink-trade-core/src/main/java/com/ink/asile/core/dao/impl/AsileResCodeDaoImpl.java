package com.ink.asile.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.asile.core.dao.IAsileResCodeDao;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.dao.BaseIbatisDao;
@Repository
public class AsileResCodeDaoImpl extends BaseIbatisDao<AsileResCode, Long> implements IAsileResCodeDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "AsileResCode";
    }

	@Override
	public AsileResCode getChanPlatResCodeRel(String channelId, String resCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("channelId", channelId);
		param.put("resCode", resCode);
		Object obj = getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace().concat(".getChanPlatResCodeRel"), param);
		return (AsileResCode)obj;
	}
	@Override
	public int updateNotNull(AsileResCode asileResCode){
		return getSqlSession().update("AsileResCode.updateNotNull",asileResCode);
	}
}
