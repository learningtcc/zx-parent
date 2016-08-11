package com.ink.basic.core.dao.impl;


import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.basic.core.dao.IBasicResCodeDao;
import com.ink.basic.core.po.BasicResCode;

@Repository
public class BasicResCodeDaoImpl extends BaseIbatisDao<BasicResCode, Long> implements IBasicResCodeDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return "BasicResCode";
    }
    @Override
    public int updateNotNull(BasicResCode basicResCode){
		return getSqlSession().update("BasicResCode.updateNotNull",basicResCode);
	}
}
