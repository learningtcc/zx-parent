package com.ink.trade.api.service;

import com.ink.trade.api.model.in.SignApplyInput;
import com.ink.trade.api.model.out.SignApplyOutput;

/**
 * 
 * @Description 签约申请服务接口
 * @author xuguoqi
 * @date 2016年4月13日 上午9:56:41
 */
public interface ISignApplyService {
	
	/**
	 * 
	 * @Description 签约申请业务功能
	 * @author xuguoqi
	 * @date 2016年4月27日 下午2:40:23
	 * @param signApplyInput
	 * @return
	 */
    public SignApplyOutput signApply(SignApplyInput signApplyInput);
}
