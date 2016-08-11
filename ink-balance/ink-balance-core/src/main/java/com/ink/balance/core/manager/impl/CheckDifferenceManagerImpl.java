package com.ink.balance.core.manager.impl;

import java.util.List;

import com.ink.balance.core.dao.ICheckDifferenceDao;
import com.ink.balance.core.manager.ICheckDifferenceManager;
import com.ink.balance.core.po.CheckDifference;
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
 * @description 描述：差异数据参数manager实现
 */

@Service("checkDifferenceManager")
@Transactional
public class CheckDifferenceManagerImpl extends
        BaseManager<CheckDifference, Long> implements
        ICheckDifferenceManager {

    YinkerLogger loger = YinkerLogger.getLogger(CheckDifferenceManagerImpl.class);

    @Autowired
    private ICheckDifferenceDao checkDifferenceDao;

    public ICheckDifferenceDao getCheckDifferenceDao() {
        return checkDifferenceDao;
    }

    public void setCheckDifferenceDao(ICheckDifferenceDao checkDifferenceDao) {
        this.checkDifferenceDao = checkDifferenceDao;
    }

    @Override
    public List<CheckDifference> find(PageRequest arg0) {
        return super.find(arg0);
    }

    @Override
    public Page<CheckDifference> findPage(PageRequest arg0) {
        return super.findPage(arg0);
    }

    @Override
    public CheckDifference getById(Long id) {
        return super.getById(id);
    }

    @Override
    public CheckDifference getById(Long id, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Long id) {
        return 0;
    }

    @Override
    public int save(CheckDifference arg0) {
        return 0;
    }

    @Override
    public int saveBatch(List<CheckDifference> arg0) {
        return 0;
    }

    @Override
    public int update(CheckDifference arg0) {
        return 0;
    }

    @Override
    protected EntityDao<CheckDifference, Long> getEntityDao() {
        return this.checkDifferenceDao;
    }

    /**
     * 插入差异数据
     */
    @Override
    public int insertCheckDifference(CheckDifference checkDifference) {
        return checkDifferenceDao.insertSelective(checkDifference);
    }

    /**
     * 更新差异数据
     */
    @Override
    public int updateByPrimaryKeySelective(CheckDifference record) {
        return checkDifferenceDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 通过主键查询差异数据
     */
    @Override
    public CheckDifference selectByPrimaryKey(Long id) {
        return checkDifferenceDao.selectByPrimaryKey(id);
    }

    /**
     * 通过平台订单号查询差异数据List集合
     */
    @Override
    public List<CheckDifference> selectByPlatformOrderNo(String platformOrderNo) {
        return checkDifferenceDao.selectByPlatformOrderNo(platformOrderNo);
    }

}
