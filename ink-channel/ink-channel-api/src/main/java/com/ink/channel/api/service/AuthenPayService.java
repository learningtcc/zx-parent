package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AuthenPayInput;
import com.ink.channel.api.model.out.AuthenPayOutput;

/**
 * 不发短验的认证支付   支付接口
 * @author Lenovo
 *
 */
public interface AuthenPayService {
	public AuthenPayOutput authenPay(AuthenPayInput input);
}
