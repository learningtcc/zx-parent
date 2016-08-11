package com.ink.balance.core.manager.impl;

import com.ink.balance.core.dao.ICheckChannelDao;
import com.ink.balance.core.manager.ICheckChannelManager;
import com.ink.balance.core.po.CheckChannel;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:57:33
 * @description 描述：总对账数据参数manager实现
 */

@Service("checkChannelManager")
@Transactional
public class CheckChannelManagerImpl extends BaseManager<CheckChannel, Long>
        implements ICheckChannelManager {

    YinkerLogger loger = YinkerLogger.getLogger(CheckChannelManagerImpl.class);

    @Autowired
    private ICheckChannelDao checkChannelDao;

    public ICheckChannelDao getCheckChannelDao() {
        return checkChannelDao;
    }

    public void setCheckChannelDao(ICheckChannelDao checkChannelDao) {
        this.checkChannelDao = checkChannelDao;
    }

    @Override
    protected EntityDao<CheckChannel, Long> getEntityDao() {
        return this.checkChannelDao;
    }

    /**
     * 插入总对账数据
     */
    @Override
    public int insertCheckChannel(CheckChannel checkChannel) {
        return checkChannelDao.insertSelective(checkChannel);
    }

    /**
     * 更新总对账数据
     */
    @Override
    public int updateByPrimaryKeySelective(CheckChannel checkChannel) {
        return checkChannelDao.updateByPrimaryKeySelective(checkChannel);
    }

}
