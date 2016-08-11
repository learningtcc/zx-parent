package com.ink.user.api.service;

import com.ink.user.api.model.in.QueryMchInfoInput;
import com.ink.user.api.model.out.QueryMchInfoOutput;

/**
 * @Description: 查询商户信息
 * @author wanghao^_^
 * @date 2016年6月13日 下午3:30:23
 * @version V1.0
 */
public interface IQueryMchInfoService {
	public QueryMchInfoOutput exec(QueryMchInfoInput dto) throws Exception ;
}
