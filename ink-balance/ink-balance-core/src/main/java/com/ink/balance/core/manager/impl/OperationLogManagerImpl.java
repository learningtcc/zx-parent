package com.ink.balance.core.manager.impl;

import java.util.List;

import com.ink.balance.core.dao.IOperationLogDao;
import com.ink.balance.core.manager.IOperationLogManager;
import com.ink.balance.core.po.OperationLog;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:57:33
 * @description 描述：操作日志数据参数manager实现
 */

@Service("operationLogManager")
@Transactional
public class OperationLogManagerImpl extends BaseManager<OperationLog, Integer>
        implements IOperationLogManager {

    YinkerLogger loger = YinkerLogger.getLogger(OperationLogManagerImpl.class);

    @Autowired
    private IOperationLogDao operationLogDao;

    public IOperationLogDao getOperationLogDao() {
        return operationLogDao;
    }

    public void setOperationLogDao(IOperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
    }

    @Override
    public List<OperationLog> find(PageRequest arg0) {
        return null;
    }

    @Override
    public Page<OperationLog> findPage(PageRequest arg0) {
        return null;
    }

    @Override
    public OperationLog getById(Integer arg0) {
        return null;
    }

    @Override
    public OperationLog getById(Integer arg0, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Integer arg0) {
        return 0;
    }

    @Override
    public int saveBatch(List<OperationLog> arg0) {
        return 0;
    }

    @Override
    public int update(OperationLog arg0) {
        return 0;
    }

    @Override
    protected EntityDao<OperationLog, Integer> getEntityDao() {
        return null;
    }

    /**
     * 保存操作日志数据
     */
    @Override
    public int save(OperationLog operationLog) {
        return operationLogDao.save(operationLog);
    }

}
