package com.ink.balance.core.manager.impl;

import com.ink.balance.core.dao.ICheckBalanceDao;
import com.ink.balance.core.manager.ICheckBalanceManager;
import com.ink.balance.core.po.CheckBalance;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年4月28日 下午1:36:38
 * @description 描述：调账数据参数manager实现
 */
@Service("checkBalanceManager")
@Transactional
public class CheckBalanceManagerImpl extends BaseManager<CheckBalance, Long>
        implements ICheckBalanceManager {

    YinkerLogger loger = YinkerLogger.getLogger(CheckBalanceManagerImpl.class);

    @Autowired
    private ICheckBalanceDao checkBalanceDao;

    @Override
    protected EntityDao<CheckBalance, Long> getEntityDao() {
        return this.checkBalanceDao;
    }

    public void setCheckBalanceDao(ICheckBalanceDao checkBalanceDao) {
        this.checkBalanceDao = checkBalanceDao;
    }

    @Override
    public List<CheckBalance> find(PageRequest arg0) {
    	return super.find(arg0);
    }

    @Override
    public Page<CheckBalance> findPage(PageRequest arg0) {
        return super.findPage(arg0);
    }

    @Override
    public CheckBalance getById(Long id) {
        return super.getById(id);
    }

    @Override
    public CheckBalance getById(Long arg0, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Long arg0) {
        return 0;
    }

    @Override
    public int save(CheckBalance checkBalance) {
        return super.save(checkBalance);
    }

    @Override
    public int saveBatch(List<CheckBalance> arg0) {
        return 0;
    }

    @Override
    public int update(CheckBalance arg0) {
        return 0;
    }

    /**
     * 根据主键获取调账对象
     */
    @Override
    public CheckBalance selectByPrimaryKey(Long id) {
        return checkBalanceDao.selectByPrimaryKey(id);
    }

    /**
     * 根据平台订单号获取调账对象
     */
    @Override
    public CheckBalance getBalanceDetails(String platformOrderNo) {
        return checkBalanceDao.getBalanceDetails(platformOrderNo);
    }

}
