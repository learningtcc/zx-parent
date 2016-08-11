/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service;

import com.ink.base.IBaseManager;
import com.ink.user.core.po.AccMch;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAccMchManager extends IBaseManager<AccMch, Long>{

	int saveMchAndAcc(AccMch accMch) throws Exception;
	
//		@Transactional(readOnly=true)
//		public AccMch getByMchId(Long v);
		
}
