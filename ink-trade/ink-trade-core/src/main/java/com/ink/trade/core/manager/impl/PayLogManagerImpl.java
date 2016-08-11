package com.ink.trade.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IPayLogDao;
import com.ink.trade.core.manager.IPayLogManager;
import com.ink.trade.core.po.PayLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huohb on 2016/4/19.
 * 支付流水Manager接口实现类
 */
@Service("payLogManager")
@Transactional
public class PayLogManagerImpl extends BaseManager<PayLog,Long> implements IPayLogManager{
    @Autowired
    private IPayLogDao payLogDao;

    @Override
    protected EntityDao<PayLog, Long> getEntityDao() {
        return payLogDao;
    }
    @Override
    public int updateNotNull(PayLog payLog){
		return payLogDao.updateNotNull(payLog);
	}
}
