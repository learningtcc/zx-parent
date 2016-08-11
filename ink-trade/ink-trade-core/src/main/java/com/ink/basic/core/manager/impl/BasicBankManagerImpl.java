package com.ink.basic.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.basic.core.dao.IBasicBankDao;
import com.ink.basic.core.manager.IBasicBankManager;
import com.ink.basic.core.po.BasicBank;
@Service("basicBankManager")
@Transactional
public class BasicBankManagerImpl extends BaseManager<BasicBank, Long> implements IBasicBankManager{
    @Autowired
    private IBasicBankDao basicBankDao;
    @Override
    protected EntityDao<BasicBank, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return this.basicBankDao;
    }
    @Override
    public int updateNotNull(BasicBank basicBank){
		return basicBankDao.updateNotNull(basicBank);
	}
}
