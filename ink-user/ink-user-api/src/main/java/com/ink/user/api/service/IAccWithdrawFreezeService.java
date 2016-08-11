package com.ink.user.api.service;

import com.ink.user.api.model.in.AccWithdrawFreezeInput;
import com.ink.user.api.model.out.AccWithdrawFreezeOutput;

/**
 * @Description: 账户提现冻结接口
 * @author wanghao ^_^
 * @date 2016年6月12日 下午6:22:43
 * @version V1.0
 */
public interface IAccWithdrawFreezeService {

	public AccWithdrawFreezeOutput exec( AccWithdrawFreezeInput dto ) throws Exception;
}
