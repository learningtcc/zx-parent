package com.ink.channel.api.service;

import com.ink.channel.api.model.in.QuickAgainValidCodeInput;
import com.ink.channel.api.model.out.QuickAgainValidCodeOutput;
/**
 * 快捷支付  再次充值发短验
 * @author Lenovo
 *
 */
public interface QuickAgainValidCodeService {
	public QuickAgainValidCodeOutput againValidCode(QuickAgainValidCodeInput input);
}
