package com.ink.user.service.limit;

import java.math.BigDecimal;

import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccLimit;

public interface IAccountLimitService {

	/**
	 * 检查主账户限额信息
	 * 
	 * @param accPac
	 * @param amt
	 * @param tnsTxn
	 * @throws Exception
	 */
//	public AccPac checkAccPacLimit(AccCust accCust, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception ;

	/**
	 * 检查子账户限制
	 * 
	 * @param accCust
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @return
	 * @throws Exception
	 */
//	public AccSac checkAccSacLimit(AccCust accCust, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception;

	/**
	 * 更新主账户限制
	 * 
	 * @param accPac
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
//	public void updateAccPacLimit(AccPac accPac, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception;
	/**
	 * 更新子账户限制
	 * 
	 * @param accSac
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
//	public void updateAccSacLimit(AccSac accSac, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception;

	/**
	 * 检查账户控制
	 * @param accCust
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @return
	 */
	public AccLimit checkAccLimit( AccAcc accAcc,
			BigDecimal amt, String txnCode, Long mchId);
	
	/**
	 * 更新账户控制
	 * @param accSac
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
	public void updateAccLimit(AccLimit accLimit, AccAcc accAcc,
			BigDecimal amt, String txnCode, Integer cnt) throws Exception;
}
