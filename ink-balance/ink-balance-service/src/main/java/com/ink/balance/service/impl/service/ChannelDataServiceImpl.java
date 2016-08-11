package com.ink.balance.service.impl.service;

import java.util.List;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.ChannelDataQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelDataOutput;
import com.ink.balance.api.model.out.ChannelDataPageOutput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelDataService;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.base.utils.BeanCopyConverter;


/**
 * @author xuguoqi
 * @Description 渠道数据dubbo接口
 * @date 2016年4月11日 上午11:12:20
 */
@Service("channelDataService")
public class ChannelDataServiceImpl implements IChannelDataService {

    YinkerLogger log = YinkerLogger.getLogger(ChannelDataServiceImpl.class);

    @Autowired
    @Qualifier("channelDataManager")
    private IChannelDataManager channelDataManager;



    /**
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<ChannelDataOutput>>
     * @Description 分页查询
     * @author xuguoqi
     * @date 2016年4月11日 上午11:10:58
     */
    @Override
    public CommonOutput<CheckCommonPageOutput<ChannelDataOutput>> pageQuery(PageParamInput paramInput) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询" + paramInput.toString(), null);

        ChannelDataQuery query = new ChannelDataQuery();
        query.setPageSize(paramInput.getNumPerPage());
        query.setPageNumber(paramInput.getPageNum());
        Page<ChannelData> page;
        try {
            page = channelDataManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询:数据库查询异常" + paramInput.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        CheckCommonPageOutput<ChannelDataOutput> pageOutput = new CheckCommonPageOutput<>();
        if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
            pageOutput.setTotail(page.getTotalCount());
            List<ChannelData> result = page.getResult();
            List<ChannelDataOutput> list = (List<ChannelDataOutput>) BeanCopyConverter.converterClass(result, ChannelDataOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    @Override
    public CommonOutput<Object> pageQuery(ChannelDataQueryParamInput param, PageParamInput pageParam) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询参数:" + param.toString(), null);
        ChannelDataQuery query = BeanCopyConverter.converterClass(param, ChannelDataQuery.class);
        query.setPageNumber(pageParam.getPageNum());
        query.setPageSize(pageParam.getNumPerPage());
        Page<ChannelData> page;
        try {
            page = channelDataManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息查询:数据库查询异常:" + param.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        ChannelDataPageOutput<ChannelDataOutput> pageOutput = new ChannelDataPageOutput<ChannelDataOutput>();
        if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
            pageOutput.setTotal(page.getTotalCount());
            List<ChannelData> result = page.getResult();
            List<ChannelDataOutput> list = (List<ChannelDataOutput>) BeanCopyConverter
                    .converterClass(result, ChannelDataOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<Object>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }

    /**
     * @param id
     * @return CommonOutput<ChannelDataOutput>
     * @Description 获取渠道信息详情
     * @author bo.chen
     * @date 2016年4月19日 下午5:17:49
     */
    @Override
    public CommonOutput<ChannelDataOutput> getDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息明细查询 id :" + id, null);
        log.debug("渠道信息明细查询");
        ChannelData channelData;
        try {
            channelData = channelDataManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "渠道信息明细查询:数据库查询异常:" + id, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == channelData) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        ChannelDataOutput output = BeanCopyConverter.converterClass(channelData, ChannelDataOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }



}
