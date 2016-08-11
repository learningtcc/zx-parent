package com.ink.balance.core.manager.impl;

import java.util.List;

import com.ink.balance.core.dao.IChannelParamDao;
import com.ink.balance.core.manager.IChannelParamManager;
import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.po.CheckDifference;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午2:57:33
 * @description 描述：渠道参数manager实现
 */
@Service("channelParamManager")
@Transactional
public class ChannelParamManagerImpl extends BaseManager<ChannelParam, Long>
        implements IChannelParamManager {

    YinkerLogger loger = YinkerLogger.getLogger(ChannelParamManagerImpl.class);

    @Autowired
    private IChannelParamDao channelParamDao;

    public IChannelParamDao getChannelParamDao() {
        return channelParamDao;
    }

    public void setChannelParamDao(IChannelParamDao channelParamDao) {
        this.channelParamDao = channelParamDao;
    }

    @Override
    public List<ChannelParam> find(PageRequest arg0) {
        return null;
    }

    @Override
    public Page<ChannelParam> findPage(PageRequest arg0) {
    	 return super.findPage(arg0);
    }

    @Override
    public ChannelParam getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ChannelParam getById(Long id, boolean arg1)
            throws DataAccessException {
        return null;
    }

    @Override
    public int removeById(Long arg0) {
        return 0;
    }

    @Override
    public int save(ChannelParam arg0) {
        return super.save(arg0);
    }

    @Override
    public int saveBatch(List<ChannelParam> arg0) {
        return 0;
    }

    @Override
    public int update(ChannelParam arg0) {
    	return super.update(arg0);
    }

    @Override
    protected EntityDao<ChannelParam, Long> getEntityDao() {
        return this.channelParamDao;
    }

    /**
     * 获取渠道参数信息
     */
    @Override
    public ChannelParam getChannelParam(ChannelParamQuery query) {
        return channelParamDao.getChannelParam(query);
    }
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
    public List<ChannelParam> getChannelParamList(ChannelParamQuery query) {
        return channelParamDao.getChannelParamList(query);
    }
}
