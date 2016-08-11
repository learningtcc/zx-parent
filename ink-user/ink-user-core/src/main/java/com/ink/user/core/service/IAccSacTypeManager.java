/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service;

import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IBaseManager;
import com.ink.user.core.po.AccSacType;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAccSacTypeManager extends IBaseManager<AccSacType, Long>{
	
//		@Transactional(readOnly=true)
//		public AccSacType getBySacType(String v);
		
}
