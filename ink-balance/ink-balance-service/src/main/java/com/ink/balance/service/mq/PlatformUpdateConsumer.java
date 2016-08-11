package com.ink.balance.service.mq;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.manager.impl.BalanceDataManagerImpl;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.balance.core.util.VeDate;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author bo.chen
 * @Description mq更新渠道数据
 * @date 2016年4月11日 上午11:03:07
 */
public class PlatformUpdateConsumer {
    YinkerLogger logger = YinkerLogger.getLogger(BalanceDataManagerImpl.class);
    @Resource
    AmqpTemplate amqpTemplate;

    @Autowired
    private IPlatformDataManager platformDataManager;

    public void handle(Map<String, Object> params) throws Exception {

        logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "从mq获取平台更新数据#######################################:" + params.toString(), null);
        //平台订单号
        String platformOrderNo;
        // 到账金额
        BigDecimal arrivedAmt;
        //支付渠道（1：CMBC）
        String channelNo;
        //支付渠道商户号
        String channelMerchantNo;
        //交易流水号
        String transNo;
        //支付状态
        int payStatus;
        //到账时间
        Date arrivedTime;
        //支付方式
        String payMethod;
        boolean checkFlag = false;
        PlatformData pd = new PlatformData();
        PlatformDataQuery platformDataQuery = new PlatformDataQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (params != null) {
                platformOrderNo = params.get("platformOrderNo") + "";
                pd.setPlatformOrderNo(platformOrderNo);
                platformDataQuery.setPlatformOrderNo(platformOrderNo);
                payStatus = Integer.parseInt(params.get("payStatus") + "");
                arrivedTime = sdf.parse(params.get("arrivedTime") + "");
                arrivedAmt = new BigDecimal(params.get("arrivedAmt").toString());
                if (params.get("channelMerchantNo") != null) {
                    channelMerchantNo = params.get("channelMerchantNo") + "";
                    pd.setChannelMerchantNo(channelMerchantNo);
                    platformDataQuery.setChannelMerchantNo(channelMerchantNo);
                }
                if (params.get("channelNo") != null) {
                    channelNo = params.get("channelNo") + "";
                    pd.setChannelNo(channelNo);
                    platformDataQuery.setChannelNo(channelNo);
                }
                if (params.get("transNo") != null) {
                    transNo = params.get("transNo") + "";
                    pd.setTransNo(transNo);
                }
                if (params.get("payMethod") != null) {
                    payMethod = params.get("payMethod") + "";
                    pd.setPayMethod(payMethod);
                }
                pd.setPayStatus(payStatus);
                pd.setArrivedTime(arrivedTime);
                pd.setArrivedAmt(arrivedAmt);
                pd.setCreateDate(new Date());
                pd.setUpdateDate(new Date());
                //判断到账日期是否小于当前的日期，如果小于才判断
                int minus = VeDate.daysBetween(arrivedTime, new Date());
                //判断是否对完账或者在对账中
                if (minus < 0) {
                    checkFlag = platformDataManager.isExistPlatformNoInRedis(platformDataQuery);
                    if (checkFlag) {
                        logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                                "该订单在对账中或完成对账，无法更新" + params.toString(), null, null);
                        throw new AmqpRejectAndDontRequeueException("rabbitmq update exception!");
                    }
                }
                int insNum = platformDataManager.secondQueuesaveOrUpdate(pd);
                if (insNum > 0) {
                    logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                            "从mq获取平台更新数据成功:" + pd.toString(), pd.toString());
                } else {
                    // 没有成功消费，no_ack
                    logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                            "从mq获取平台更新数据异常:" + params.toString(), null, null);
                    throw new AmqpRejectAndDontRequeueException("rabbitmq insert exception!");
                }
            }
        } catch (Exception e) {
            amqpTemplate.convertAndSend("SmsGatewayQueue", params);
            logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "从mq更新平台数据异常:" + params.toString(), e, null);
            throw new AmqpRejectAndDontRequeueException("rabbitmq update exception!");
        }
    }

}
