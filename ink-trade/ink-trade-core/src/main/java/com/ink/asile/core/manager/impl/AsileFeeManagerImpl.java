package com.ink.asile.core.manager.impl;

import com.ink.asile.core.manager.IAsileFeeManager;
import com.ink.asile.core.po.AsileFee;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;

public class AsileFeeManagerImpl extends BaseManager<AsileFee, Long> implements IAsileFeeManager {

    @Override
    protected EntityDao<AsileFee, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
