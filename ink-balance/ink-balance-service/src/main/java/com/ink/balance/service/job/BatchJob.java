package com.ink.balance.service.job;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.service.impl.service.ChannelDataServiceImpl;
import com.ink.base.log.util.YinkerLogger;
import com.ink.job.AbstractBaseJob;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bo.chen
 * @date 2016-06-16
 */
@Component("batchJob")
public class BatchJob extends AbstractBaseJob {
    YinkerLogger logger = YinkerLogger.getLogger(ChannelDataServiceImpl.class);

    @Autowired
    private IChannelDataManager channelDataManager;

    @Override
    public void execute(){
    	 try {
             logger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                     "HTTP定时请求，开始解析民生渠道数据并入库...", null);
             Date dNow = new Date(); // 当前时间
             Calendar calendar = Calendar.getInstance(); // 得到日历
             calendar.setTime(dNow);// 把当前时间赋给日历
             calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
             Date dBefore = calendar.getTime();
             
             CheckChannelParamInput checkChannel=new CheckChannelParamInput();
             checkChannel.setCheckDate(dBefore);
             checkChannel.setChannelNo(SysParamConst.CMBC_CHANNEL_NO);
             checkChannel.setChannelMerchantNo(SysParamConst.CMBC_JLC_MERCHANT_NO);
             channelDataManager.readBatchChannelData(checkChannel);
         } catch (Exception e) {
             logger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                     "HTTP定时请求，开始解析民生渠道数据并入库异常", e, null);
         }
    }


}
