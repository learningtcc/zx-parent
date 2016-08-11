package com.ink.user.api.service;

import com.ink.user.api.model.in.MchAcountTransferInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 商户账户转账
 * @author wanghao^_^
 * @date 2016年6月13日 下午4:05:19
 * @version V1.0
 */
public interface IMchAcountTransferService {

	public RetOutput exec(MchAcountTransferInput dto) throws Exception;
}
