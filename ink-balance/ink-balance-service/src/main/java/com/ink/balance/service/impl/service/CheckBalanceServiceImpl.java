package com.ink.balance.service.impl.service;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.CheckBalanceInput;
import com.ink.balance.api.model.in.CheckBalanceQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckBalanceOutput;
import com.ink.balance.api.model.out.CheckBalancePageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.ICheckBalanceService;
import com.ink.balance.core.enums.AdjustStatusEnum;
import com.ink.balance.core.enums.CheckBalanceStatusEnum;
import com.ink.balance.core.enums.DetailCheckStatusEnum;
import com.ink.balance.core.enums.DifferenceHandleStatusEnum;
import com.ink.balance.core.manager.*;
import com.ink.balance.core.po.CheckBalance;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.CheckDifference;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.query.CheckBalanceQuery;
import com.ink.balance.core.query.CheckDifferenceQuery;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bo.chen
 * @Description 调账数据dubbo接口
 * @date 2016年4月28日 上午11:12:20
 */
@Service("checkBalanceService")
public class CheckBalanceServiceImpl implements ICheckBalanceService {

    YinkerLogger log = YinkerLogger.getLogger(CheckBalanceServiceImpl.class);

    @Autowired
    @Qualifier("checkBalanceManager")
    private ICheckBalanceManager checkBalanceManager;
    @Autowired
    private ICheckDifferenceManager checkDifferenceManager;
    @Autowired
    private IChannelDataManager channelDataManager;
    @Autowired
    private IPlatformDataManager platformDataManager;
    @Autowired
    private ICheckChannelManager checkChannelManager;

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    @Override
    public CommonOutput<Object> pageQuery(CheckBalanceQueryParamInput param, PageParamInput pageParam) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "调账信息查询参数:" + param.toString(), null);

        CheckBalanceQuery query = BeanCopyConverter.converterClass(param, CheckBalanceQuery.class);
        query.setPageNumber(pageParam.getPageNum());
        query.setPageSize(pageParam.getNumPerPage());
        Page<CheckBalance> page;
        try {
            page = checkBalanceManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "调账信息查询:数据库查询异常:" + param.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        CheckBalancePageOutput<CheckBalanceOutput> pageOutput = new CheckBalancePageOutput<>();
        if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
            pageOutput.setTotal(page.getTotalCount());
            List<CheckBalance> result = page.getResult();
            List<CheckBalanceOutput> list = (List<CheckBalanceOutput>) BeanCopyConverter
                    .converterClass(result, CheckBalanceOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<Object>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }
    /**
     * 条件查询
     * @param param
     * @return
     */
    public List<CheckBalanceOutput> findList(CheckBalanceQueryParamInput param) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "调账信息查询参数:" + param.toString(), null);
        CheckBalanceQuery query = BeanCopyConverter.converterClass(param, CheckBalanceQuery.class);
        List<CheckBalance> list;
        List<CheckBalanceOutput> lists=new ArrayList<CheckBalanceOutput>();
        try {
        	list = checkBalanceManager.find(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "调账信息查询:数据库查询异常:" + param.toString(), e, null);
            return null;
        }
        if (CollectionUtils.isNotEmpty(list)) {
            lists = (List<CheckBalanceOutput>) BeanCopyConverter.converterClass(list, CheckBalanceOutput.class);
        }
        return lists;
    }
    /**
     * @param id
     * @return CommonOutput<CheckBalanceOutput>
     * @Description 获取信息详情
     * @author bo.chen
     * @date 2016年4月28日 下午5:17:49
     */
    @Override
    public CommonOutput<CheckBalanceOutput> getDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "调账信息明细查询 id :" + id, null);
        CheckBalance checkBalance;
        try {
            checkBalance = checkBalanceManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHANNEL_QUERY_BUS, "调账信息明细查询:数据库查询异常:" + id, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == checkBalance) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        CheckBalanceOutput output = BeanCopyConverter.converterClass(checkBalance, CheckBalanceOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }


    /**
     * 调账数据符合调账要求，依次做以下操作（一个事务）；
     * 1、插入调账记录
     * 2、修改差异记录
     * 3、修改原始数据
     * 4、修改对账总表信息
     */
    @Transactional
    @Override
    public int saveBalanceBatch(CheckBalanceInput checkBalanceApi) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "差异处理后的批量保存"+"checkBalanceApi：" + checkBalanceApi.toString(), null);
        CheckBalance checkBalance = BeanCopyConverter.converterClass(checkBalanceApi, CheckBalance.class);
        CheckDifference cd = new CheckDifference();
        cd.setChannelNo(checkBalance.getChannelNo());
        cd.setPlatformOrderNo(checkBalance.getPlatformOrderNo());
        cd.setHandleStatus(DifferenceHandleStatusEnum.UNHANDLE.getCode());//待处理
        CheckDifferenceQuery query = BeanCopyConverter.converterClass(cd, CheckDifferenceQuery.class);
        List<CheckDifference> checkDifferenceList = checkDifferenceManager.find(query);
        //1、插入调账记录
        saveCheckBalance(checkBalance);
        //2、修改差异记录
        updateDifference(checkDifferenceList);
        //3、修改原始数据
        updatePlatformAndChannelStatus(checkDifferenceList);
        //4、修改对账总表信息
        updateCheckChannelStatus(checkDifferenceList);

        return 1;
    }


    /**
     * @param checkBalance
     * @return int
     * @Description 保存调账表信息
     * @author bo.chen
     * @date 2016年5月26日 下午5:17:49
     */
    public int saveCheckBalance(CheckBalance checkBalance) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "保存调账表信息"+"checkBalance：" + checkBalance.toString(), null);
        checkBalance.setBalanceStatus(CheckBalanceStatusEnum.BALANCE_SUCCESS.getCode());
        checkBalance.setCreateTime(new Date());
        checkBalance.setUpdateTime(new Date());
        return checkBalanceManager.save(checkBalance);
    }

    /**
     * @param checkDifferenceList
     * @return int
     * @Description 更改差异表处理状态为已处理
     * @author bo.chen
     * @date 2016年5月26日 上午11:17:30
     */
    public int updateDifference(List<CheckDifference> checkDifferenceList) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改差异表处理状态为已处理开始,"+checkDifferenceList + ";" + checkDifferenceList.toString(), null);
        try {
            for (CheckDifference checkDifference : checkDifferenceList) {
                CheckDifference newCheckDifference = new CheckDifference();
                newCheckDifference.setId(checkDifference.getId());
                newCheckDifference.setHandleStatus(DifferenceHandleStatusEnum.HANDLED.getCode());
                int insNum = checkDifferenceManager.updateByPrimaryKeySelective(newCheckDifference);
                if (insNum != 1) {
                    log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改差异表处理状态为已处理异常，回滚", null, null);
                    throw new RuntimeException("更改差异表处理状态为已处理异常");
                }
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改差异表处理状态为已处理异常，回滚", e, null);
            throw new RuntimeException("更改差异表处理状态为已处理异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改差异表处理状态为已处理完成", null);
        return 1;
    }

    /**
     * @param checkDifferenceList
     * @return int
     * @Description 更改渠道和平台流水对账状态为匹配
     * @author bo.chen
     * @date 2016年5月26日 上午11:17:30
     */
    public int updatePlatformAndChannelStatus(List<CheckDifference> checkDifferenceList) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配开始"+checkDifferenceList + ";" + checkDifferenceList.toString(), null);
        try {
            for (CheckDifference checkDifference : checkDifferenceList) {
                String platformOrderNo = checkDifference.getPlatformOrderNo();
                if (checkDifference.getDifferenceSource() == 1) {//差错来源（1：渠道  2：平台）
                    ChannelDataQuery cdq = new ChannelDataQuery();
                    cdq.setPlatformOrderNo(platformOrderNo);
                    cdq.setCheckStatus(DetailCheckStatusEnum.AMT2MATCH.getCode());
                    int insNum = channelDataManager.updateChannelByPlatformOrderNo(cdq);
                    if (insNum != 1) {
                        log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配异常，回滚", null, null);
                        throw new RuntimeException("更改渠道和平台流水对账状态为匹配异常");
                    }
                } else {
                    PlatformDataQuery pdq = new PlatformDataQuery();
                    pdq.setPlatformOrderNo(platformOrderNo);
                    pdq.setCheckStatus(DetailCheckStatusEnum.AMT2MATCH.getCode());
                    int insNum = platformDataManager.updatePlatformByPlatformOrderNo(pdq);
                    if (insNum != 1) {
                        log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配异常，回滚", null, null);
                        throw new RuntimeException("更改渠道和平台流水对账状态为匹配异常");
                    }
                }
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配异常，回滚", e, null);
            throw new RuntimeException("更改渠道和平台流水对账状态为匹配异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配完成", null);
        return 1;
    }

    /**
     * @param checkDifferenceList
     * @return int
     * @Description 更改主对账信息，待处理差异笔数和处理状态
     * @author bo.chen
     * @date 2016年5月26日 上午11:17:30
     */
    public int updateCheckChannelStatus(List<CheckDifference> checkDifferenceList) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态开始"+checkDifferenceList + ";" + checkDifferenceList.toString(), null);
        try {
            CheckDifference checkDifference = checkDifferenceList.get(0);
            Long refId = checkDifference.getRefMainrecordId();
            CheckChannel cc = checkChannelManager.getById(refId);
            int unHandleCount = cc.getUnhandleCount();
            if (unHandleCount <= 0) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表渠道方未处理笔数已经为0，回滚", null, null);
                throw new RuntimeException("更改总对账表未处理笔数已经为0异常");
            }
            CheckChannel ccParam = new CheckChannel();
            ccParam.setId(refId);
            ccParam.setUnhandleCount(unHandleCount - 1);
            if (unHandleCount == 1) {
                ccParam.setAdjustStatus(AdjustStatusEnum.HANDLED.getCode());
            }
            log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态"+ccParam.toString() + ";" + ccParam.toString(),null );
            int updNum = checkChannelManager.updateByPrimaryKeySelective(ccParam);
            if (updNum != 1) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态异常，回滚", null, null);
                throw new RuntimeException("更改总对账表未处理笔数与处理状态异常");
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态异常，回滚", e, null);
            throw new RuntimeException("更改总对账表未处理笔数与处理状态异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.BALANCE_DIFFERENCE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态完成", null);
        return 1;
    }

    /**
     * @param platformOrderNo
     * @return CommonOutput<CheckBalanceOutput>
     * @Description 获取调账信息明细
     * @author bo.chen
     * @date 2016年5月26日 下午5:17:30
     */
    @Override
    public CommonOutput<CheckBalanceOutput> getBalanceDetails(String platformOrderNo) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "调账信息明细查询 platformOrderNo :" + platformOrderNo, null);
        CheckBalance checkBalance;
        try {
            checkBalance = checkBalanceManager.getBalanceDetails(platformOrderNo);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.PLATFORM_QUERY_BUS, "调账信息明细查询:数据库查询异常:" + platformOrderNo, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == checkBalance) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        CheckBalanceOutput output = BeanCopyConverter.converterClass(checkBalance, CheckBalanceOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }

}
