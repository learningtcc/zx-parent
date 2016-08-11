package com.ink.user.api.service;

import com.ink.user.api.model.in.QueryTransResultInput;
import com.ink.user.api.model.out.QueryTransResultOutput;

/**
 * @Description: 交易结果查询
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:15:12
 * @version V1.0
 */
public interface IQueryTransResultService {
	public QueryTransResultOutput exec(QueryTransResultInput dto) throws Exception;
}
