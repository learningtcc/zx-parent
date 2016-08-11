package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AutheConfirmBindCardInput;
import com.ink.channel.api.model.out.AutheConfirmBindCardOutput;

/**
 * 发短验的认证支付   确认绑卡接口
 * @author Lenovo
 *
 */
public interface AutheConfirmBindCardService {
	public AutheConfirmBindCardOutput confirmBindCard(AutheConfirmBindCardInput input);
}
