package com.ink.user.api.service;

import com.ink.user.api.model.in.QueryCustAccBalInput;
import com.ink.user.api.model.out.QueryCustAccBalOutput;

/**
 * @Description: 个人账户余额查询
 * @author wanghao
 * @date 2016年5月24日 下午5:20:52
 */
public interface IQueryCustAccBalService {
	public QueryCustAccBalOutput exec(QueryCustAccBalInput dto) throws Exception;
}
