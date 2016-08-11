
package com.ink.trade.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.ITradeLogDao;
import com.ink.trade.core.manager.ITradeLogManager;
import com.ink.trade.core.po.TradeLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huohb on 2016/4/19.
 * 交易流水Manager
 */
@Service("tradeLogManager")
@Transactional
public class TradeLogManagerImpl extends BaseManager<TradeLog, Long> implements ITradeLogManager {
    @Autowired
    private ITradeLogDao tradeLogDao;

    @Override
    protected EntityDao<TradeLog, Long> getEntityDao() {
        return this.tradeLogDao;
    }
    @Override
    public int updateNotNull(TradeLog tradeLog){
		return tradeLogDao.updateNotNull(tradeLog);
	}
}
