package com.ink.user.api.service;

import com.ink.user.api.model.in.AccWithdrawAcceptInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 账户提现受理接口
 * @author wanghao^_^
 * @date 2016年6月12日 下午7:29:02
 * @version V1.0
 */
public interface IAccWithdrawAcceptService {
	public RetOutput exec(AccWithdrawAcceptInput dto) throws Exception;
}
