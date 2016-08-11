package com.ink.balance.core.manager;

import java.util.List;

import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.base.IBaseManager;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:26:56
 * @description 描述：渠道参数manager接口
 */
public interface IChannelParamManager extends
        IBaseManager<ChannelParam, Long> {

    /**
     * 获取渠道参数信息
     */
    ChannelParam getChannelParam(ChannelParamQuery query);
    /**
     * 
    * @Title: getChannelParamList 
    * @Description: 获取渠道数据集合
    * @param @param query
    * @param @return
    * @return List<ChannelParam> 
    * @author zhaojie
    * @date 2016年7月18日 下午5:14:58
    * @throws
     */
    List<ChannelParam> getChannelParamList(ChannelParamQuery query);

}
