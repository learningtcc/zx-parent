/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao;

import com.ink.user.core.po.AccLimit;
import com.ink.base.EntityDao;

/**
 * 
 * @Description: TODO
 * @author wanghao
 * @date 2016年5月26日 下午3:19:47
 *
 */
public interface IAccLimitDao extends EntityDao<AccLimit, java.lang.Long>{

	public AccLimit selectByIdForUpdate(Long id);
	
	public AccLimit selectBySacIdAndTradeDateForUpdate(String sacId, String tradeDate);
	
	public AccLimit selectByPacIdAndTradeDateForUpdate(String pacId, String tradeDate);
}