package com.ink.balance.api.service;

import java.util.List;

import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.in.PlatformDataMqParamInput;
import com.ink.balance.api.model.in.PlatformDataQueryParamInput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;

/**
 * @author xuguoqi
 * @Description 平台数据dubbo接口
 * @date 2016年4月11日 下午1:55:52
 */
public interface IPlatformDataService {

    /**
     * @param paramInput
     * @return CommonOutput<CheckCommonPageOutput<PlatformDataOutput>>
     * @Description 分页查询
     * @author xuguoqi
     * @date 2016年4月11日 下午2:01:13
     */
    CommonOutput<CheckCommonPageOutput<PlatformDataOutput>> pageQuery(PageParamInput paramInput);

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author bo.chen
     */
    CommonOutput<Object> pageQuery(PlatformDataQueryParamInput param, PageParamInput pageParam);

    /**
     * @param id
     * @return CommonOutput<PlatformDataOutput>
     * @Description 获取平台信息明细
     * @author bo.chen
     * @date 2016年4月19日 下午5:17:30
     */
    CommonOutput<PlatformDataOutput> getDetails(Long id);
    /**
     * 查询所有平台交易信息
     * @param checkParam
     * @return
     */
	List<PlatformDataOutput> findList(PlatformDataQueryParamInput checkParam);

    /**
     * @param param
     * @return String
     * @Description 插入平台mq信息
     * @author bo.chen
     * @date 2016年7月20日 下午12:17:30
     */
    String insertMq(PlatformDataMqParamInput param);

    /**
     * @param param
     * @return String
     * @Description 更新平台mq信息
     * @author bo.chen
     * @date 2016年7月20日 下午12:17:30
     */
    String updateMq(PlatformDataMqParamInput param);
}
