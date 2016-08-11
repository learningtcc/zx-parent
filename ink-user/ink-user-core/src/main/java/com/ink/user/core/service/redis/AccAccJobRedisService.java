package com.ink.user.core.service.redis;

import java.math.BigDecimal;
import java.util.List;

import com.ink.user.core.po.AccAcc;
import com.ink.user.core.model.redis.AccAccRedisBean;
import com.ink.user.core.po.TnsAce;

/**
 * 批量修改商户账户余额时的redis缓存服务
 * @author yangchen
 * @date 2016年2月16日 下午6:22:37
 */
public interface AccAccJobRedisService {

	public boolean pushAccToRedisList(AccAcc accAcc, BigDecimal amt, TnsAce tnsAce) throws Exception ;

	public boolean pushAccToRedisList(AccAccRedisBean accAccRedisBean) throws Exception ;

	public List<AccAccRedisBean> getAccAccRedisBeanListByTxnCode(String txnCode) throws Exception;
	
	public void popAccAccRedisBeanList(String txnCode) throws Exception;
	
	public void removeAccAccRedisBeanList(String txnCode) throws Exception;
}
