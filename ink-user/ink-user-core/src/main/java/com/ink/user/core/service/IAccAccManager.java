/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service;

import com.ink.user.core.po.AccAcc;
import com.ink.base.IBaseManager;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAccAccManager extends IBaseManager<AccAcc, Long>{
	 
	 public Page<AccAcc> findCustPage(PageRequest query);
	 
	 public Page<AccAcc> findMchPage(PageRequest query);

	 public int initAccMac();
	
}
