/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.SmsReport;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsReportManager extends IBaseManager<SmsReport, String>{

 /**
  * 删除状态报告信息
  * @param smsReport
  */
 int deleteInfo(SmsReport smsReport);
}
