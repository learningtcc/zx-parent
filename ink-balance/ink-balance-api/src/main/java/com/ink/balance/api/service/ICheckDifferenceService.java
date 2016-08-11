package com.ink.balance.api.service;

import com.ink.balance.api.model.in.CheckDifferenceInput;
import com.ink.balance.api.model.in.DifferenceParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CheckDifferenceOutput;
import com.ink.balance.api.model.out.CommonOutput;

import java.util.List;

/**
 * @author xuguoqi
 * @Description 差异数据dubbo接口
 * @date 2016年4月11日 上午9:51:20
 */
public interface ICheckDifferenceService {

    /**
     * @param diffParam
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>>
     * @Description 差异分页查询
     * @author xuguoqi
     * @date 2016年4月18日 下午5:16:44
     */
    CommonOutput<CheckCommonPageOutput<CheckDifferenceOutput>> pageQuery(DifferenceParamInput diffParam, PageParamInput paramInput);

    /**
     * @param id
     * @return CommonOutput<CheckDifferenceOutput>
     * @Description 获取差异信息详情
     * @author xuguoqi
     * @date 2016年4月18日 下午5:17:30
     */
    CommonOutput<CheckDifferenceOutput> getDetails(Long id);

    /**
     * @param pid
     * @param cid
     * @return int
     * @Description 处理双单边都进入差异表的直接勾兑为匹配
     * @author bo.chen
     * @date 2016年5月12日 上午11:17:30
     */
    int handle2oneSideToMatch(Long pid,Long cid);

    /**
     * @param platformOrderNo
     * @return List<CheckDifferenceInput>
     * @Description 获取差异信息详情List
     * @author bo.chen
     * @date 2016年4月18日 下午5:17:30
     */
    List<CheckDifferenceInput> getDetailsByPlatformOrderNo(String platformOrderNo);

    /**
     * @param checkDifferenceApi
     * @return List<CheckDifferenceInput>
     * @Description 获取差异信息详情List
     * @author bo.chen
     * @date 2016年5月25日 下午5:17:30
     */
    List<CheckDifferenceInput> getDifferenceListByParams(CheckDifferenceInput checkDifferenceApi);

    /**
     * @param id
     * @return int
     * @Description 处理单边失败交易为挂起
     * @author bo.chen
     * @date 2016年5月30日 上午11:17:30
     */
    int handle2hangUp(Long id);
}
