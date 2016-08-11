package com.ink.asile.core.dao;

import java.util.List;

import com.ink.asile.core.po.AsileBankTemp;
import com.ink.base.EntityDao;

public interface IAsileBankTempDao extends EntityDao<AsileBankTemp, Long> {

	public List<AsileBankTemp> findAsileBankTemps(AsileBankTemp record);

//	public List<AsileBankTemp> findBankByLimitAndCodeAndTime(AsileBankTemp record,
//			Date tradeDate);

	/**
	 * 获取所有数据
	 * @return
     */
	List<AsileBankTemp> getAll();
	
	public int updateNotNull(AsileBankTemp asileBankTemp);
}
