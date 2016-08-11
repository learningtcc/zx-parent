package com.ink.balance.api.service;

import com.ink.balance.api.model.in.ChannelDataQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelDataOutput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;

/**
 * @author xuguoqi
 * @Description 渠道数据dubbo接口
 * @date 2016年4月11日 上午11:03:07
 */
public interface IChannelDataService {

    /**
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<ChannelDataOutput>>
     * @Description 分页查询
     * @author xuguoqi
     * @date 2016年4月11日 上午11:10:58
     */
    CommonOutput<CheckCommonPageOutput<ChannelDataOutput>> pageQuery(PageParamInput paramInput);

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    CommonOutput<Object> pageQuery(ChannelDataQueryParamInput param, PageParamInput pageParam);

    /**
     * @param id
     * @return CommonOutput<ChannelDataOutput>
     * @Description 获取渠道信息明细
     * @author bo.chen
     * @date 2016年4月19日 下午5:17:30
     */
    CommonOutput<ChannelDataOutput> getDetails(Long id);


}
