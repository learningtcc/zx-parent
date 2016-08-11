package com.ink.user.api.service;

import com.ink.user.api.model.in.AccRechargeInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 账户充值接口
 * @author wanghao^_^
 * @date 2016年6月12日 下午6:10:24
 */
public interface IAccRechargeService {
	
	public RetOutput exec(AccRechargeInput dto ) throws Exception;

}
