package com.ink.balance.service.impl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ink.balance.api.constants.BalanceCodeUtils;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.api.model.in.CheckQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CheckPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.ICheckMainService;
import com.ink.balance.core.manager.IBalanceDataManager;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.manager.ICheckChannelManager;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.query.CheckChannelQuery;
import com.ink.balance.service.job.BatchJob;
import com.ink.base.page.Page;
import com.ink.base.log.util.YinkerLogger;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.base.utils.BeanCopyConverter;
import com.ink.util.JobExecutorUtils;

/**
 * @author xuguoqi
 * @Description 主对账dubbo接口
 * @date 2016年4月7日 下午2:32:14
 */
@Service("checkMainService")
public class CheckMainServiceImpl implements ICheckMainService {


    YinkerLogger log = YinkerLogger.getLogger(CheckMainServiceImpl.class);

    @Qualifier(value = "checkChannelManager")
    @Autowired
    private ICheckChannelManager checkChannelManager;

    @Autowired
    private IBalanceDataManager balanceDataManager;
    
    @Autowired
    private IChannelDataManager channelDataManager;

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author xuguoqi
     */
    @Override
    public CommonOutput<Object> pageQuery(CheckQueryParamInput param, PageParamInput pageParam) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "主对账信息查询参数:" + param.toString(), null);
        CheckChannelQuery query = BeanCopyConverter.converterClass(param, CheckChannelQuery.class);
        query.setPageNumber(pageParam.getPageNum());
        query.setPageSize(pageParam.getNumPerPage());
        Page<CheckChannel> page;
        try {
            page = checkChannelManager.findPage(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "主对账信息查询:数据库查询异常:" + param.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        List<CheckChannelOutput> list = (List<CheckChannelOutput>) BeanCopyConverter.converterClass(page.getResult(), CheckChannelOutput.class);
        CheckPageOutput checkPage = new CheckPageOutput(page.getTotalCount(), list);
        return new CommonOutput<Object>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, checkPage);
    }

    /**
     * 条件查询
     *
     * @param param
     * @return
     */
    public List<CheckChannelOutput> findList(CheckQueryParamInput param) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "主对账信息查询参数:" + param.toString(), null);
        CheckChannelQuery query = BeanCopyConverter.converterClass(param, CheckChannelQuery.class);
        List<CheckChannel> list;
        List<CheckChannelOutput> lists = new ArrayList<CheckChannelOutput>();
        try {
            list = checkChannelManager.find(query);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "主对账信息查询:数据库查询异常:" + param.toString(), e, null);
            return null;
        }
        if (CollectionUtils.isNotEmpty(list)) {
            lists = (List<CheckChannelOutput>) BeanCopyConverter.converterClass(list, CheckChannelOutput.class);
        }
        return lists;
    }

    /**
     * @param checkChannel
     * @return String
     * @Description 执行手工对账的dubbo服务
     * @author bo.chen
     * @date 2016年6月17日 下午5:34:52
     */
    @Override
    public String balanceData(CheckChannelParamInput checkChannel) {
        log.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "dubbo服务执行手工对账:" + checkChannel.toString(), null);
        String ret;
        try {
            ret = balanceDataManager.balanceData(checkChannel);
            return ret;
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "dubbo服务执行手工对账异常:" + e.getMessage(), e, null);
            return "ERROR";
        }
    }
    /**
     * 
    * @Title: readChannelData 
    * @Description: 读取渠道数据文件
    * @param @param checkChannel
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年6月21日 下午3:58:09
    * @throws
     */
    @Override
    public String readChannelData(CheckChannelParamInput checkChannel) {
        log.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "dubbo服务执行对账入库:" + checkChannel.toString(), null);
        
        try {
        	if(checkChannel.getChannelNo().equals(SysParamConst.YKLM_CHANNEL_NO)){
        		channelDataManager.readBatchYinkerData(checkChannel);
        	}else if(checkChannel.getChannelNo().equals(SysParamConst.CMBC_CHANNEL_NO)){
        		channelDataManager.readBatchChannelData(checkChannel);
        	}else if(checkChannel.getChannelNo().equals(SysParamConst.BOOFOO_CHANNEL_NO)){
        		channelDataManager.readBatchBooChannelData(checkChannel);
        	}
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.EXE_BALANCE_BUS, "dubbo服务执行对账入库异常:" + e.getMessage(), e, null);
            return "ERROR";
        }
        return "SUCCESS";
    }
    /**
     * @param ids
     * @param remark
     * @return CommonOutput<Object>
     * @Description 批量修改对账状态
     * @author xuguoqi
     * @date 2016年4月6日 下午5:34:52
     */
    @Override
    public CommonOutput<Object> updateBatchStatus(List<Long> ids, String remark) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "批量修改对账状态参数 ids:" + ids.toString(), null);
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                for (Long id : ids) {
                    CheckChannel entity = new CheckChannel();
                    entity.setId(id);
                    entity.setRemark(remark);
                    checkChannelManager.update(entity);
                }
            }
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "总对账-批量修改对账状态异常 ids=" + ids.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, null);
    }

    /**
     * @param id
     * @return CommonOutput<CheckChannelOutput>
     * @Description 获取对账详细信息
     * @author xuguoqi
     * @date 2016年4月6日 下午5:37:24
     */
    @Override
    public CommonOutput<CheckChannelOutput> getCheckDetails(Long id) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取总对账详情参数 id:" + id.toString(), null);
        CheckChannel checkChannel;
        try {
            checkChannel = checkChannelManager.getById(id);
        } catch (Exception e) {
            log.error(LoggerCnst.BALANCE_QUERY, LoggerCnst.CHECK_MAIN_QUERY_BUS, "获取总对账详情异常 id=" + id.toString(), e, null);
            return new CommonOutput<>(BalanceCodeUtils.DABASE_ERROR_CODE, BalanceCodeUtils.DABASE_ERROR_MSG, null);
        }
        CheckChannelOutput checkChannelOutput = BeanCopyConverter.converterClass(checkChannel, CheckChannelOutput.class);
        return new CommonOutput<>(BalanceCodeUtils.SUCCESS, BalanceCodeUtils.SUCCESS_MSG, checkChannelOutput);
    }

}
