package com.ink.user.api.service;

import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.model.in.AccInterestInput;

/**
 * @Description: 计息
 * @author wanghao^_^
 * @date 2016年6月13日 下午3:56:21
 * @version V1.0
 */
public interface IAccInterestService {
	public RetOutput exec(AccInterestInput dto) throws Exception ;
}
