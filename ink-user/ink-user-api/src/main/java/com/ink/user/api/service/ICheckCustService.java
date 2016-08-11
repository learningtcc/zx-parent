package com.ink.user.api.service;

import com.ink.user.api.model.in.CheckCustInput;
import com.ink.user.api.model.out.CheckCustOutput;

/**
 * @Description: 检查用户合法性
 * @author wanghao^_^
 * @date 2016年6月13日 下午2:59:25
 * @version V1.0
 */
public interface ICheckCustService {
	public CheckCustOutput exec(CheckCustInput dto) throws Exception ;

}
