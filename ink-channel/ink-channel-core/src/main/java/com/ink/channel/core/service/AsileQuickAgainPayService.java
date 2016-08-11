package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileQuickAgainPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
/**
 * 快捷支付 再次支付
 * @author Lenovo
 *
 */
public interface AsileQuickAgainPayService {
	public AsileQuickPayOutput againPay(AsileQuickAgainPayInput input) ;
}
