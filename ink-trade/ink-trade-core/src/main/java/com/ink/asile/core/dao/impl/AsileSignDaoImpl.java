package com.ink.asile.core.dao.impl;

import com.ink.asile.core.dao.IAsileSignDao;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.dao.BaseIbatisDao;

import org.springframework.stereotype.Repository;

@Repository
public class AsileSignDaoImpl extends BaseIbatisDao<AsileSign, Long> implements IAsileSignDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return "AsileSign";
    }

    @Override
    public AsileSign selectSignIdByChannel(AsileSign asileSign) {
        return this.getSqlSession().selectOne(getIbatisSqlMapNamespace().concat(".selectSignIdByChannel"),asileSign);
    }
    @Override
    public int updateNotNull(AsileSign asileSign){
		return getSqlSession().update("AsileSign.updateNotNull",asileSign);
	}
}
