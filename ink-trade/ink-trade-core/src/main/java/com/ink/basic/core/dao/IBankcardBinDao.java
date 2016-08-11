/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.core.dao;

import com.ink.base.EntityDao;
import com.ink.basic.core.po.BankcardBin;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IBankcardBinDao extends EntityDao<BankcardBin, Long>{
	public BankcardBin getByCardBin(String bankNo);
	
	public BankcardBin getByCardBinByCardBinLen(BankcardBin entity);

	public int updateNotNull(BankcardBin bankcardBin);
}