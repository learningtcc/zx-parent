package com.ink.balance.core.manager;

import java.util.List;

import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.IBaseManager;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:26:56
 * @description 描述：平台数据数据参数manager接口
 */
public interface IPlatformDataManager extends
        IBaseManager<PlatformData, Long> {
    PlatformData selectByPrimaryKey(Long id);

    /**
     * 获取平台数据list
     */
    List<PlatformData> getPlatformDataList(PlatformDataQuery query);

    /**
     * 根据平台订单号更改渠道数据
     */
    int updatePlatformByPlatformOrderNo(PlatformDataQuery query);

    /**
     * 获取平台数据
     */
    PlatformData getPlatformData(PlatformDataQuery query);

    /**
     * 获取平台本次对账数据List（包含前期驻留数据）
     */
    List<PlatformData> getPlatformBalanceList(PlatformDataQuery query);

    /**
     * 获取平台对账总笔数和总金额
     */
    CheckChannel getPlatformSumAmtAndCount(PlatformDataQuery query);

    /**
     * 保存平台数据
     */
    int savePlatformData(PlatformData data);

    /**
     * MQ的插入队列saveOrUpdate
     */
    int firstQueuesaveOrUpdate(PlatformData platformData);

    /**
     * MQ的更新队列saveOrUpdate
     */
    int secondQueuesaveOrUpdate(PlatformData platformData);

    /**
     * 判断redis中是否存在该订单号（正在对账中的状态）
     */
    boolean isExistPlatformNoInRedis(PlatformDataQuery platformDataQuery);

}
