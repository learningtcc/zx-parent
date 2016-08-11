package com.ink.basic.core.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.basic.core.dao.IBasicResCodeDao;
import com.ink.basic.core.manager.IBasicResCodeManager;
import com.ink.basic.core.po.BasicResCode;
@Service
@Transactional
public class BasicResCodeManagerImpl extends BaseManager<BasicResCode, Long> implements IBasicResCodeManager {
    @Autowired
    private IBasicResCodeDao basicResCodeDao;
    @Override
    protected EntityDao<BasicResCode, Long> getEntityDao() {
        return this.basicResCodeDao;
    }
    @Override
    public int updateNotNull(BasicResCode basicResCode){
		return basicResCodeDao.updateNotNull(basicResCode);
	}
}
