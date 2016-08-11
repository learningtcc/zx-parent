package com.ink.balance.core.manager;

import java.util.Date;
import java.util.List;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.base.IBaseManager;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:26:56
 * @description 描述：渠道数据manager接口
 */
public interface IChannelDataManager extends IBaseManager<ChannelData, Long> {
    ChannelData selectByPrimaryKey(Long id);

    /**
     * 获取渠道数据List
     */

    List<ChannelData> getChannelDataList(ChannelDataQuery query);

    /**
     * 获取渠道数据
     */
    ChannelData getChannelData(ChannelDataQuery query);

    /**
     * 根据平台订单号更改渠道数据
     */
    int updateChannelByPlatformOrderNo(ChannelDataQuery query);

    /**
     * 获取渠道本次对账数据List（包含前期驻留数据）
     */
    List<ChannelData> getChannelBalanceList(ChannelDataQuery query);

    /**
     * 获取渠道对账总笔数和总金额
     */
    CheckChannel getChannelSumAmtAndCount(ChannelDataQuery query);

    /**
     * spring batch读取渠道文件入库
     */
    void readBatchChannelData(CheckChannelParamInput checkChannel);
    /**
     * 
    * @Title: readBatchYinkerData 
    * @Description: 读取银客对账数据
    * @param @param checkChannel
    * @return void 
    * @author zhaojie
    * @date 2016年7月12日 上午11:03:55
    * @throws
     */
    void readBatchYinkerData(CheckChannelParamInput checkChannel);
    /**
     * 
    * @Title: readBatchBooChannelData 
    * @Description: 读取宝付渠道数据
    * @param @param checkChannel
    * @return void 
    * @author zhaojie
    * @date 2016年7月14日 下午5:45:20
    * @throws
     */
    void readBatchBooChannelData(CheckChannelParamInput checkChannel);
}
