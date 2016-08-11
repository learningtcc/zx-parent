package com.ink.channel.api.service;

import com.ink.channel.api.model.in.QuickAgainPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;

/**
 * 快捷支付再次支付
 * @author Lenovo
 *
 */
public interface QuickAgainPayService {
	public QuickPayOutput againPay(QuickAgainPayInput input);
}
