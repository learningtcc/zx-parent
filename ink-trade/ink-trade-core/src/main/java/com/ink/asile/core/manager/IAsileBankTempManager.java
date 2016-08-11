package com.ink.asile.core.manager;

import java.util.Date;
import java.util.List;

import com.ink.asile.core.po.AsileBankTemp;
import com.ink.base.IBaseManager;

public interface IAsileBankTempManager extends IBaseManager<AsileBankTemp, Long> {

	public List<AsileBankTemp> findAsileBankTemps(AsileBankTemp record);
	
	public List<AsileBankTemp> findBankByLimitAndCodeAndTime(AsileBankTemp record, Date teadeDate);
	
	public int updateNotNull(AsileBankTemp asileBankTemp);
}
