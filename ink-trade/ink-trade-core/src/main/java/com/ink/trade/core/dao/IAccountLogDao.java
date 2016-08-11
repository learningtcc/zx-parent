package com.ink.trade.core.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.AccountLog;

public interface IAccountLogDao extends EntityDao<AccountLog, Long>{
	
	List<AccountLog> findAccountLogs(AccountLog record);
}
