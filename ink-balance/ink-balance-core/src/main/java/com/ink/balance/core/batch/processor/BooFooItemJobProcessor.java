package com.ink.balance.core.batch.processor;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.batch.item.ItemProcessor;

public class BooFooItemJobProcessor implements
        ItemProcessor<ChannelData, ChannelData> {
    YinkerLogger loger = YinkerLogger.getLogger(BooFooItemJobProcessor.class);
    /**
     * @param item
     * @return ChannelData
     * @Description 处理每一行数据
     * @author bo.chen
     * @date 2016年4月11日 上午11:10:58
     */
    @Override
    public ChannelData process(ChannelData item) throws Exception {
        if (item != null) {
            String platformOrderNo = item.getPlatformOrderNo();
            if (platformOrderNo != null && platformOrderNo.equals("CB1008610010")) {
                //判断如果是对账文件标题行，则返回null不作处理
                return null;
            }
            if (item.getRemark() != null && item.getRemark().equals("ERROR")) {
                loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                        "宝付对账文件平台号为：" + platformOrderNo + "的数据出现错误", null);
                throw new Exception();
            }
        }
        return item;
    }

}
