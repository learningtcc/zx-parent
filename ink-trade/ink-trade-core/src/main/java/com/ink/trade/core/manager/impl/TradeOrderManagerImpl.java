package com.ink.trade.core.manager.impl;

import java.util.Date;
import java.util.List;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.ITradeLogDao;
import com.ink.trade.core.dao.ITradeOrderDao;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.TradeLog;
import com.ink.trade.core.po.TradeOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tradeOrderManager")
@Transactional
public class TradeOrderManagerImpl extends BaseManager<TradeOrder, Long>
        implements ITradeOrderManager {

    @Autowired
    private ITradeOrderDao tradeOrderDao;
    @Autowired
    private ITradeLogDao tradeLogDao;

    @Override
    protected EntityDao<TradeOrder, Long> getEntityDao() {
        return tradeOrderDao;
    }

    @Override
    public List<TradeOrder> findTradeOrders(TradeOrder record) {
        return tradeOrderDao.findTradeOrders(record);
    }

    @Override
    public TradeOrder getByMerNoAndMerOrderNo(TradeOrder record) {
        return tradeOrderDao.getByMerNoAndMerOrderNo(record);
    }

    @Transactional
    @Override
    public int saveOrderAndCreateLog(TradeOrder order) {
        int affectRow = tradeOrderDao.save(order);
        createTradeLog(order);
        return affectRow;
    }

    @Transactional
    @Override
    public int updateOrderAndCreateLog(TradeOrder order) {
        int affectRow = tradeOrderDao.update(order);
        if(affectRow != 1){
            throw new RuntimeException("update expected 1 row ,actual "+affectRow+" rows");
        }
        createTradeLog(order);
        return affectRow;
    }
    @Transactional
    @Override
    public int updateStatusAndCreateLog(TradeOrder order) {
        int affectRow = tradeOrderDao.updateStatus(order);
        if(affectRow != 1){
            throw new TradeException("update expected 1 row ,actual "+affectRow+" rows");
        }
        createTradeLog(order);
        return affectRow;
    }

    /**
     * 创建交易订单流水
     *
     * @param order
     * @return
     */
    private void createTradeLog(TradeOrder order) {

        TradeLog tradeLog = new TradeLog();
        tradeLog.setReqId(order.getReqId());// 请求流水号
        tradeLog.setMchId(order.getMchId());// 商户号
        tradeLog.setTxnCode(order.getTxnCode());// 交易码
        tradeLog.setOrderDate(order.getOrderTime());// 订单日期
        tradeLog.setAmt(order.getAmt());// 交易金额
        tradeLog.setStatus(order.getStatus());// 订单状态
        tradeLog.setOperator(order.getOperator());// 操作者
        tradeLog.setChannelNo(order.getChannelNo());// 渠道号
        tradeLog.setUserId(order.getUserId());// 用户号
        tradeLog.setCreateTime(new Date());// 创建时间
        tradeLog.setCardNo(order.getCardNo());// 卡号
        tradeLog.setPhone(order.getPhoneNo());// 手机号
        tradeLog.setOrdId(order.getOrderId());//交易订单号
        tradeLog.setCid(order.getCid());// 绑卡关系主键
        tradeLogDao.save(tradeLog);
    }
    @Override
    public int updateNotNull(TradeOrder tradeOrder){
		return tradeOrderDao.updateNotNull(tradeOrder);
	}
}
