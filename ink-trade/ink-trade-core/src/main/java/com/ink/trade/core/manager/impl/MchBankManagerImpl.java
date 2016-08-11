/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IMchBankDao;
import com.ink.trade.core.manager.IMchBankManager;
import com.ink.trade.core.po.MchBank;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service("mchBankManager")
@Transactional
public class MchBankManagerImpl extends BaseManager<MchBank, java.lang.Long> implements IMchBankManager {

	@Autowired
	private IMchBankDao mchBankDao;

	public EntityDao<MchBank, java.lang.Long> getEntityDao() {
		return this.mchBankDao;
	}

	@Override
	public boolean isBankSupport(String mchId, String bankShort) {
		boolean flag = true;
		MchBank mchBank = mchBankDao.getByMchIdBankShort(mchId, bankShort);
		if (mchBank == null) {
			flag = false;
		}
		return flag;
	}
	@Override
	public int updateNotNull(MchBank mchBank){
		return mchBankDao.updateNotNull(mchBank);
	}
}
