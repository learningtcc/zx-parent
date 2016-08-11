package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileAuthenBindCardInput;
import com.ink.channel.core.model.out.AsileAuthenBindCardOutput;
/**
 * 认证支付  绑卡接口
 * @author Lenovo
 *
 */
public interface AsileAuthenBindCardService{
	public AsileAuthenBindCardOutput bindCard(AsileAuthenBindCardInput input);
}
