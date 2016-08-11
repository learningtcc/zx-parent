package com.ink.user.api.service;

import com.ink.user.api.model.out.QueryMchAccBalOutput;
import com.ink.user.api.model.in.QueryMchAccBalInput;

/**
 * @Description: 查询商户账户余额
 * @author wanghao^_^
 * @date 2016年6月13日 下午4:10:34
 * @version V1.0
 */
public interface IQueryMchAccBalService {
	
	public QueryMchAccBalOutput exec(QueryMchAccBalInput dto) throws Exception;
}
