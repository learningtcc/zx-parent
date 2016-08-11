package com.ink.user.api.service;

import com.ink.user.api.model.in.OpenAccountInput;
import com.ink.user.api.model.out.OpenAccountOutput;

/**
 * @Description: 个人账户开户默认先开资金账户
 * @author wanghao
 * @date 2016年5月24日 下午5:20:24
 */
public interface IOpenAccountService {
	public OpenAccountOutput exec(OpenAccountInput dto) throws Exception;
}
