/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.MerchantInfo;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMerchantInfoDao extends EntityDao<MerchantInfo, Long>{

    int updateInfo(MerchantInfo merchantInfo);

    List<MerchantInfo> findMerchantForChannel(MerchantChn merchantChn);
}