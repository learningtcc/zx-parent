/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.service;

import com.ink.cert.core.po.CertInfo;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ICertInfoManager extends IBaseManager<CertInfo, Integer>{

 /**
  * 更新状态
  * @param certInfo
  */
 int updateStatus(CertInfo certInfo);
}
