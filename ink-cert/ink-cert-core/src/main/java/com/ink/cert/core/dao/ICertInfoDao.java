/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.dao;

import com.ink.base.EntityDao;
import com.ink.cert.core.po.CertInfo;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ICertInfoDao extends EntityDao<CertInfo, Integer>{

    int updateStatus(CertInfo certInfo);
}