/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsReport;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISmsReportDao extends EntityDao<SmsReport, String>{

    int deleteInfo(SmsReport smsReport);
}