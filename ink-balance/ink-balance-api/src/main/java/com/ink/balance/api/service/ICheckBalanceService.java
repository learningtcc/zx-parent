package com.ink.balance.api.service;

import java.util.List;

import com.ink.balance.api.model.in.CheckBalanceInput;
import com.ink.balance.api.model.in.CheckBalanceQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckBalanceOutput;
import com.ink.balance.api.model.out.CommonOutput;

/**
 * @author bo.chen
 * @Description 调账数据dubbo接口
 * @date 2016年4月28日 上午11:03:07
 */
public interface ICheckBalanceService {

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    CommonOutput<Object> pageQuery(CheckBalanceQueryParamInput param, PageParamInput pageParam);

    /**
     * @param id
     * @return CommonOutput<CheckBalanceOutput>
     * @Description 获取信息明细
     * @author bo.chen
     * @date 2016年4月28日 下午5:17:30
     */
    CommonOutput<CheckBalanceOutput> getDetails(Long id);

    /**
     * @param checkBalanceApi
     * @return int
     * @Description 调账后批量保存信息
     * @author bo.chen
     * @date 2016年5月26日 下午5:17:30
     */
    int saveBalanceBatch(CheckBalanceInput checkBalanceApi);

    /**
     * @param platformOrderNo
     * @return CommonOutput<CheckBalanceOutput>
     * @Description 获取调账信息明细
     * @author bo.chen
     * @date 2016年5月26日 下午5:17:30
     */
    CommonOutput<CheckBalanceOutput> getBalanceDetails(String platformOrderNo);
    /**
     * 条件查询调账信息
     * @param checkBalanceParam
     * @return
     */
	List<CheckBalanceOutput> findList(CheckBalanceQueryParamInput checkBalanceParam);

}
