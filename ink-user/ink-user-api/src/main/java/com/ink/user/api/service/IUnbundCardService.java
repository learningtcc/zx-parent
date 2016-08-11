package com.ink.user.api.service;

import com.ink.user.api.model.out.UnbundCardOutput;
import com.ink.user.api.model.in.UnbundCardInput;

/**
 * @Description: 个人账户银行卡解绑
 * @author wanghao
 * @date 2016年5月24日 下午5:21:21
 */
public interface IUnbundCardService {
	public UnbundCardOutput exec(UnbundCardInput dto) throws Exception;
}
