package com.ink.protocol.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.protocol.core.manager.IProtocolTypeManager;
import com.ink.protocol.core.po.ProtocolType;

public class ProtocolTypeManagerImpl extends BaseManager<ProtocolType, Long> implements IProtocolTypeManager{

    @Override
    protected EntityDao<ProtocolType, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
