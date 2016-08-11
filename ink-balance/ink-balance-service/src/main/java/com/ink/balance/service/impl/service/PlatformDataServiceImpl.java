package com.ink.balance.service.impl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.ChannelDataQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.in.PlatformDataMqParamInput;
import com.ink.balance.api.model.in.PlatformDataQueryParamInput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;
import com.ink.balance.api.model.out.PlatformDataPageOutput;
import com.ink.balance.api.service.IPlatformDataService;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.base.utils.BeanCopyConverter;

import javax.annotation.Resource;

/**
 * @author xuguoqi
 * @Description 平台数据dubbo接口
 * @date 2016年4月11日 下午2:03:41
 */
@Service("platformDataService")
public class PlatformDataServiceImpl implements IPlatformDataService {
    YinkerLogger log = YinkerLogger.getLogger(PlatformDataServiceImpl.class);

    @Autowired
    @Qualifier("platformDataManager")
    private IPlatformDataManager platformDataManager;

    @Resource
    AmqpTemplate amqpTemplate;

    /**
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<PlatformDataOutput>>
     * @Description 分页查询
     * @author xuguoqi
     * @date 2016年4月11日 下午2:01:13
     */
    @Override
    public CommonOutput<CheckCommonPageOutput<PlatformDataOutput>> pageQuery(PageParamInput paramInput) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "平台信息查询参数:" + paramInput.toString(), null);
        PlatformDataQuery query = new PlatformDataQuery();
        query.setPageSize(paramInput.getNumPerPage());
        query.setPageNumber(paramInput.getPageNum());
        Page<PlatformData> findPage;
        try {
            findPage = platformDataManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "平台信息查询参数:数据库查询异常:" + paramInput.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        CheckCommonPageOutput<PlatformDataOutput> page = new CheckCommonPageOutput<>();
        if (findPage != null && CollectionUtils.isNotEmpty(findPage.getResult())) {
            List<PlatformDataOutput> list = (List<PlatformDataOutput>) BeanCopyConverter.converterClass(findPage.getResult(), PlatformDataOutput.class);
            page.setTotail(findPage.getTotalCount());
            page.setList(list);
        }
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, page);
    }

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    @Override
    public CommonOutput<Object> pageQuery(PlatformDataQueryParamInput param,
                                          PageParamInput pageParam) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS,
                "平台信息查询参数:" + param.toString(), null);
        PlatformDataQuery query = BeanCopyConverter.converterClass(param,
                PlatformDataQuery.class);
        query.setPageNumber(pageParam.getPageNum());
        query.setPageSize(pageParam.getNumPerPage());
        Page<PlatformData> page;
        try {
            page = platformDataManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS,
                    "平台信息查询:数据库查询异常:" + param.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        PlatformDataPageOutput<PlatformDataOutput> pageOutput = new PlatformDataPageOutput<PlatformDataOutput>();
        if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
            pageOutput.setTotal(page.getTotalCount());
            List<PlatformData> result = page.getResult();
            List<PlatformDataOutput> list = (List<PlatformDataOutput>) BeanCopyConverter
                    .converterClass(result, PlatformDataOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<Object>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }

    /**
     * @param id
     * @return CommonOutput<PlatformDataOutput>
     * @Description 获取平台信息详情
     * @author bo.chen
     * @date 2016年4月19日 下午5:17:49
     */
    @Override
    public CommonOutput<PlatformDataOutput> getDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "平台信息明细查询 id :" + id, null);
        PlatformData platformData;
        try {
            platformData = platformDataManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "平台信息明细查询:数据库查询异常:" + id, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == platformData) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        PlatformDataOutput output = BeanCopyConverter.converterClass(platformData, PlatformDataOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }
    /**
     * 条件查询
     * @param param
     * @return
     */
    public List<PlatformDataOutput> findList(PlatformDataQueryParamInput param) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "平台交易信息查询参数:" + param.toString(), null);
        PlatformDataQuery query = BeanCopyConverter.converterClass(param,PlatformDataQuery.class);
        List<PlatformData> list;
        List<PlatformDataOutput> lists=new ArrayList<PlatformDataOutput>();
        try {
        	list = platformDataManager.find(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "平台交易信息查询:数据库查询异常:" + param.toString(), e, null);
            return null;
        }
        if (CollectionUtils.isNotEmpty(list)) {
            lists = (List<PlatformDataOutput>) BeanCopyConverter.converterClass(list, PlatformDataOutput.class);
        }
        return lists;
    }

    @Override
    public String insertMq(PlatformDataMqParamInput param) {
        Map<String, Object> params = new HashMap<String, Object>();

        // 交易状态 1、待支付 2、支付成功 3、支付失败
        params.put("amt",param.getAmt());
        params.put("platformOrderNo", param.getPlatformOrderNo());
        params.put("payTime", param.getPayTime());
        params.put("payStatus", param.getPayStatus());

        amqpTemplate.convertAndSend("yinker.balance.platform.notifyData", params);

        return "SUCCESS";
    }

    @Override
    public String updateMq(PlatformDataMqParamInput param) {
        Map<String, Object> params = new HashMap<String, Object>();

        // 交易状态 1、待支付 2、支付成功 3、支付失败
        params.put("platformOrderNo", param.getPlatformOrderNo());
        params.put("arrivedAmt", param.getArrivedAmt());
        params.put("channelNo", param.getChannelNo());
        params.put("arrivedTime", param.getArrivedTime());
        //params.put("transNo", "");
        params.put("payStatus", param.getPayStatus());
        params.put("payMethod", param.getPayMethod());
        params.put("channelMerchantNo", param.getChannelMerchantNo());

        amqpTemplate.convertAndSend("yinker.balance.platform.updateData", params);
        return "SUCCESS";
    }
}
