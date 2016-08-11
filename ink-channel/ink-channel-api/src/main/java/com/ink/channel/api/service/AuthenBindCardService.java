package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AuthenBindCardInput;
import com.ink.channel.api.model.out.AuthenBindCardOutput;

/**
 * 不发短验的认证支付   绑卡接口
 * @author Lenovo
 *
 */
public interface AuthenBindCardService {
	public AuthenBindCardOutput bindCard(AuthenBindCardInput input);
}
