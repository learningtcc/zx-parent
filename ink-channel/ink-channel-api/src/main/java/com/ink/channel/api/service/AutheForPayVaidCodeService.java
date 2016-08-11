package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AutheForPayVaidInput;
import com.ink.channel.api.model.out.AutheForPayVaidOutput;

/**
 * 发短验的认证支付   支付发短验接口
 * @author Lenovo
 *
 */
public interface AutheForPayVaidCodeService {
	public AutheForPayVaidOutput forPayVaidCode(AutheForPayVaidInput input);
}
