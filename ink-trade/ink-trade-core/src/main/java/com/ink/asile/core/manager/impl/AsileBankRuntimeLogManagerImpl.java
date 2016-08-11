package com.ink.asile.core.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.asile.core.manager.IAsileBankRuntimeLogManager;
import com.ink.asile.core.po.AsileBankRuntimeLog;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
@Transactional
@Service
public class AsileBankRuntimeLogManagerImpl extends BaseManager<AsileBankRuntimeLog, Long> implements IAsileBankRuntimeLogManager {

    @Override
    protected EntityDao<AsileBankRuntimeLog, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
