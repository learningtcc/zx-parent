/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.MerchantChn;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMerchantChnDao extends EntityDao<MerchantChn, Long>{

    List selectEmailChannelList(Long id);

    List selectSmsChannelList(Long id);

    int deleteInfo(MerchantChn merchantChn);

    int updateInfo(MerchantChn merchantChn);

    List<Map<String,Object>> findChannelForMerchant(MerchantChn merchantChn);

	List<MerchantChn> findChannels(MerchantChn mc);

    List findEmailChannelsByMerchId(Long merchId);

    List findSmsChannelsByMerchId(Long merchId);
}