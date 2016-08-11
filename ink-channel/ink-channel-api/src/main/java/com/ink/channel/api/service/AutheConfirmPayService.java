package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AutheConfirmPayInput;
import com.ink.channel.api.model.out.AutheConfirmPayOutput;

/**
 * 发短验的认证支付    确认支付接口
 * @author Lenovo
 *
 */
public interface AutheConfirmPayService {
	public AutheConfirmPayOutput  confirmPay(AutheConfirmPayInput input);
}
