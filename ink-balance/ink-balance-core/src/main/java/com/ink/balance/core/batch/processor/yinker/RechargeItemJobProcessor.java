package com.ink.balance.core.batch.processor.yinker;

import com.ink.balance.core.po.ChannelData;

import org.springframework.batch.item.ItemProcessor;

public class RechargeItemJobProcessor implements
        ItemProcessor<ChannelData, ChannelData> {
    /**
     * @param item
     * @return ChannelData
     * @Description 处理每一行数据
     * @author bo.chen
     * @date 2016年4月11日 上午11:10:58
     */
    @Override
    public ChannelData process(ChannelData item) throws Exception {
        String platformOrderNo = item.getPlatformOrderNo();
        if (platformOrderNo != null && platformOrderNo.equals("CB1008610010")) {
            //判断如果是对账文件标题行，则返回null不作处理
            return null;
        }
        return item;
    }

}
