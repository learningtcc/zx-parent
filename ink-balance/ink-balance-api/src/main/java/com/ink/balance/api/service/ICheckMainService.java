package com.ink.balance.api.service;

import java.util.List;

import com.ink.balance.api.model.in.CheckChannelParamInput;
import com.ink.balance.api.model.in.CheckQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CommonOutput;

/**
 * @author xuguoqi
 * @Description 主对账dubbo接口
 * @date 2016年4月6日 下午5:33:31
 */
public interface ICheckMainService {

    /**
     * @param param
     * @param pageParam
     * @return CommonOutput<Object>
     * @desc 分页查询
     * @author xuguoqi
     */
    CommonOutput<Object> pageQuery(CheckQueryParamInput param, PageParamInput pageParam);

    /**
     * @param ids
     * @param remark
     * @return CommonOutput<Object>
     * @Description 批量修改对账状态
     * @author xuguoqi
     * @date 2016年4月6日 下午5:34:52
     */
    CommonOutput<Object> updateBatchStatus(List<Long> ids, String remark);

    /**
     * @param id
     * @return CommonOutput<CheckChannelOutput>
     * @Description 获取对账详细信息
     * @author xuguoqi
     * @date 2016年4月6日 下午5:37:24
     */
    CommonOutput<CheckChannelOutput> getCheckDetails(Long id);

    /**
     * 条件查询所有对账信息
     * @param paramVo
     * @return
     */
	List<CheckChannelOutput> findList(CheckQueryParamInput paramVo);

    /**
     * @return String
     * @Description 执行对账的服务 （ ChannelNo TradeDate CheckDate ChannelMerchantNo）
     * @author bo.chen
     * @date 2016年6月17日 下午5:16:44
     */
    String balanceData(CheckChannelParamInput checkChannel);
    
    /**
     * 
    * @Title: readChannelData 
    * @Description: 执行对账入库服务
    * @param @param checkChannel
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年6月21日 下午4:39:06
    * @throws
     */
    String readChannelData(CheckChannelParamInput checkChannel);

}
