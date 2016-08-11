package com.ink.balance.core.manager;

import java.util.List;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.ret.Result;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.base.IBaseManager;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午8:14:24
 * @description 描述：真正的对账接口类
 */
public interface IBalanceDataManager extends IBaseManager<ChannelData, Integer> {

    Result balanceData(List<ChannelData> channelDataList,
                       List<PlatformData> platformDataList, CheckChannel cc);

    void executeBalanceData(CheckChannel cc);

    /**
     * @return String
     * @Description core项目对账入口，此方法直接被service的http请求调用
     * @author bo.chen
     * @date 2016年4月18日 下午5:16:44
     */
    String balanceData(CheckChannelParamInput checkChannel);
}
