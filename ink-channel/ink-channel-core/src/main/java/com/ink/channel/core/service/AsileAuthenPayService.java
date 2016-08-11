package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileAuthenPayInput;
import com.ink.channel.core.model.out.AsileAuthenPayOutput;
/**
 * 认证支付  支付接口 不发短验 
 * @author Lenovo
 *
 */
public interface AsileAuthenPayService {
	public AsileAuthenPayOutput authenPay(AsileAuthenPayInput input);
}
