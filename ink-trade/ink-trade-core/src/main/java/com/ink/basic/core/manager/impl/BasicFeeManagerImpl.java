package com.ink.basic.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.basic.core.manager.IBasicFeeManager;
import com.ink.basic.core.po.BasicFee;

public class BasicFeeManagerImpl extends BaseManager<BasicFee, Long> implements IBasicFeeManager{

    @Override
    protected EntityDao<BasicFee, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
