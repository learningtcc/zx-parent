package com.ink.user.api.service;

import com.ink.user.api.model.in.AccWithdrawCancelInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 提现撤回交易(线上只能撤销提现申请交易)
 * @author wanghao
 * @date 2016年5月24日 下午5:21:35
 */
public interface IAccWithdrawCancelService {
	public RetOutput exec(AccWithdrawCancelInput dto) throws Exception;
}
