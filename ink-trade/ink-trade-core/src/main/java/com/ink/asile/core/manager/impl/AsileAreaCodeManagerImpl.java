package com.ink.asile.core.manager.impl;

import org.springframework.transaction.annotation.Transactional;

import com.ink.asile.core.manager.IAsileAreaCodeManager;
import com.ink.asile.core.po.AsileAreaCode;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
@Transactional
public class AsileAreaCodeManagerImpl extends BaseManager<AsileAreaCode, Long> implements IAsileAreaCodeManager {

    @Override
    protected EntityDao<AsileAreaCode, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
