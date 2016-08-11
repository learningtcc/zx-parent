package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AutheBindCardValidInput;
import com.ink.channel.api.model.out.AutheBindCardValidOutput;

/**
 * 发短验的认证支付  绑卡发短验接口
 * @author Lenovo
 *
 */
public interface AutheBindCardValidCodeService {
	public AutheBindCardValidOutput bindCardValidCode(AutheBindCardValidInput input);
}
