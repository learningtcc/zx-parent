package com.ink.balance.service.impl.service;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.CheckDifferenceInput;
import com.ink.balance.api.model.in.DifferenceParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CheckDifferenceOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.ICheckDifferenceService;
import com.ink.balance.core.enums.AdjustStatusEnum;
import com.ink.balance.core.enums.DetailCheckStatusEnum;
import com.ink.balance.core.enums.DifferenceHandleStatusEnum;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.manager.ICheckChannelManager;
import com.ink.balance.core.manager.ICheckDifferenceManager;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.CheckDifference;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.query.CheckDifferenceQuery;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuguoqi
 * @Description 差异数据dubbo接口
 * @date 2016年4月11日 上午9:54:42
 */
@Service("checkDifferenceService")
public class CheckDifferenceServiceImpl implements ICheckDifferenceService {

    YinkerLogger log = YinkerLogger.getLogger(CheckDifferenceServiceImpl.class);

    @Autowired
    private ICheckDifferenceManager checkDifferenceManager;
    @Autowired
    private IChannelDataManager channelDataManager;
    @Autowired
    private IPlatformDataManager platformDataManager;
    @Autowired
    private ICheckChannelManager checkChannelManager;

    /**
     * @param diffParam
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>>
     * @Description 差异分页查询
     * @author xuguoqi
     * @date 2016年4月18日 下午5:15:31
     */
    @Override
    public CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>> pageQuery(DifferenceParamInput diffParam, PageParamInput paramInput) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息查询参数:" + diffParam.toString(), null);
        //分页参数和条件转换bean
        CheckDifferenceQuery query = BeanCopyConverter.converterClass(diffParam, CheckDifferenceQuery.class);
        query.setPageSize(paramInput.getNumPerPage());
        query.setPageNumber(paramInput.getPageNum());
        Page<CheckDifference> page;
        try {
            page = checkDifferenceManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息查询:数据库查询异常:" + diffParam.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        CheckCommonPageOutput<CheckDifferenceOutput> pageOutput = new CheckCommonPageOutput<>();
        pageOutput.setTotail(page.getTotalCount());
        if (CollectionUtils.isNotEmpty(page.getResult())) {
            List<CheckDifferenceOutput> list = (List<CheckDifferenceOutput>) BeanCopyConverter.converterClass(page.getResult(), CheckDifferenceOutput.class);
            pageOutput.setList(list);
        }
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, pageOutput);
    }

    /**
     * @param id
     * @return CommonOutput<CheckDifferenceOutput>
     * @Description 获取差异信息详情
     * @author xuguoqi
     * @date 2016年4月18日 下午5:17:49
     */
    @Override
    public CommonOutput<CheckDifferenceOutput> getDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息明细查询 id :" + id, null);
        CheckDifference checkDifference;
        try {
            checkDifference = checkDifferenceManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息明细查询:数据库查询异常:" + id, e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        if (null == checkDifference) {
            return new CommonOutput<>(BalanceCodeUtils.RESULT_IS_NULL, BalanceCodeUtils.RESULT_IS_NULL_MSG, null);
        }
        CheckDifferenceOutput output = BeanCopyConverter.converterClass(checkDifference, CheckDifferenceOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, output);
    }

    /**
     * @param pid
     * @param cid
     * @return int
     * @Description 处理双单边都进入差异表的直接勾兑为匹配
     * @author bo.chen
     * @date 2016年5月12日 上午11:17:30
     */
    @Transactional
    @Override
    public int handle2oneSideToMatch(Long pid, Long cid) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "双单边匹配勾兑处理,"+"pid=" + pid.toString() + ";cid=" + cid, null);
        //平台单边差异记录
        CheckDifference cdp = checkDifferenceManager.selectByPrimaryKey(pid);
        //渠道单边差异记录
        CheckDifference cdc = checkDifferenceManager.selectByPrimaryKey(cid);
        String platformOrderNop = cdp.getPlatformOrderNo();
        String platformOrderNoc = cdc.getPlatformOrderNo();
        Long checkChannelIdP = cdp.getRefMainrecordId();
        Long checkChannelIdC = cdc.getRefMainrecordId();
        int statusp = cdp.getStatus();
        int statusc = cdc.getStatus();
        int typep = cdp.getDifferenceType();
        int typec = cdc.getDifferenceType();
        BigDecimal amountp = cdp.getAmount();
        BigDecimal amountc = cdc.getAmount();
        //检查两个单边情况是否满足勾兑条件
        if (!(platformOrderNop.equals(platformOrderNoc) && statusp == statusc && typep == 2 && typec == 1 && amountp.compareTo(amountc) == 0)) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS,
                    "不符合双单边匹配勾兑条件，无法处理,"+"pid=" + pid.toString() + ";cid=" + cid, null,null);
            return 0;
        }
        //更改差异表处理状态为已处理
        updateDifferenceStatus(pid, cid);
        //更改渠道和平台流水对账状态为匹配
        updatePlatformAndChannelStatus(platformOrderNoc, platformOrderNop);
        //更改主对账信息，待处理差异笔数和处理状态
        updateCheckChannelStatus(checkChannelIdP, checkChannelIdC);
        return 1;
    }

    /**
     * @param platformOrderNo
     * @return List<CheckDifferenceInput>
     * @Description 获取差异信息List
     * @author bo.chen
     * @date 2016年5月19日 下午5:17:49
     */
    @Override
    public List<CheckDifferenceInput> getDetailsByPlatformOrderNo(String platformOrderNo) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息明细List查询 platformOrderNo :" + platformOrderNo, null);
        List<CheckDifference> checkDifferenceList = new ArrayList<>();
        List<CheckDifferenceInput> checkDifferenceApiList = new ArrayList<>();
        try {
            checkDifferenceList = checkDifferenceManager.selectByPlatformOrderNo(platformOrderNo);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息明细List查询:数据库查询异常platformOrderNo:" + platformOrderNo, e, null);
        }
        for (CheckDifference checkDifference : checkDifferenceList) {
            CheckDifferenceInput checkDifferenceApi = BeanCopyConverter.converterClass(checkDifference, CheckDifferenceInput.class);
            checkDifferenceApiList.add(checkDifferenceApi);
        }
        return checkDifferenceApiList;
    }
    /**
     * @param checkDifferenceApi
     * @return List<CheckDifferenceInput>
     * @Description 根据对象获取对象list
     * @author bo.chen
     * @date 2016年5月25日 上午11:17:30
     */
    @Override
    public List<CheckDifferenceInput> getDifferenceListByParams(CheckDifferenceInput checkDifferenceApi) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_DIFFERENCE_QUERY_BUS, "差异信息List查询 platformOrderNo :" + checkDifferenceApi.toString(), null);
        List<CheckDifferenceInput> checkDifferenceApiList = new ArrayList<>();
        CheckDifferenceQuery query = BeanCopyConverter.converterClass(checkDifferenceApi, CheckDifferenceQuery.class);
        List<CheckDifference> checkDifferenceList = checkDifferenceManager.find(query);
        for (CheckDifference checkDifference : checkDifferenceList) {
            CheckDifferenceInput differenceApi = BeanCopyConverter.converterClass(checkDifference, CheckDifferenceInput.class);
            checkDifferenceApiList.add(differenceApi);
        }
        return checkDifferenceApiList;
    }

    /**
     * @param id
     * @return int
     * @Description 处理单边失败交易为挂起
     * @author bo.chen
     * @date 2016年5月30日 上午11:17:30
     */
    @Override
    public int handle2hangUp(Long id) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "处理单边失败交易为挂起", id + "=" + id);
        try {
            CheckDifference cd = new CheckDifference();
            cd.setId(id);
            cd.setHandleStatus(DifferenceHandleStatusEnum.HANGUP.getCode());
            int cdEff = checkDifferenceManager.updateByPrimaryKeySelective(cd);
            if (cdEff != 1) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "处理单边失败交易为挂起异常", null, null);
                return 0;
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "处理单边失败交易为挂起异常", e, null);
            throw new RuntimeException("处理单边失败交易为挂起异常异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "处理单边失败交易为挂起异常已处理完成", null);
        return 1;
    }

    /**
     * @param pid
     * @param cid
     * @return int
     * @Description 更改差异表处理状态为已处理
     * @author bo.chen
     * @date 2016年5月12日 上午11:17:30
     */
    public int updateDifferenceStatus(Long pid, Long cid) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改差异表处理状态为已处理开始", pid + "=" + cid);
        try {
            CheckDifference pcd = new CheckDifference();
            CheckDifference ccd = new CheckDifference();
            pcd.setId(pid);
            pcd.setHandleStatus(DifferenceHandleStatusEnum.HANDLED.getCode());
            ccd.setId(cid);
            ccd.setHandleStatus(DifferenceHandleStatusEnum.HANDLED.getCode());
            int pcdEff = checkDifferenceManager.updateByPrimaryKeySelective(pcd);
            int ccdEff = checkDifferenceManager.updateByPrimaryKeySelective(ccd);
            if (pcdEff != 1 || ccdEff != 1) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改差异表处理状态为已处理异常，回滚", null, null);
                throw new RuntimeException("更改差异表处理状态为已处理异常");
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改差异表处理状态为已处理异常，回滚", e, null);
            throw new RuntimeException("更改差异表处理状态为已处理异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改差异表处理状态为已处理完成", null);
        return 1;
    }

    /**
     * @param platformOrderNoc
     * @param platformOrderNop
     * @return int
     * @Description 更改渠道和平台流水对账状态为匹配
     * @author bo.chen
     * @date 2016年5月12日 上午11:17:30
     */
    public int updatePlatformAndChannelStatus(String platformOrderNoc, String platformOrderNop) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配开始", platformOrderNoc + "=" + platformOrderNop);
        try {
            ChannelDataQuery cdq = new ChannelDataQuery();
            PlatformDataQuery pdq = new PlatformDataQuery();
            cdq.setPlatformOrderNo(platformOrderNoc);
            cdq.setCheckStatus(DetailCheckStatusEnum.MATCH.getCode());
            pdq.setPlatformOrderNo(platformOrderNop);
            pdq.setCheckStatus(DetailCheckStatusEnum.MATCH.getCode());
            int cdqEff = channelDataManager.updateChannelByPlatformOrderNo(cdq);
            int pdqEff = platformDataManager.updatePlatformByPlatformOrderNo(pdq);
            if (cdqEff != 1 || pdqEff != 1) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配异常，回滚", null, null);
                throw new RuntimeException("更改渠道和平台流水对账状态为匹配异常");
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配异常，回滚", e, null);
            throw new RuntimeException("更改渠道和平台流水对账状态为匹配异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改渠道和平台流水对账状态为匹配完成", null);
        return 1;
    }
    /**
     * @param checkChannelIdP
     * @param checkChannelIdC
     * @return int
     * @Description 更改主对账信息，待处理差异笔数和处理状态
     * @author bo.chen
     * @date 2016年5月12日 上午11:17:30
     */
    public int updateCheckChannelStatus(Long checkChannelIdP, Long checkChannelIdC) {
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态开始", checkChannelIdP + "=" + checkChannelIdC);
        try {
            CheckChannel ccp = checkChannelManager.getById(checkChannelIdP);
            CheckChannel ccc = checkChannelManager.getById(checkChannelIdC);
            int unumP = ccp.getUnhandleCount();
            int unumC = ccc.getUnhandleCount();
            if (unumP <= 0) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表渠道方未处理笔数已经为0，回滚", null, null);
                throw new RuntimeException("更改总对账表渠道方未处理笔数已经为0异常");
            }
            if (unumC <= 0) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表平台方未处理笔数已经为0，回滚", null, null);
                throw new RuntimeException("更改总对账表平台方未处理笔数已经为0异常");
            }
            CheckChannel ccpParam = new CheckChannel();
            CheckChannel cccParam = new CheckChannel();
            ccpParam.setId(checkChannelIdP);
            cccParam.setId(checkChannelIdC);
            ccpParam.setUnhandleCount(unumP - 1);
            cccParam.setUnhandleCount(unumC - 1);
            if (unumP == 1) {
                ccpParam.setAdjustStatus(AdjustStatusEnum.HANDLED.getCode());
            }
            if (unumC == 1) {
                cccParam.setAdjustStatus(AdjustStatusEnum.HANDLED.getCode());
            }
            log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态,"+ccpParam.toString() + ";" + cccParam.toString(), null);
            int ccpEff = checkChannelManager.updateByPrimaryKeySelective(ccpParam);
            int cccEff = checkChannelManager.updateByPrimaryKeySelective(cccParam);
            if (ccpEff != 1 || cccEff != 1) {
                log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态异常，回滚", null, null);
                throw new RuntimeException("更改总对账表未处理笔数与处理状态异常");
            }
        } catch (Exception e) {
            log.error(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态异常，回滚", e, null);
            throw new RuntimeException("更改总对账表未处理笔数与处理状态异常");
        }
        log.info(LoggerCnst.DIFFERENCE_HANDLE, LoggerCnst.TWO_SIDE_HANDLE_BUS, "更改总对账表未处理笔数与处理状态完成", null);
        return 1;
    }

}
