package com.ink.balance.core.manager.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ink.balance.core.enums.*;
import com.ink.balance.core.manager.IBalanceDataManager;
import com.ink.balance.core.manager.IChannelDataManager;
import com.ink.balance.core.manager.IChannelParamManager;
import com.ink.balance.core.manager.ICheckChannelManager;
import com.ink.balance.core.manager.ICheckDifferenceManager;
import com.ink.balance.core.manager.IPlatformDataManager;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.CheckDifference;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.balance.core.ret.Result;
import com.ink.balance.core.util.Md5Util;
import com.ink.balance.core.util.VeDate;
import com.ink.balance.api.constants.CheckChannelStatus;
import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.constants.PayStatusConst;
import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.base.log.util.YinkerLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redis.BaseRedis;

import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;


/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午8:19:29
 * @description 描述：真正的对账功能实现类
 */
@Repository("balanceDataManager")
@Transactional
public class BalanceDataManagerImpl implements IBalanceDataManager {

    YinkerLogger loger = YinkerLogger.getLogger(BalanceDataManagerImpl.class);

    // 差集Set
    private String resultChannel = "SDIFF_CHANNEL_DATA";

    // mini差集Set
    private String miniResultChannel = "MINI_SDIFF_CHANNEL_DATA";

    // 差集的差集（正向）
    private String sdiffSdiffChannel = "SDIFF_SDIFF_CHANNEL_DATA";

    // 差错集合
    private String errorDataChannel = "ERROR_CHANNEL_DATA";

    // 反向差集Set
    private String reverseResultChannel = "REVERSE_SDIFF_CHANNEL_DATA";

    // 反向mini差集Set
    private String reverseMiniResultChannel = "REVERSE_MINI_SDIFF_CHANNEL_DATA";

    // 渠道数据set集合
    private String channelSet = "CHANNEL_SET_DATA";

    // 平台数据set集合
    private String platformSet = "PLATFORM_SET_DATA";

    // 平台数据set集合
    private String platformNoSet = "PLATFORM_NO_SET_DATA";

    // 平台数据set集合
    private String matchSet = "MATCH_SET_DATA";

    // 渠道单边集合
    private String channelSideData = "CHANNEL_SIDE_DATA";

    // 平台单边集合
    private String platformSideData = "PLATFORM_SIDE_DATA";

    @Autowired
    private IChannelDataManager channelDataManager;

    @Autowired
    private IPlatformDataManager platformDataManager;

    @Autowired
    private ICheckChannelManager checkChannelManager;

    @Autowired
    private ICheckDifferenceManager checkDifferenceManager;

    @Autowired
    private IChannelParamManager channelParamManager;

    @Autowired
    private BaseRedis baseRedis;


    /**
     * （1）
     *
     * @return String
     * @Description core项目对账入口，此方法直接被service的http请求调用
     * @author bo.chen
     * @date 2016年4月18日 下午5:16:44
     */
    @Override
    public String balanceData(CheckChannelParamInput checkChannelParam) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "http请求后进入core的balanceData（）对账算法，参数："+checkChannelParam.toString(), null);
        /**
         * 对账前数据准备
         */
        try {
            //设置对账参数
            CheckChannel cc = new CheckChannel();
            if (checkChannelParam == null) {
                loger.error(LoggerCnst.BALANCE_MOUDLE,
                        LoggerCnst.EXE_BALANCE_BUS, "执行balanceData（）失败 checkChannelParam为null:",
                        null, null);
            } else {
                cc.setChannelNo(checkChannelParam.getChannelNo());
                cc.setTradeDate(checkChannelParam.getTradeDate());
                cc.setCheckDate(checkChannelParam.getCheckDate());
                cc.setChannelMerchantNo(checkChannelParam.getChannelMerchantNo());
            }

            /**
             * 执行对账方法
             */
            executeBalanceData(cc);

        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE,
                    LoggerCnst.EXE_BALANCE_BUS, "执行balanceData（）失败 返回 ERROR:" + e.getMessage(),
                    e, null);
            return "ERROR";
        }
        return "SUCCESS";

    }


    /**
     * （2）
     * 获取双方对账数据并调用真正的对账方法
     */
    @Override
    public void executeBalanceData(CheckChannel cc) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "进入对账功能算法 CheckChannel_param:" + cc.toString(), null);
        String channelNo = cc.getChannelNo();//获取渠道编号
        String channelMerchantNo = cc.getChannelMerchantNo();//获取渠道商户号
        Date tradeDate = cc.getTradeDate();//获取交易时间
        //获取渠道对账数据
        ChannelDataQuery channelQuery = new ChannelDataQuery();
        channelQuery.setChannelNo(channelNo);
        channelQuery.setMerchantNo(channelMerchantNo);
        channelQuery.setTransTime(tradeDate);
        channelQuery.setResideFlag(ResideFlagEnum.RESIDE.getCode());
        channelQuery.setCheckStatus(CheckStatusEnum.INIT.getCode());
        channelQuery.setTransStatus(Integer.parseInt(PayStatusConst.PAY_SUCCESS));//只对成功的交易
        List<ChannelData> channelDataList = channelDataManager
                .getChannelBalanceList(channelQuery);
        //获取渠道对账数据
        PlatformDataQuery platformQuery = new PlatformDataQuery();
        platformQuery.setChannelNo(channelNo);
        platformQuery.setChannelMerchantNo(channelMerchantNo);
        platformQuery.setArrivedTime(tradeDate);
        platformQuery.setResideFlag(ResideFlagEnum.RESIDE.getCode());
        platformQuery.setCheckStatus(CheckStatusEnum.INIT.getCode());
        platformQuery.setPayStatus(Integer.parseInt(PayStatusConst.PAY_SUCCESS));//只对成功的交易
        List<PlatformData> platformDataList = platformDataManager
                .getPlatformBalanceList(platformQuery);
        // 清除redis缓存，防止历史数据造成干扰
        delRedisKey(channelNo, channelMerchantNo);
        /**
         * 将双方数据传入，执行真正的对账操作
         */
        Result ret = balanceData(channelDataList, platformDataList, cc);
        // 对账结果处理
        markCheckChannel(ret);
        delRedisKey(channelNo,channelMerchantNo);// 清除redis缓存，防止历史数据造成干扰
        loger.info(LoggerCnst.SET_BALANCE_BUS, LoggerCnst.SET_BALANCE_BUS,
                "对账完成", null);
    }

    /**
     * （3）
     * 实际的对账方法
     */
    @Override
    public Result balanceData(List<ChannelData> channelDataList,
                              List<PlatformData> platformDataList, CheckChannel cc) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "开始实际对账算法...", null);
        cc.setDifferenceCount(0);//初始默认差异笔数为0
        String channelNo = cc.getChannelNo();//获取渠道编号
        String channelMerchantNo = cc.getChannelMerchantNo();//获取渠道商户号
        String channelNoRedisPrefix = channelNo + "_" + channelMerchantNo + "_";//获取渠道编号和渠道商户号
        Result ret = new Result();
        // 设置渠道和平台的对账总笔数和对账总金额
        setCPSumAmtAndCount(cc);
        //约简渠道数据
        List<String> channelList = reduceChannelData(channelDataList);
        //约简平台数据
        List<String> platformList = reducePlatformData(platformDataList);
        //平台订单号list
        List<String> platformNoList = getPlatformDataNoList(platformDataList);
        if (channelList == null || platformList == null || channelList.size() == 0 || platformList.size() == 0) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "约简数据集为空", null,
                    "约简数据集为空：channelList：【" + (channelList != null ? channelList.size() : "null")
                            + "】;platformList:【" + (platformList != null ? platformList.size() : "null") + "】");
            ret.setRetCode(CheckChannelStatus.CHECK_CHANNEL_EMPTY);
            ret.setRetMsg("约简数据集为空：渠道数据集：【" + (channelList != null ? channelList.size() : "null") + "】;平台数据集:【"
                    + (platformList != null ? platformList.size() : "null") + "】");
            ret.setCheckChannel(cc);
            return ret;
        } else {
            try {
                // 向set集合中写入数据
                baseRedis.saddUsePipeline(channelNoRedisPrefix + channelSet, channelList);
                baseRedis.saddUsePipeline(channelNoRedisPrefix + platformSet, platformList);
                baseRedis.saddUsePipeline(channelNoRedisPrefix + platformNoSet, platformNoList);//插入纯平台订单号
                // 求差集
                baseRedis.sdiffStore(channelNoRedisPrefix + resultChannel, channelNoRedisPrefix + channelSet, channelNoRedisPrefix + platformSet);
                // 获取差集set
                Set<String> sdiffDataSet = baseRedis.smembers(channelNoRedisPrefix + resultChannel);
                // 求差集,获取到匹配集合
                baseRedis.sdiffStore(channelNoRedisPrefix + matchSet, channelNoRedisPrefix + channelSet, channelNoRedisPrefix + resultChannel);
                // 获取到匹配的集合set
                Set<String> matchDataSet = baseRedis.smembers(channelNoRedisPrefix + matchSet);
                // 设置匹配笔数
                cc.setMatchCount(matchDataSet.size());
                // 更新两方数据匹配状态
                updateMatchStatusData(matchDataSet);
                // 求反向差集
                baseRedis.sdiffStore(channelNoRedisPrefix + reverseResultChannel, channelNoRedisPrefix + platformSet, channelNoRedisPrefix + channelSet);
                // 获取反向差集set
                Set<String> reverseSdiffDataSet = baseRedis
                        .smembers(channelNoRedisPrefix + reverseResultChannel);
                // 判断是否完全匹配
                if (sdiffDataSet.size() == 0 && reverseSdiffDataSet.size() == 0) {
                    ret.setRetCode(CheckChannelStatus.CHECK_CHANNEL_MATCH);
                    ret.setRetMsg("对账完全匹配");
                    ret.setCheckChannel(cc);
                    return ret;
                }
                // 转化渠道差集set，只保留平台订单号
                List<String> newSdiffDataList = convertSet2List(sdiffDataSet);
                // 转化平台差集set，只保留平台订单号
                List<String> newReverseSdiffDataList = convertSet2List(reverseSdiffDataSet);
                // 向set集合中写入mimi差集数据
                baseRedis.saddUsePipeline(channelNoRedisPrefix + miniResultChannel, newSdiffDataList);
                baseRedis.saddUsePipeline(channelNoRedisPrefix + reverseMiniResultChannel,
                        newReverseSdiffDataList);
                // 求差集的差集
                baseRedis.sdiffStore(channelNoRedisPrefix + sdiffSdiffChannel, channelNoRedisPrefix + miniResultChannel,
                        channelNoRedisPrefix + reverseMiniResultChannel);
                // 求差错集合（平台订单号相等的都是差异数据）
                baseRedis.sdiffStore(channelNoRedisPrefix + errorDataChannel, channelNoRedisPrefix + miniResultChannel,
                        channelNoRedisPrefix + sdiffSdiffChannel);
                Set<String> errorDataSet = baseRedis.smembers(channelNoRedisPrefix + errorDataChannel);
                // 设置差错笔数暂时只是差异笔数非全部
                if(errorDataSet!=null) {
                    cc.setDifferenceCount(errorDataSet.size());
                }
                // 更新数据为差错,并存入差错表
                updateDiffStatusData(errorDataSet, ret);
                // 作差集，求渠道单边，判断是标记驻留，还是进差异
                baseRedis.sdiffStore(channelNoRedisPrefix + channelSideData, channelNoRedisPrefix + miniResultChannel,
                        channelNoRedisPrefix + errorDataChannel);
                Set<String> channelSideDataSet = baseRedis
                        .smembers(channelNoRedisPrefix + channelSideData);
                updateChannelSideStatusData(channelSideDataSet, cc, ret);

                // 作差集，求平台单边，判断是标记驻留，还是进差异
                baseRedis.sdiffStore(channelNoRedisPrefix + platformSideData, channelNoRedisPrefix + reverseMiniResultChannel,
                        channelNoRedisPrefix + errorDataChannel);
                Set<String> platformSideDataSet = baseRedis
                        .smembers(channelNoRedisPrefix + platformSideData);
                updatePlatformSideStatusData(platformSideDataSet, cc, ret);
                //到了这一步确定一定有未匹配的数据
                ret.setRetCode(CheckChannelStatus.CHECK_CHANNEL_UNMATCH);
                ret.setRetMsg("对账完成");
                ret.setCheckChannel(cc);
            } catch (Exception e) {
                delRedisKey(channelNo, channelMerchantNo);//异常清除reids缓存
                loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                        "redis缓存对账发生异常", e, null);
                ret.setRetCode(CheckChannelStatus.CHECK_CHANNEL_EMPTY);
                ret.setRetMsg("redis缓存对账发生异常");
                ret.setCheckChannel(cc);
                return ret;
            }
            return ret;

        }
    }


    /**
     * 约简渠道数据后加入List
     */
    public List<String> reduceChannelData(List<ChannelData> channelDataList) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "开始约简渠道数据:" + channelDataList.toString(), null);
        List<String> list = new ArrayList<String>();
        String platformOrderNo;
        BigDecimal receivedAmt;
        Integer transStatus;
        String concatStr;
        StringBuffer sbM5d;
        try {
            for (ChannelData channelData : channelDataList) {
                sbM5d = new StringBuffer();
                platformOrderNo = channelData.getPlatformOrderNo();
                receivedAmt = channelData.getReceivedAmt();
                transStatus = channelData.getTransStatus();
                if (!(platformOrderNo == null || receivedAmt == null || transStatus == null)) {
                    sbM5d.append(platformOrderNo);
                    sbM5d.append(receivedAmt.toString().trim()
                            + transStatus.toString().trim());
                    concatStr = sbM5d.toString();
                    String md5Str = Md5Util.MD5(concatStr);
                    list.add(platformOrderNo.trim() + ":" + md5Str);
                } else {
                    loger.error(LoggerCnst.BALANCE_MOUDLE,
                            LoggerCnst.REDIS_DATA_BUS, "约简渠道数据异常（基本要素不全）channelData:" + channelData.toString(),
                            null, null);
                }
            }
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                    "约简渠道数据异常", e, null);
            return null;
        }
        return list;

    }

    /**
     * 约简平台数据后加入List
     */
    public List<String> reducePlatformData(List<PlatformData> platformDataList) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "开始约简平台数据", platformDataList.toString());
        List<String> list = new ArrayList<String>();
        String platformOrderNo;
        BigDecimal arrivedAmt;
        Integer payStatus;
        String concatStr;
        StringBuffer sbM5d;
        try {
            for (PlatformData platformData : platformDataList) {
                sbM5d = new StringBuffer();
                platformOrderNo = platformData.getPlatformOrderNo();
                arrivedAmt = platformData.getArrivedAmt();
                payStatus = platformData.getPayStatus();
                if (!(platformOrderNo == null || arrivedAmt == null || payStatus == null)) {
                    sbM5d.append(platformOrderNo.trim());
                    sbM5d.append(arrivedAmt.toString().trim()
                            + payStatus.toString().trim());
                    concatStr = sbM5d.toString();
                    String md5Str = Md5Util.MD5(concatStr);
                    list.add(platformOrderNo.trim() + ":" + md5Str);
                } else {
                    loger.error(LoggerCnst.BALANCE_MOUDLE,
                            LoggerCnst.REDIS_DATA_BUS, "约简平台数据异常（基本要素不全）:" + platformData.toString(),
                            null, null);
                }
            }
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                    "约简平台数据异常", e, null);
            return null;
        }
        return list;
    }

    /**
     * 获取对账的平台订单号list
     */
    public List<String> getPlatformDataNoList(List<PlatformData> platformDataList) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "开始获取对账的平台订单号list", platformDataList.toString());
        List<String> list = new ArrayList<String>();
        String platformOrderNo;
        try {
            for (PlatformData platformData : platformDataList) {
                platformOrderNo = platformData.getPlatformOrderNo();
                if (platformOrderNo != null) {
                    list.add(platformOrderNo.trim());
                } else {
                    loger.error(LoggerCnst.BALANCE_MOUDLE,
                            LoggerCnst.REDIS_DATA_BUS, "获取对账的平台订单号list异常:" + platformData.toString(),
                            null, null);
                }
            }
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                    "获取对账的平台订单号list异常", e, null);
            return null;
        }
        return list;
    }

    /**
     * 更新渠道和平台数据状态为匹配
     */
    public void updateMatchStatusData(Set<String> set) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                "更新渠道和平台数据状态为匹配", null);
        for (String str : set) {
            ChannelDataQuery cdq = new ChannelDataQuery();
            cdq.setCheckStatus(DetailCheckStatusEnum.MATCH.getCode());
            cdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
            cdq.setPlatformOrderNo(str.split(":")[0].trim());
            PlatformDataQuery pdq = new PlatformDataQuery();
            pdq.setCheckStatus(DetailCheckStatusEnum.MATCH.getCode());
            pdq.setPlatformOrderNo(str.split(":")[0].trim());
            pdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
            int channelRetNum = channelDataManager
                    .updateChannelByPlatformOrderNo(cdq);
            int platformRetNum = platformDataManager
                    .updatePlatformByPlatformOrderNo(pdq);

            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "更新渠道和平台数据状态为匹配" + "更新渠道数据条数【" + channelRetNum
                            + "】；更新平台数据条数【" + platformRetNum
                            + "】;PlatformOrderNo:" + cdq.getPlatformOrderNo()
                            + ";状态：" + DetailCheckStatusEnum.MATCH.getCode(), null);
        }

    }

    /**
     * 更新渠道和平台数据状态为差错
     */
    public void updateDiffStatusData(Set<String> set, Result ret) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                "更新渠道和平台数据状态为差错:" + set.toString(), null);
        List<String> cSdiff = new ArrayList<String>();
        List<String> pSdiff = new ArrayList<String>();

        for (String str : set) {
            ChannelDataQuery cdq = new ChannelDataQuery();
            cdq.setCheckStatus(DetailCheckStatusEnum.DIFF.getCode());
            cdq.setPlatformOrderNo(str.trim());
            cdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
            PlatformDataQuery pdq = new PlatformDataQuery();
            pdq.setCheckStatus(DetailCheckStatusEnum.DIFF.getCode());
            pdq.setPlatformOrderNo(str.trim());
            pdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
            int channelRetNum = channelDataManager
                    .updateChannelByPlatformOrderNo(cdq);
            if (channelRetNum > 0) {// 向差异表中增加渠道差异数据
                // insertChannelDiff(str.trim());
                cSdiff.add(str.trim());
            }
            int platformRetNum = platformDataManager
                    .updatePlatformByPlatformOrderNo(pdq);
            if (platformRetNum > 0) {// 向差异表中增加平台差异数据
                // insertPlatformDiff(str.trim());
                pSdiff.add(str.trim());
            }
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "更新渠道和平台数据状态为差错" + "更新渠道数据条数【" + channelRetNum
                            + "】；更新平台数据条数【" + platformRetNum
                            + "】;PlatformOrderNo:" + cdq.getPlatformOrderNo()
                            + ";状态：" + DetailCheckStatusEnum.DIFF.getCode(), null);
        }
        ret.setChannelSdiff(cSdiff);
        ret.setPlatformSdiff(pSdiff);
    }

    /**
     * 更新渠道状态为单边
     */
    public void updateChannelSideStatusData(Set<String> set, CheckChannel cc,
                                            Result ret) {
        List<String> channelSide = new ArrayList<String>();
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "更新渠道数据状态为单边", null);
        int channelSideCount = 0;
        // 根据渠道编号获取渠道参数信息
        Date tradeDate = cc.getTradeDate();
        int resideDays = getChannelResideDays(cc);
        if (resideDays < 1) {// 说明无驻留 存在即为单边
            for (String str : set) {
                // 更新渠道数据为单边
                updateChannelDataByPlatformOrderNo(str.trim());
                // 加入到差异表操作
                // insertChannelSide(str);
                channelSide.add(str.trim());
                // 渠道单边数加1
                channelSideCount++;
            }
        } else {// 驻留业务
            for (String str : set) {
                // 1、获取该条渠道数据
                ChannelDataQuery cdQuery = new ChannelDataQuery();
                cdQuery.setPlatformOrderNo(str);
                ChannelData cd = channelDataManager.getChannelData(cdQuery);

                int resideFlag = cd.getResideFlag();//判断是否是驻留记录
                Date resideToDate = cd.getResideToDate();//获取驻留截止日期
                int minus = 0;
                if (resideFlag == 1) {// 2、驻留标志如存在，看是否到期，到期则进差异，未到期 则不处理
                    try {
                        //计算是否超过了驻留期限
                        minus = VeDate.daysBetween(tradeDate, resideToDate);
                    } catch (ParseException e) {
                        loger.error(LoggerCnst.BALANCE_MOUDLE,
                                LoggerCnst.SET_BALANCE_BUS,
                                "渠道驻留日期减当前对账交易日期异常", e, null);
                    }
                    if (minus <= 0) {// 等于零的情况说明今天已经对完（已经是最后期限）还没对上，直接也进差异表的单边
                        // 更新渠道数据为单边
                        updateChannelDataByPlatformOrderNo(str.trim());
                        // 加入到差异表操作
                        // insertChannelSide(str);
                        channelSide.add(str.trim());
                        // 渠道单边数加1
                        channelSideCount++;
                    }
                } else {// 3、驻留标志如不存在，标识为驻留，设置驻留日期
                    ChannelDataQuery cdq = new ChannelDataQuery();
                    cdq.setResideFlag(ResideFlagEnum.RESIDE.getCode());
                    cdq.setResideToDate(VeDate.getNextNDay(tradeDate,
                            resideDays));//设置驻留截止日期
                    cdq.setPlatformOrderNo(str.trim());
                    int channelRetNum = channelDataManager
                            .updateChannelByPlatformOrderNo(cdq);
                    if (channelRetNum > 0) {
                        loger.info(
                                LoggerCnst.BALANCE_MOUDLE,
                                LoggerCnst.SET_BALANCE_BUS,
                                "设置渠道驻留天数【" + cdq.getResideToDate()
                                        + "】;PlatformOrderNo:"
                                        + cdq.getPlatformOrderNo(),
                                null);
                    }
                }
            }
        }
        cc.setChannelSideCount(channelSideCount);
        ret.setChannelSide(channelSide);

    }

    /**
     * 更新平台状态为单边
     */
    public void updatePlatformSideStatusData(Set<String> set, CheckChannel cc,
                                             Result ret) {
        List<String> platformSide = new ArrayList<String>();
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "更新平台数据状态为单边," + "set集合【" + set.toString()
                        + "】;cc:"
                        + cc.toString(), null);
        // 根据渠道编号获取渠道参数信息
        int platformSideCount = 0;
        Date tradeDate = cc.getTradeDate();
        int resideDays = getChannelResideDays(cc);

        if (resideDays < 1) {// 说明无驻留 存在即为单边
            for (String str : set) {
                // 更新平台数据为单边
                updatePlatformDataByPlatformOrderNo(str);
                // 加入到差异表操作
                // insertChannelSide(str);
                platformSide.add(str.trim());
                // 平台单边数加1
                platformSideCount++;
            }
        } else {// 驻留业务
            for (String str : set) {
                // 1、获取该条渠道数据
                PlatformDataQuery pdQuery = new PlatformDataQuery();
                pdQuery.setPlatformOrderNo(str);
                PlatformData pd = platformDataManager.getPlatformData(pdQuery);

                int resideFlag = pd.getResideFlag();
                Date resideToDate = pd.getResideToDate();
                int minus = 0;
                if (resideFlag == 1) {// 2、驻留标志如存在，看是否到期，到期则进差异，未到期 则不处理
                    try {
                        minus = VeDate.daysBetween(tradeDate, resideToDate);
                    } catch (ParseException e) {
                        loger.error(LoggerCnst.BALANCE_MOUDLE,
                                LoggerCnst.SET_BALANCE_BUS,
                                "平台驻留日期减当前对账交易日期异常", e, null);
                        e.printStackTrace();
                    }
                    if (minus <= 0) {// 等于零的情况说明今天已经对完（已经是最后期限）还没对上，直接也进差异表的单边
                        // 更新平台数据为单边
                        updatePlatformDataByPlatformOrderNo(str);
                        // 加入到差异表操作
                        // insertChannelSide(str);
                        platformSide.add(str.trim());
                        // 平台单边数加1
                        platformSideCount++;

                    }
                } else {// 3、驻留标志如不存在，标识为驻留，设置驻留日期
                    PlatformDataQuery pdq = new PlatformDataQuery();
                    pdq.setResideFlag(ResideFlagEnum.RESIDE.getCode());
                    pdq.setResideToDate(VeDate.getNextNDay(tradeDate,
                            resideDays));
                    pdq.setPlatformOrderNo(str.trim());
                    int plaformRetNum = platformDataManager
                            .updatePlatformByPlatformOrderNo(pdq);
                    if (plaformRetNum > 0) {
                        loger.info(
                                LoggerCnst.BALANCE_MOUDLE,
                                LoggerCnst.SET_BALANCE_BUS,
                                "设置平台驻留天数【" + pdq.getResideToDate()
                                        + "】;PlatformOrderNo:"
                                        + pdq.getPlatformOrderNo(), null
                        );
                    }
                }

            }

        }
        int channelSideCount = cc.getChannelSideCount();
        int differenceCount = cc.getDifferenceCount();
        // 设置平台单边笔数
        cc.setPlatformSideCount(platformSideCount);
        // 设置差异总笔数和待处理差异笔数
        differenceCount = differenceCount + channelSideCount
                + platformSideCount;
        cc.setDifferenceCount(differenceCount);
        cc.setUnhandleCount(differenceCount);
        ret.setPlatformSide(platformSide);

    }

    /**
     * 缩短set集合中的item长度，只保留平台订单号
     */
    public List<String> convertSet2List(Set<String> set) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "缩短set集合中的item长度转为list，只保留平台订单号..." + set.toString(), null);
        List<String> list = new ArrayList<String>();
        for (String str : set) {
            String platformOrderNo = str.split(":")[0].trim();
            list.add(platformOrderNo);
        }
        return list;
    }

    public void delRedisKey(String channelNo, String channelMerchantNo) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "开始清理redis缓存", null);
        String channelNoRedisPrefix = channelNo + "_" + channelMerchantNo + "_";//获取渠道编号和渠道商户号
        try {
            baseRedis.del(channelNoRedisPrefix + resultChannel);
            baseRedis.del(channelNoRedisPrefix + miniResultChannel);
            baseRedis.del(channelNoRedisPrefix + sdiffSdiffChannel);
            baseRedis.del(channelNoRedisPrefix + errorDataChannel);
            baseRedis.del(channelNoRedisPrefix + reverseResultChannel);
            baseRedis.del(channelNoRedisPrefix + reverseMiniResultChannel);
            baseRedis.del(channelNoRedisPrefix + channelSet);
            baseRedis.del(channelNoRedisPrefix + platformSet);
            baseRedis.del(channelNoRedisPrefix + platformNoSet);//删除用于检测mq更新时该单号是否在对账中
            baseRedis.del(channelNoRedisPrefix + matchSet);
            baseRedis.del(channelNoRedisPrefix + channelSideData);
            baseRedis.del(channelNoRedisPrefix + platformSideData);
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "清理redis缓存异常", e, null);
        }
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                "清理redis缓存完成", null);
    }

    public void markCheckChannel(Result ret) {
        loger.info(LoggerCnst.SET_BALANCE_BUS, LoggerCnst.GET_DATA_BUS,
                "开始标记对账结果" + ret.toString(), null);
        try {

            String retCode = ret.getRetCode();//获取对账返回码
            String retMsg = ret.getRetMsg();//获取对账返回信息
            CheckChannel cc = ret.getCheckChannel();//获取渠道编号
            cc.setId(getCheckChannelId());
            cc.setCreateDate(new Date());
            cc.setUpdateDate(new Date());
            int differenceCount = cc.getDifferenceCount();//获取差异总笔数
            if (differenceCount > 0) {
                cc.setAdjustStatus(AdjustStatusEnum.UNHANDLE.getCode());
            } else {
                cc.setAdjustStatus(AdjustStatusEnum.HANDLED.getCode());
                cc.setUnhandleCount(0);//设置未处理差异笔数
            }
            if (retCode.equals(CheckChannelStatus.CHECK_CHANNEL_UNMATCH)) {// 未匹配
                cc.setCheckStatus(BatchCheckStatusEnum.UNMATCH.getCode());
                cc.setRemark(retMsg);
                checkChannelManager.insertCheckChannel(cc);//插入对账汇总信息
            } else if (retCode.equals(CheckChannelStatus.CHECK_CHANNEL_EMPTY)) {// 空异常
                cc.setCheckStatus(BatchCheckStatusEnum.EXCEPTION.getCode());
                cc.setRemark(retMsg);
                checkChannelManager.insertCheckChannel(cc);
            } else if (retCode.equals(CheckChannelStatus.CHECK_CHANNEL_MATCH)) {// 完全匹配
                cc.setCheckStatus(BatchCheckStatusEnum.MATCH.getCode());
                cc.setRemark(retMsg);
                checkChannelManager.insertCheckChannel(cc);
            }
            if (!retCode.equals(CheckChannelStatus.CHECK_CHANNEL_EMPTY)) {

                // 渠道差异数据的订单号
                List<String> channelSdiff = ret.getChannelSdiff();
                // 平台差异数据的订单号
                List<String> platformSdiff = ret.getPlatformSdiff();
                // 渠道单边数据的订单号
                List<String> channelSide = ret.getChannelSide();
                // 平台单边数据的订单号
                List<String> platformSide = ret.getPlatformSide();
                Long id = cc.getId();
                if (channelSdiff != null && channelSdiff.size() > 0) {
                    for (String str : channelSdiff) {
                        insertChannelDiff(str, id);//向差错表中插入渠道差异数据
                    }
                }
                if (platformSdiff != null && platformSdiff.size() > 0) {
                    for (String str : platformSdiff) {
                        insertPlatformDiff(str, id);//向差错表中插入平台差异数据
                    }
                }
                if (channelSide != null && channelSide.size() > 0) {
                    for (String str : channelSide) {
                        insertChannelSide(str, id);//向差错表中插入渠道单边数据
                    }
                }
                if (platformSide != null && platformSide.size() > 0) {
                    for (String str : platformSide) {
                        insertPlatformSide(str, id);//向差错表中插入平台单边数据
                    }
                }
            }
        } catch (Exception e) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "标记对账结果异常" + e.getMessage(), e, null);
        }
    }

    /**
     * 获取汇总表主键
     */
    public Long getCheckChannelId() {
        String timeStr = System.currentTimeMillis() + "";
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String newId = df.format(day) + "" + timeStr.substring(2);
        return Long.parseLong(newId);
    }

    /**
     * 向差错表中插入渠道差异数据
     *
     * @param platformOrderNo
     */
    public void insertChannelDiff(String platformOrderNo, Long id) {
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "向差异表中写入差异渠道数据开始PlatformOrderNo" + platformOrderNo,
                platformOrderNo);
        ChannelDataQuery channeldq = new ChannelDataQuery();
        channeldq.setPlatformOrderNo(platformOrderNo.trim());
        List<ChannelData> channelDataList = channelDataManager
                .getChannelDataList(channeldq);
        if (channelDataList != null && channelDataList.size() > 0) {
            ChannelData cData = channelDataList.get(0);
            CheckDifference cd = new CheckDifference();
            cd.setDifferenceType(DifferenceTypeEnum.DIFF.getCode());
            cd.setDifferenceSource(DifferenceSourceEnum.CHANNEL.getCode());
            cd.setPlatformOrderNo(platformOrderNo);
            cd.setAmount(cData.getReceivedAmt());
            cd.setStatus(cData.getTransStatus());
            cd.setChannelMerchantNo(cData.getMerchantNo());
            cd.setChannelNo(cData.getChannelNo());
            cd.setRefMainrecordId(id);
            cd.setCreateDate(new Date());
            cd.setUpdateDate(new Date());
            int insNum = checkDifferenceManager.insertCheckDifference(cd);
            if (insNum > 0) {
                loger.info(
                        LoggerCnst.BALANCE_MOUDLE,
                        LoggerCnst.SET_BALANCE_BUS,
                        "向差异表中写入差异渠道数据成功PlatformOrderNo" + platformOrderNo,
                        platformOrderNo);
            }
        } else {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "获取渠道数据异常PlatformOrderNo" + platformOrderNo, null, platformOrderNo);
        }
    }

    /**
     * 向差错表中插入平台差异数据
     *
     * @param platformOrderNo
     */
    public void insertPlatformDiff(String platformOrderNo, Long id) {
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "向差异表中写入差异平台数据开始 PlatformOrderNo" + platformOrderNo,
                platformOrderNo);
        PlatformDataQuery platformdq = new PlatformDataQuery();
        platformdq.setPlatformOrderNo(platformOrderNo.trim());
        List<PlatformData> platformDataList = platformDataManager
                .getPlatformDataList(platformdq);
        if (platformDataList != null && platformDataList.size() > 0) {
            PlatformData pData = platformDataList.get(0);
            CheckDifference cd = new CheckDifference();
            cd.setDifferenceType(DifferenceTypeEnum.DIFF.getCode());
            cd.setDifferenceSource(DifferenceSourceEnum.PLATFORM.getCode());
            cd.setPlatformOrderNo(platformOrderNo);
            cd.setAmount(pData.getArrivedAmt());
            cd.setStatus(pData.getPayStatus());
            cd.setChannelMerchantNo(pData.getChannelMerchantNo());
            cd.setChannelNo(pData.getChannelNo());
            cd.setRefMainrecordId(id);
            cd.setCreateDate(new Date());
            cd.setUpdateDate(new Date());
            int insNum = checkDifferenceManager.insertCheckDifference(cd);
            if (insNum > 0) {
                loger.info(
                        LoggerCnst.BALANCE_MOUDLE,
                        LoggerCnst.SET_BALANCE_BUS,
                        "向差异表中写入差异平台数据成功PlatformOrderNo" + platformOrderNo,
                        platformOrderNo);
            }
        } else {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "获取平台数据异常PlatformOrderNo" + platformOrderNo, null, platformOrderNo);
        }
    }

    /**
     * 向差错表中插入渠道单边数据
     *
     * @param platformOrderNo
     */
    public void insertChannelSide(String platformOrderNo, Long id) {
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "向差异表中写入渠道单边数据开始 PlatformOrderNo" + platformOrderNo,
                null);
        ChannelDataQuery channeldq = new ChannelDataQuery();
        channeldq.setPlatformOrderNo(platformOrderNo.trim());
        List<ChannelData> channelDataList = channelDataManager
                .getChannelDataList(channeldq);
        if (channelDataList != null && channelDataList.size() > 0) {
            ChannelData cData = channelDataList.get(0);
            CheckDifference cd = new CheckDifference();
            cd.setDifferenceType(DifferenceTypeEnum.CSIDE.getCode());
            cd.setDifferenceSource(DifferenceSourceEnum.CHANNEL.getCode());
            cd.setPlatformOrderNo(platformOrderNo);
            cd.setAmount(cData.getReceivedAmt());
            cd.setStatus(cData.getTransStatus());
            cd.setChannelMerchantNo(cData.getMerchantNo());
            cd.setChannelNo(cData.getChannelNo());
            cd.setRefMainrecordId(id);
            cd.setCreateDate(new Date());
            cd.setUpdateDate(new Date());
            int insNum = checkDifferenceManager.insertCheckDifference(cd);
            if (insNum > 0) {
                loger.info(
                        LoggerCnst.BALANCE_MOUDLE,
                        LoggerCnst.SET_BALANCE_BUS,
                        "向差异表中写入渠道单边数据成功PlatformOrderNo" + platformOrderNo,
                        platformOrderNo);
            }
        } else {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "获取渠道数据异常PlatformOrderNo" + platformOrderNo, null, platformOrderNo);
        }
    }

    /**
     * 向差错表中插入平台单边数据
     *
     * @param platformOrderNo
     */
    public void insertPlatformSide(String platformOrderNo, Long id) {
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "向差异表中写入平台单边数据开始 PlatformOrderNo" + platformOrderNo,
                platformOrderNo);

        PlatformDataQuery platformdq = new PlatformDataQuery();
        platformdq.setPlatformOrderNo(platformOrderNo.trim());
        List<PlatformData> platformDataList = platformDataManager
                .getPlatformDataList(platformdq);
        if (platformDataList != null && platformDataList.size() > 0) {
            PlatformData pData = platformDataList.get(0);
            CheckDifference cd = new CheckDifference();
            cd.setDifferenceType(DifferenceTypeEnum.PSIDE.getCode());
            cd.setDifferenceSource(DifferenceSourceEnum.PLATFORM.getCode());
            cd.setPlatformOrderNo(platformOrderNo);
            cd.setAmount(pData.getArrivedAmt());
            cd.setStatus(pData.getPayStatus());
            cd.setRefMainrecordId(id);
            cd.setChannelMerchantNo(pData.getChannelMerchantNo());
            cd.setChannelNo(pData.getChannelNo());
            cd.setCreateDate(new Date());
            cd.setUpdateDate(new Date());
            int insNum = checkDifferenceManager.insertCheckDifference(cd);
            if (insNum > 0) {
                loger.info(
                        LoggerCnst.BALANCE_MOUDLE,
                        LoggerCnst.SET_BALANCE_BUS,
                        "向差异表中写入平台单边数据成功PlatformOrderNo" + platformOrderNo,
                        platformOrderNo);
            }
        } else {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SET_BALANCE_BUS,
                    "获取平台数据异常PlatformOrderNo" + platformOrderNo, null, platformOrderNo);
        }
    }

    /**
     * 更新渠道数据为单边
     *
     * @param platformOrderNo
     */
    public void updateChannelDataByPlatformOrderNo(String platformOrderNo) {

        ChannelDataQuery cdq = new ChannelDataQuery();
        cdq.setPlatformOrderNo(platformOrderNo);
        cdq.setCheckStatus(DetailCheckStatusEnum.UNMATCH.getCode());
        cdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
        int channelRetNum = channelDataManager
                .updateChannelByPlatformOrderNo(cdq);
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "更新渠道数据为单边条数【" + channelRetNum + "】;PlatformOrderNo:"
                        + cdq.getPlatformOrderNo() + ";状态："
                        + DetailCheckStatusEnum.UNMATCH.getCode(),
                null);
    }

    /**
     * 更新平台数据为单边
     *
     * @param platformOrderNo
     */
    public void updatePlatformDataByPlatformOrderNo(String platformOrderNo) {
        PlatformDataQuery pdq = new PlatformDataQuery();
        pdq.setCheckStatus(DetailCheckStatusEnum.UNMATCH.getCode());
        pdq.setResideFlag(ResideFlagEnum.UNRESIDE.getCode());
        pdq.setPlatformOrderNo(platformOrderNo);
        int channelRetNum = platformDataManager
                .updatePlatformByPlatformOrderNo(pdq);
        loger.info(
                LoggerCnst.BALANCE_MOUDLE,
                LoggerCnst.SET_BALANCE_BUS,
                "更新平台单边数据条数【" + channelRetNum + "】;PlatformOrderNo:"
                        + pdq.getPlatformOrderNo() + ";状态："
                        + DetailCheckStatusEnum.UNMATCH.getCode(),
                null);
    }

    /**
     * 根据渠道编号获取渠道参数信息
     *
     * @param cc
     * @return
     */
    public int getChannelResideDays(CheckChannel cc) {
        String channelNo = cc.getChannelNo();
        ChannelParamQuery cpq = new ChannelParamQuery();
        cpq.setNo(channelNo);
        ChannelParam cp = channelParamManager.getChannelParam(cpq);
        int resideDays = cp.getResideDays();
        return resideDays;
    }

    /**
     * 设置渠道和平台的对账总笔数和对账总金额
     *
     * @param cc
     */
    public void setCPSumAmtAndCount(CheckChannel cc) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "设置渠道和平台的对账总笔数和对账总金额开始 cc" + cc.toString(), null);
        //渠道数据获取
        ChannelDataQuery cdq = new ChannelDataQuery();
        cdq.setChannelNo(cc.getChannelNo());//设置渠道编号
        cdq.setTransTime(cc.getTradeDate());//设置交易时间
        cdq.setMerchantNo(cc.getChannelMerchantNo());
        cdq.setResideFlag(ResideFlagEnum.RESIDE.getCode());
        cdq.setCheckStatus(CheckStatusEnum.INIT.getCode());
        cdq.setTransStatus(Integer.parseInt(PayStatusConst.PAY_SUCCESS));//只对成功的交易
        CheckChannel channelCheckChannel = channelDataManager
                .getChannelSumAmtAndCount(cdq);//获取到总笔数总金额
        cc.setChannelCount(channelCheckChannel.getChannelCount());//设置总笔数
        cc.setChannelAmount(channelCheckChannel.getChannelAmount());//设置总金额
        //平台数据获取
        PlatformDataQuery pdq = new PlatformDataQuery();
        pdq.setChannelNo(cc.getChannelNo());//设置渠道编号
        pdq.setArrivedTime(cc.getTradeDate());//设置到账时间
        pdq.setChannelMerchantNo(cc.getChannelMerchantNo());
        pdq.setResideFlag(ResideFlagEnum.RESIDE.getCode());
        pdq.setCheckStatus(CheckStatusEnum.INIT.getCode());
        pdq.setPayStatus(Integer.parseInt(PayStatusConst.PAY_SUCCESS));//只对成功的交易
        CheckChannel platformCheckChannel = platformDataManager
                .getPlatformSumAmtAndCount(pdq);//获取到总笔数总金额
        cc.setPlatformCount(platformCheckChannel.getPlatformCount());//设置总笔数
        cc.setPlatformAmount(platformCheckChannel.getPlatformAmount());//设置总金额
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.REDIS_DATA_BUS,
                "设置渠道和平台的对账总笔数和对账总金额结束 ", null);
    }

    @Override
    public ChannelData getById(Integer id) {
        return null;
    }

    @Override
    public ChannelData getById(Integer id, boolean masterMark)
            throws DataAccessException {
        return null;
    }

    @Override
    public int save(ChannelData entity) {
        return 0;
    }

    @Override
    public int saveBatch(List<ChannelData> list) {
        return 0;
    }

    @Override
    public int removeById(Integer id) {
        return 0;
    }

    @Override
    public int update(ChannelData entity) {
        return 0;
    }

    @Override
    public List<ChannelData> find(PageRequest query) {
        return null;
    }

    @Override
    public Page<ChannelData> findPage(PageRequest query) {
        return null;
    }


}
