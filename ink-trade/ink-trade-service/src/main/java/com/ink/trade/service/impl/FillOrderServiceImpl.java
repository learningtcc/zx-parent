package com.ink.trade.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QueryPayAccountIn;
import com.ink.channel.api.model.out.QueryPayAccountOut;
import com.ink.channel.api.service.Pay2AccountQueryService;
import com.ink.job.AbstractBaseJob;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.model.in.FillOrderInput;
import com.ink.trade.api.model.out.FillOrderOutput;
import com.ink.trade.api.service.IFillOrderService;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.po.UnknownOrder;
import com.ink.trade.service.PreCollectionCallBackService;
import com.ink.trade.service.PrepaidCallBackService;
import com.ink.trade.service.dto.PreCollectionCallBackDto;

/**
 * 补单服务实现类
 * Created by huohb on 2016/5/11.
 */
@Service("fillOrderService")
public class FillOrderServiceImpl extends AbstractBaseJob implements IFillOrderService {

    private final YinkerLogger logger = YinkerLogger.getLogger(FillOrderServiceImpl.class);

    private static final String EXECUTE_REMARK = "192.168.1.1";
    @Autowired
    private IUnknownOrderManager unknownOrderManager;// 未知状态订单Manager
    @Autowired
    private Pay2AccountQueryService pay2AccountQueryService;// 订单查询

    private static final String ORDER_STATUS_SUCCESS = "00";// 成功

    private static final String ORDER_STATUS_FAILED = "02";// 失败

    @Autowired
    private PreCollectionCallBackService preCollectionCallBackService;// 代收回调接口
    @Autowired
    private PrepaidCallBackService prepaidCallBackService;// 提现回调接口

    @Override
    public FillOrderOutput fillOrder(FillOrderInput input) {
        /*
         * 可标记任务需具备以下条件中一项
         * 1、未被标记的任务
         * 2、不是自己的任务，但是任务最后更新时间在半小时之前，并且未到达终态，并且任务执行次数小于5次
         */
        final Date sysdate = new Date();// 系统时间
        Map<String, Object> remarkParam = new HashMap<String, Object>();
        remarkParam.put("executeRemark", EXECUTE_REMARK);// 标记机器
        remarkParam.put("lastUpdateTime", sysdate);// 最后更新时间
        remarkParam.put("orderTime", sysdate);// 订单的终止时间，大于此时间的订单不进行标记
        remarkParam.put("finalStatus", "N");// 是否终态
        remarkParam.put("timeOut", 1800);// 超时时间（单位：秒）
        remarkParam.put("maxExecCounts", 5);// 任务最多执行次数
        remarkParam.put("noneRemarkStatus", "NONE");// 未被标记状态
        remarkParam.put("perRemarkRecords", 1000);// 单次标记记录数

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("finalStatus", "N");// 非终态
        queryParam.put("executeCount", 5);// 执行次数
        queryParam.put("executeRemark", EXECUTE_REMARK);
        queryParam.put("perQueryRecords", 500);// 单次查询记录数

        int remarkRows = 0;// 标记任务的条数，如果未标记到任务，结束此次调度
        List<UnknownOrder> result = null;
        // 调度结束的条件：没有待标记的任务并且标记的任务都已处理完成
        while ((remarkRows = remarkTask(remarkParam)) != 0 || !CollectionUtils.isEmpty((result = queryTask(queryParam)))) {
            logger.info(remarkRows != 0 ? "机器" + EXECUTE_REMARK + "本次标记任务" + remarkRows + "条" : "本次待处理条数为" + result.size());
            QueryPayAccountOut output = null;
            if (result == null) {
                result = queryTask(queryParam);
            }
            // 遍历未知状态订单查询渠道
            for (UnknownOrder unknownOrder : result) {
                output = queryChannel(unknownOrder);

                // 订单成功
                if (ORDER_STATUS_SUCCESS.equals(output.getOrderStatus())) {
                    logger.info("订单" + unknownOrder.getPayOrderNo() + "更新为成功");
                    updateOrderStatus(unknownOrder, true);
                }// 订单失败
                else if (ORDER_STATUS_FAILED.equals(output.getOrderStatus())) {
                    logger.info("订单" + unknownOrder.getPayOrderNo() + "更新为失败");
                    updateOrderStatus(unknownOrder, false);
                } else {
                    logger.info("订单" + unknownOrder.getPayOrderNo() + "不处理，未查询到终态");
                    // 更改执行次数+1
                    updateExecuteCount(unknownOrder);
                }
                
            }

        }

        return null;
    }
    
    

    /**
     * 标记任务
     *
     * @param remarkParam
     * @return
     */
    private int remarkTask(Map<String, Object> remarkParam) {
        try {
            return unknownOrderManager.remarkOrder(remarkParam);
        } catch (Exception e) {
            logger.error("标记任务失败", e);
        }
        return 0;
    }

    /**
     * @param queryParam
     * @return
     */
    private List<UnknownOrder> queryTask(Map<String, Object> queryParam) {
        List<UnknownOrder> result = null;
        try {
            result = unknownOrderManager.queryTask(queryParam);
        } catch (Exception e) {
            logger.error("查询未知状态订单出错", e);
        }
        return result;
    }

    /**
     * 查询渠道
     *
     * @param unknownOrder
     * @return
     */
    private QueryPayAccountOut queryChannel(UnknownOrder unknownOrder) {
        QueryPayAccountOut queryPayAccountOut = new QueryPayAccountOut();
        try {
            QueryPayAccountIn queryPayAccountIn = new QueryPayAccountIn();
            queryPayAccountIn.setChannelId(unknownOrder.getChannelNo());// 渠道号
            queryPayAccountIn.setMerchantNo(unknownOrder.getMchId());// 商户号
            queryPayAccountIn.setOrderNo(unknownOrder.getPayOrderNo());// 支付订单号
            //ChannelBizType
            String queryType = null;
            // 代收
            if (PayType.COLLECT.getValue().equals(unknownOrder.getTransType())) {
                queryType = ChannelBizType.PAY_QUERY.getCode();
            } // 代付
            else if (PayType.PAY.getValue().equals(unknownOrder.getTransType())) {
                queryType = ChannelBizType.WITHDRAW_QUERY.getCode();
            } // 快捷
            else if (PayType.QUICKPAY.getValue().equals(unknownOrder.getTransType())) {
                queryType = ChannelBizType.QUICK_QUERY.getCode();
            } // 认证支付
            else if (PayType.AUTHPAY.getValue().equals(unknownOrder.getTransType())) {
                queryType = ChannelBizType.AUTHEN_NO_VALID_CODE_QUERY_PAY.getCode();
            } // 网关
            else if (PayType.GATEWAY.getValue().equals(unknownOrder.getTransType())) {
                // do nothing
            } else {
                // do nothing
            }
            queryPayAccountIn.setQueryType(queryType);// 查询类型（代收、代付、快捷、认证支付）
            queryPayAccountIn.setTradeDate(unknownOrder.getOrderTime());// 订单日期
            queryPayAccountOut = pay2AccountQueryService.accountQuery(queryPayAccountIn);
        } catch (Exception e) {
            logger.error("交易查证查询渠道异常", e);
        }
        return queryPayAccountOut;
    }

    /**
     * 更新执行次数
     *
     * @param unknownOrder
     */
    private void updateExecuteCount(UnknownOrder unknownOrder) {
        try {
            unknownOrder.setExecuteCount(unknownOrder.getExecuteCount() + 1);// 执行次数加1
            unknownOrderManager.update(unknownOrder);
        } catch (Exception e) {
            logger.error("更新未知状态订单出错", e);
        }
    }

    /**
     * 更新订单状态
     *
     * @param unknownOrder
     * @param isSuccess
     */
    private void updateOrderStatus(UnknownOrder unknownOrder, boolean isSuccess) {
        unknownOrder.setFinalStatus("Y");
        updateExecuteCount(unknownOrder);

        if (PayType.PAY.getValue().equals(unknownOrder.getTransType())) {
            // 提现回调
            prepaidCallBackService.callBack(unknownOrder.getPayOrderNo(), isSuccess, null);
        } else {
            // 充值回调
            PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
            dto.setOrderNo(unknownOrder.getPayOrderNo());// 支付订单号
            dto.setOrderStatus(isSuccess ? "01" : "03");// 订单状态
            preCollectionCallBackService.callBack(dto);
        }
    }

    @Override
    public void execute() throws Exception {
        this.fillOrder(new FillOrderInput());
    }
}