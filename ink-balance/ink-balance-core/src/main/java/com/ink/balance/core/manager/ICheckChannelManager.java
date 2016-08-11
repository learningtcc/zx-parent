package com.ink.balance.core.manager;

import com.ink.balance.core.po.CheckChannel;
import com.ink.base.IBaseManager;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:26:56
 * @description 描述：总对账数据参数manager接口
 */
public interface ICheckChannelManager extends
        IBaseManager<CheckChannel, Long> {

    /**
     * 插入总对账数据
     */
    int insertCheckChannel(CheckChannel checkChannel);

    /**
     * 更新总对账数据
     */
    int updateByPrimaryKeySelective(CheckChannel checkChannel);

}
