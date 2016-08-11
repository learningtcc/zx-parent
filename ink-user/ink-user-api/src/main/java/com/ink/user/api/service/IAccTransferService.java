package com.ink.user.api.service;

import com.ink.user.api.model.in.AccTransferInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 转账接口
 * @author wanghao^_^
 * @date 2016年6月13日 上午10:29:21
 * @version V1.0
 */
public interface IAccTransferService {
	
	public RetOutput exec(AccTransferInput dto) throws Exception;

}
