package com.ink.balance.service.job;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.core.manager.IBalanceDataManager;
import com.ink.balance.core.util.VeDate;
import com.ink.balance.service.impl.service.ChannelDataServiceImpl;
import com.ink.base.log.util.YinkerLogger;
import com.ink.job.AbstractBaseJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* <p>Title:YinkerBalanceJob </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author zhaojie
* @date 2016年7月13日 上午11:13:54
 */
@Component("yinkerBalanceJob")
public class YinkerBalanceJob extends AbstractBaseJob {
    YinkerLogger logger = YinkerLogger.getLogger(ChannelDataServiceImpl.class);

    @Autowired
    private IBalanceDataManager balanceDataManager;

    @Override
    public void execute() {
        try {
            logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS,
                    "HTTP定时请求，开启银客懒猫对账...", null);
            Date dNow = new Date(); // 当前时间
            Calendar calendar = Calendar.getInstance(); // 得到日历
            calendar.setTime(dNow);// 把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
            Date dBefore = calendar.getTime(); // 得到前一天的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
            String defaultStartDate = sdf.format(dBefore); // 格式化前一天
            String defaultEndDate = sdf.format(dNow);//格式化当前日期
            // 交易日期
            Date tradeDate = VeDate.strToDate(defaultStartDate);
            // 对账日期
            Date checkDate = VeDate.strToDate(defaultEndDate);
            //获取渠道参数信息
            CheckChannelParamInput cc = new CheckChannelParamInput();
            cc.setTradeDate(tradeDate);
            cc.setCheckDate(checkDate);
            cc.setChannelNo(SysParamConst.YKLM_CHANNEL_NO);
            cc.setChannelMerchantNo(SysParamConst.YK_RECHARGE_MERCHANT_NO);
            balanceDataManager.balanceData(cc);
            cc.setChannelMerchantNo(SysParamConst.YK_WITHDRAW_MERCHANT_NO);
            balanceDataManager.balanceData(cc);
            cc.setChannelMerchantNo(SysParamConst.YK_TRANSACTION_MERCHANT_NO);
            balanceDataManager.balanceData(cc);
            cc.setChannelMerchantNo(SysParamConst.YK_COMMISSION_MERCHANT_NO);
            balanceDataManager.balanceData(cc);
        } catch (Exception e) {
            logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS,
                    "HTTP定时请求，开启银客懒猫异常", e, null);
        }
    }


}
