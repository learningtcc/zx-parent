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
import com.ink.base.log.util.YinkerLogger;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author bo.chen
 * @Description mq新增渠道数据
 * @date 2016年4月11日 上午11:03:07
 */
public class PlatformNotifyConsumer {
    YinkerLogger logger = YinkerLogger.getLogger(BalanceDataManagerImpl.class);
    @Resource
    AmqpTemplate amqpTemplate;

    @Autowired
    private IPlatformDataManager platformDataManager;

    public void handle(Map<String, Object> params) throws Exception {
        logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "从mq获取平台插入数据@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+params.toString(), null);
        //支付金额
        BigDecimal amt;
        //平台订单号
        String platformOrderNo;
        //支付生成时间
        Date payTime;
        //支付状态
        int payStatus;
        PlatformData pd = new PlatformData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (params != null) {
                platformOrderNo = params.get("platformOrderNo") + "";
                amt = new BigDecimal(params.get("amt").toString());
                payTime = sdf.parse(params.get("payTime") + "");
                pd.setAmt(amt);
                pd.setPayTime(payTime);
                payStatus = Integer.parseInt(params.get("payStatus") + "");
                pd.setPlatformOrderNo(platformOrderNo);
                pd.setPayStatus(payStatus);
                pd.setCreateDate(new Date());
                pd.setUpdateDate(new Date());
                int insNum = platformDataManager.firstQueuesaveOrUpdate(pd);
                //.savePlatformData(pd);
                if (insNum > 0) {
                    logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                            "从mq获取平台插入数据成功,"+pd.toString(), null);
                } else {
                    // 没有成功消费，no_ack
                    logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                            "从mq获取平台插入数据异常:"+params.toString(), null, null);
                    throw new AmqpRejectAndDontRequeueException("rabbitmq insert exception!");
                }
            }
        } catch (Exception e) {
            logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "从mq获取平台数据异常:"+params.toString(), e,null );
            amqpTemplate.convertAndSend("SmsGatewayQueue", params);
            throw new AmqpRejectAndDontRequeueException("rabbitmq insert exception!");
        }


    }

}
