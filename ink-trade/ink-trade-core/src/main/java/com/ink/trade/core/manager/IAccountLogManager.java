package com.ink.trade.core.manager;

import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.AccountLog;

public interface IAccountLogManager extends IBaseManager<AccountLog, Long> {
	public List<AccountLog> findAccountLogs(AccountLog record);
}
