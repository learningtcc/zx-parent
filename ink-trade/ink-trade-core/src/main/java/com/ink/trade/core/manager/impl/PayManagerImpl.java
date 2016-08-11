package com.ink.trade.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IPayDao;
import com.ink.trade.core.dao.IPayLogDao;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.PayLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("payManager")
@Transactional
public class PayManagerImpl extends BaseManager<Pay, Long> implements
		IPayManager {

	@Autowired
	private IPayDao payDao;
    @Autowired
    private IPayLogDao payLogDao;

	@Override
	protected EntityDao<Pay, Long> getEntityDao() {
		return payDao;
	}

	@Override
	public Pay findPayOrder(Pay pay) {
		return payDao.findPayOrder(pay);
	}

    @Transactional
    @Override
    public int savePayAndCreateLog(Pay pay) {
        int affectRow = payDao.save(pay);
        createPayLog(pay);
        return affectRow;
    }
    @Transactional
    @Override
    public int updatePayAndCreateLog(Pay pay) {
        int affectRow = payDao.update(pay);
        if(affectRow != 1){
            throw new RuntimeException("update expected 1 row ,actual "+affectRow+" rows");
        }
        createPayLog(pay);
        return affectRow;
    }
    @Transactional
    @Override
    public int updateStatusAndCreateLog(Pay pay) {
        int affectRow = payDao.updateStatus(pay);
        if(affectRow != 1){
            throw new TradeException("update expected 1 row ,actual "+affectRow+" rows");
        }
        createPayLog(pay);
        return affectRow;
    }

    /**
     * 根据支付订单创建支付流水
     * @param pay
     */
    private void createPayLog(Pay pay){
        PayLog payLog = new PayLog();
        payLog.setPayId(pay.getId());// 支付信息主键
        payLog.setReqId(pay.getReqId());// 交易请求流水号
        payLog.setOrdId(pay.getOrdId());// 交易订单号
        payLog.setChanelName(pay.getChanelName());// 渠道名称
        payLog.setChanelNo(pay.getChanelNo());// 渠道号
        payLog.setAmt(pay.getAmt());// 支付金额
        payLog.setStatus(pay.getStatus());// 支付状态
        Date sysdate = new Date();
        payLog.setCreateTime(sysdate);// 创建时间
        payLog.setVersion(1);// 乐观锁
        payLog.setRemark(pay.getRemark());// 备注
        payLog.setReqTime(sysdate);// 请求时间
        payLog.setRepTime(pay.getRepTime());// 响应时间
        payLog.setLastupdateTime(sysdate);// 最后更新时间
        payLog.setMchId(pay.getMchId());// 商户号
        payLog.setOrderDate(pay.getOrderDate());// 订单日期
        payLog.setPayOrderId(pay.getPayOrderId());// 支付订单号
        payLog.setPayReqId(pay.getPayReqId());// 支付流水号
        payLog.setAsileRepCode(pay.getAsileRepCode());// 渠道响应码
        payLog.setAsileRepMsg(pay.getAsileRepMsg());// 渠道响应信息
        payLog.setRepCode(pay.getRepCode());// 返回给商户响应码
        payLog.setRepMsg(pay.getRepMsg());// 返回给商户响应信息

        payLogDao.save(payLog);
    }
    @Override
    public int updateNotNull(Pay pay){
		return payDao.updateNotNull(pay);
	}

}
