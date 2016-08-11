package com.ink.balance.service.impl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.ChannelParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.in.PlatformDataMqParamInput;
import com.ink.balance.api.model.out.ChannelDataOutput;
import com.ink.balance.api.model.out.ChannelDataPageOutput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelParamService;
import com.ink.balance.core.manager.IChannelParamManager;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.base.utils.BeanCopyConverter;

/**
 * 
* <p>Title:ChannelParamServiceImpl </p>
* <p>Description: 渠道接口</p>
* <p>Company: </p> 
* @author zhaojie
* @date 2016年7月18日 下午5:21:00
 */
@Service("channelParamService")
public class ChannelParamServiceImpl implements IChannelParamService {

    YinkerLogger log = YinkerLogger.getLogger(ChannelParamServiceImpl.class);

    @Autowired
    @Qualifier("channelParamManager")
    private IChannelParamManager channelParamManager;

    /**
     * 
    * @Title: pageQuery 
    * @Description: 查询所有
    * @param @param param
    * @param @param pageParam
    * @param @return
    * @return CommonOutput<Object> 
    * @author zhaojie
    * @date 2016年7月22日 上午11:56:15
    * @throws
     */
    public List<ChannelParamOutput> pageQuery(PageParamInput paramInput) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询" + paramInput.toString(), null);

        ChannelParamQuery query = new ChannelParamQuery();
        query.setPageSize(paramInput.getNumPerPage());
        query.setPageNumber(paramInput.getPageNum());
        List<ChannelParam> page=new ArrayList<ChannelParam>();
        try {
            page = channelParamManager.getChannelParamList(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询:数据库查询异常" + paramInput.toString(), e, null);
        }
        List<ChannelParamOutput> list = (List<ChannelParamOutput>)  BeanCopyConverter.converterClass(page, ChannelParamOutput.class);
        return list;
    }
    /**
     * 
    * @Title: pageQuery 
    * @Description: 分页查询
    * @param @param param
    * @param @param pageParam
    * @param @return
    * @return CommonOutput<Object> 
    * @author zhaojie
    * @date 2016年7月22日 上午11:56:15
    * @throws
     */
    public CommonOutput<Object> pageChannel(ChannelParamInput param, PageParamInput pageParam) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询参数:" + param.toString(), null);
        ChannelParamQuery query = BeanCopyConverter.converterClass(param, ChannelParamQuery.class);
        query.setPageNumber(pageParam.getPageNum());
        query.setPageSize(pageParam.getNumPerPage());
        Page<ChannelParam> page;
        try {
            page = channelParamManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询:数据库查询异常:" + param.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        ChannelDataPageOutput<ChannelParamOutput> pageOutput = new ChannelDataPageOutput<ChannelParamOutput>();
        if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
            pageOutput.setTotal(page.getTotalCount());
            List<ChannelParam> result = page.getResult();
            List<ChannelParamOutput> list = (List<ChannelParamOutput>) BeanCopyConverter
                    .converterClass(result, ChannelParamOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<Object>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }
    /**
     * 
    * @Title: getDetails 
    * @Description: 获取渠道详细信息
    * @param @param id
    * @param @return
    * @return CommonOutput<ChannelParamOutput> 
    * @author zhaojie
    * @date 2016年7月25日 上午10:38:45
    * @throws
     */
    @Override
    public CommonOutput<ChannelParamOutput> getDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询 id :" + id, null);
        log.debug("渠道信息查询");
        ChannelParam channelParam;
        try {
        	channelParam = channelParamManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询:数据库查询异常:" + id, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == channelParam) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        ChannelParamOutput output = BeanCopyConverter.converterClass(channelParam, ChannelParamOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }
    /**
     * 添加渠道
     */
    @Override
    public String insert(ChannelParamInput channelParam) {
    	ChannelParam Param = BeanCopyConverter.converterClass(channelParam, ChannelParam.class);
    	Param.setCreateDate(new Date());
    	Param.setUpdateDate(new Date());
    	int result=channelParamManager.save(Param);
        return "SUCCESS";
    }
    /**
     * 
    * @Title: update 
    * @Description: 修改渠道
    * @param @param channelParam
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年7月25日 下午7:19:07
    * @throws
     */
    @Override
    public String update(ChannelParamInput channelParam) {
    	ChannelParam Param = BeanCopyConverter.converterClass(channelParam, ChannelParam.class);
    	Param.setUpdateDate(new Date());
    	int result=channelParamManager.update(Param);
        return "SUCCESS";
    }
}
