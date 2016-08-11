package com.ink.basic.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.basic.core.manager.IBasicCardBinManager;
import com.ink.basic.core.po.BasicCardBin;

public class BasicCardBinManagerImpl extends  BaseManager<BasicCardBin, Long> implements IBasicCardBinManager{

    @Override
    protected EntityDao<BasicCardBin, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
