package com.ink.protocol.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.protocol.core.manager.IProtocolTextManager;
import com.ink.protocol.core.po.ProtocolText;

public class ProtocolTextManagerImpl extends BaseManager<ProtocolText, Long> implements IProtocolTextManager {

    @Override
    protected EntityDao<ProtocolText, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
