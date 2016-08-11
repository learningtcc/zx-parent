package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
/**
 * 四要素认证服务接口（等同于绑卡）
 * @author huohb
 *
 */
public interface AsileValidateCodeService {
	/**
	 * 获取验证码
	 * @param authority
	 * @return
	 */
    AsileValidCodeOutput getValidateCode(AsileValidCodeInput asileValidCodeInput);
}
