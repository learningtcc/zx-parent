package com.ink.trade.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IAccountLogDao;
import com.ink.trade.core.po.AccountLog;

@Repository
public class AccountLogDaoImpl extends BaseIbatisDao<AccountLog, Long>
		implements IAccountLogDao {
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccountLog";
	}

	@Override
	public List<AccountLog> findAccountLogs(AccountLog record) {
		return getSqlSessionSlave().selectList(
				getIbatisSqlMapNamespace() + ".findAccountLogs", record);
	}
}
