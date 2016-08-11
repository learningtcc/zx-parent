package com.ink.user.api.service;

import com.ink.user.api.model.in.QueryCustAccInput;
import com.ink.user.api.model.out.QueryCustAccOutput;

/**
 * @Description: 个人账户信息查询
 * @author wanghao
 * @date 2016年5月24日 下午5:20:36
 */
public interface IQueryCustAccService {
	public QueryCustAccOutput exec(QueryCustAccInput dto) throws Exception;
}
