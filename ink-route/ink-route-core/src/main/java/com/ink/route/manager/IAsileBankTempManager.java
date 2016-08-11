package com.ink.route.manager;

import java.util.Date;
import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.route.api.model.po.AsileBankTemp;

public interface IAsileBankTempManager extends IBaseManager<AsileBankTemp, Long> {

	public List<AsileBankTemp> findAsileBankTemps(AsileBankTemp record);
	
	public List<AsileBankTemp> findBankByLimitAndCodeAndTime(AsileBankTemp record, Date teadeDate);
	
	public int updateNotNull(AsileBankTemp asileBankTemp);
}
