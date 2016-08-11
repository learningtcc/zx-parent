package com.ink.trade.core.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IAccountLogDao;
import com.ink.trade.core.manager.IAccountLogManager;
import com.ink.trade.core.po.AccountLog;

@Service
@Transactional
public class AccountLogManagerImpl extends BaseManager<AccountLog, Long>
		implements IAccountLogManager {

	@Autowired
	private IAccountLogDao accountLogDao;

	@Override
	protected EntityDao<AccountLog, Long> getEntityDao() {
		return accountLogDao;
	}

	public List<AccountLog> findAccountLogs(AccountLog record) {
		return accountLogDao.findAccountLogs(record);
	}

}
