package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileQuickAgainValidCodeInput;
import com.ink.channel.core.model.out.AsileQuickAgainValidCodeOutput;
/**
 * 快捷支付  支付发短验
 * @author Lenovo
 *
 */
public interface AsileQuickAgainValidCodeService {
	public AsileQuickAgainValidCodeOutput quickAgainValidCode(AsileQuickAgainValidCodeInput input);
}
