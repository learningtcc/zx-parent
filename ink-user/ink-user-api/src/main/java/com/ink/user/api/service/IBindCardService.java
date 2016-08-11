package com.ink.user.api.service;

import com.ink.user.api.model.in.BindCardInput;
import com.ink.user.api.model.out.BindCardOutput;

/**
 * @Description: 个人账户银行卡绑定
 * @author wanghao
 * @date 2016年5月24日 下午5:21:07
 */
public interface IBindCardService {
	public BindCardOutput exec(BindCardInput dto) throws Exception;
}
