package com.ink.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.model.in.OrderQueryInput;
import com.ink.trade.api.model.out.OrderQueryOutput;
import com.ink.trade.api.service.ITradeOrderQueryService;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.TradeOrder;

/**
 * 交易订单查询服务实现类
 * Created by huohb on 2016/5/5.
 */
@Service("tradeOrderQueryService")
public class TradeOrderQueryServiceImpl  implements ITradeOrderQueryService{

    private static final YinkerLogger logger = YinkerLogger.getLogger(TradeOrderQueryServiceImpl.class);
    @Autowired
    private ITradeOrderManager tradeOrderManager;// 交易订单anager

    @Override
    public OrderQueryOutput tradeOrderQuery(OrderQueryInput orderQueryInput) {

        TradeOrder param = new TradeOrder();
        param.setMchId(orderQueryInput.getMerchantId());
        param.setOrderId(orderQueryInput.getOrderId());
        TradeOrder tradeOrder = null;
        OrderQueryOutput orderQueryOutput = new OrderQueryOutput();
        try{
            tradeOrder = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        }catch(Exception e){
            logger.error("商户号"+orderQueryInput.getMerchantId()+"交易订单"+orderQueryInput.getOrderId()+"查询异常",e);
            // 设置查询失败返回码 TODO
            return orderQueryOutput;
        }
        if (tradeOrder == null){
            logger.error("未查询到商户号"+orderQueryInput.getMerchantId()+"的交易订单"+orderQueryInput.getOrderId());
            // 设置查询为空返回码 TODO
        }else{
            orderQueryOutput.setTradeCode(tradeOrder.getTxnCode());
            orderQueryOutput.setTradeDate(tradeOrder.getTradeDate());
            orderQueryOutput.setMerchantId(tradeOrder.getMchId());
            orderQueryOutput.setUserId(tradeOrder.getUserId());
            orderQueryOutput.setAmt(tradeOrder.getAmt());
            orderQueryOutput.setOrderId(tradeOrder.getOrderId());
            orderQueryOutput.setRequestId(tradeOrder.getReqId());
            orderQueryOutput.setOrderStatus(tradeOrder.getStatus());
            // 设置正常返回码 TODO
        }
        return orderQueryOutput;
    }
}
