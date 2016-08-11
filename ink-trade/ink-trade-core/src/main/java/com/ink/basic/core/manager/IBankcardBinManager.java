/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.core.manager;

import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IBaseManager;
import com.ink.basic.core.po.BankcardBin;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IBankcardBinManager extends IBaseManager<BankcardBin,Long>{
	 	
	/**
	 *  
	 * @Description 根据银行卡号获取cardBin信息
	 * @author xuguoqi
	 * @date 2016年5月5日 下午7:21:28
	 * @param bankNo
	 * @return
	 */
	@Transactional(readOnly=true)
	public BankcardBin getByCardBin(String bankNo);
		
	public int updateNotNull(BankcardBin bankcardBin);
}
