package com.ink.balance.core.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ink.balance.core.dao.IPlatformDataDao;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

import redis.BaseRedis;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:36:38
 * @description 描述：平台数据数据参数manager实现
 */
@Service("platformDataManager")
@Transactional
public class PlatformDataManagerImpl extends BaseManager<PlatformData, Long>
        implements IPlatformDataManager {

    YinkerLogger loger = YinkerLogger.getLogger(PlatformDataManagerImpl.class);

    @Autowired
    private IPlatformDataDao platformDataDao;

    // 平台数据set集合
    private String platformNoSet = "PLATFORM_NO_SET_DATA";

    @Autowired
    private BaseRedis baseRedis;

    public void setPlatformDataDao(IPlatformDataDao platformDataDao) {
        this.platformDataDao = platformDataDao;
    }

    @Override
    protected EntityDao<PlatformData, Long> getEntityDao() {
        return this.platformDataDao;
    }

    @Override
    public List<PlatformData> find(PageRequest arg0) {
        return super.find(arg0);
    }

    @Override
    public Page<PlatformData> findPage(PageRequest arg0) {
        return super.findPage(arg0);
    }

    @Override
    public PlatformData getById(Long id) {
        return super.getById(id);
    }

    @Override
    public PlatformData getById(Long arg0, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Long arg0) {
        return 0;
    }

    @Override
    public int save(PlatformData arg0) {
        return 0;
    }

    @Override
    public int saveBatch(List<PlatformData> arg0) {
        return 0;
    }

    @Override
    public int update(PlatformData arg0) {
        return 0;
    }

    @Override
    public PlatformData selectByPrimaryKey(Long id) {
        return platformDataDao.selectByPrimaryKey(id);
    }

    /**
     * 获取平台数据list
     */
    @Override
    public List<PlatformData> getPlatformDataList(PlatformDataQuery query) {
        return platformDataDao.getPlatformDataList(query);
    }

    /**
     * 根据平台订单号更改渠道数据
     */
    @Override
    public int updatePlatformByPlatformOrderNo(PlatformDataQuery query) {
        query.setUpdateDate(new Date());
        return platformDataDao.updatePlatformByPlatformOrderNo(query);
    }

    /**
     * 获取平台数据
     */
    @Override
    public PlatformData getPlatformData(PlatformDataQuery query) {
        List<PlatformData> list = getPlatformDataList(query);
        PlatformData platformData = null;
        if (list != null && list.size() > 0) {
            platformData = list.get(0);
        }
        return platformData;
    }

    /**
     * 获取平台本次对账数据List（包含前期驻留数据）
     */
    @Override
    public List<PlatformData> getPlatformBalanceList(PlatformDataQuery query) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "获取平台数据开始，入参:", query.toString());
        try {
            return platformDataDao.getPlatformBalanceList(query);
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "获取平台数据异常", e, null);
            return null;
        }
    }

    /**
     * 获取平台对账总笔数和总金额
     */
    @Override
    public CheckChannel getPlatformSumAmtAndCount(PlatformDataQuery query) {
        return platformDataDao.getPlatformSumAmtAndCount(query);
    }

    /**
     * 保存平台数据
     */
    @Override
    public int savePlatformData(PlatformData data) {
        return platformDataDao.insertSelective(data);
    }

    /**
     * MQ的插入队列saveOrUpdate
     */
    @Override
    public int firstQueuesaveOrUpdate(PlatformData platformData) {
        return platformDataDao.firstQueuesaveOrUpdate(platformData);
    }

    /**
     * MQ的更新队列saveOrUpdate
     */
    @Override
    public int secondQueuesaveOrUpdate(PlatformData platformData) {
        return platformDataDao.secondQueuesaveOrUpdate(platformData);
    }

    /**
     * 判断redis中是否存在该订单号（正在对账中的状态）
     */
    @Override
    public boolean isExistPlatformNoInRedis(PlatformDataQuery platformDataQuery) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "开始判断redis中是否存在该订单号...", null);

        String channelNoRedisPrefix;//获取渠道编号和渠道商户号
        String channelNo;
        String channelMerchantNo;
        String platformOrderNo;
        boolean flag=false;
        Integer checkStatus=0;

        if (platformDataQuery != null) {

            //判断数据库中是对账完成
            PlatformData platformData=getPlatformData(platformDataQuery);
            if(platformData!=null){
                checkStatus=platformData.getCheckStatus();
            }
            if(checkStatus!=0){
                return true;
            }

            //判断redis中是否存在
            channelNo = platformDataQuery.getChannelNo();
            channelMerchantNo = platformDataQuery.getChannelMerchantNo();
            platformOrderNo = platformDataQuery.getPlatformOrderNo();
            channelNoRedisPrefix = channelNo + "_" + channelMerchantNo + "_";//获取渠道编号和渠道商户号
            flag=baseRedis.sismember(channelNoRedisPrefix + platformNoSet,platformOrderNo);
        }
        return flag;
    }

}
