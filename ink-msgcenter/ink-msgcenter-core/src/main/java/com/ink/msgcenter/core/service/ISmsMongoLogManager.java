/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.base.page.Page;
import com.ink.msgcenter.core.po.SmsMongoLog;
import com.ink.msgcenter.core.query.SmsMongoLogQuery;

/**
 * @author zhaojie
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsMongoLogManager{
	 Page<SmsMongoLog> findSmsMongoLogInfo(SmsMongoLogQuery logQuery)throws Exception;
	 SmsMongoLog getById(String MerctCode,String _id) throws Exception;
}
