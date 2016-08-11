package com.ink.asile.core.manager;

import java.util.Date;
import java.util.List;

import com.ink.asile.core.po.AsileBank;
import com.ink.base.IBaseManager;

public interface IAsileBankManager extends IBaseManager<AsileBank, Long> {

	/**
	 * 根据银行、限额查询银行
	 * 
	 * @param record
	 * @return
	 */
	public List<AsileBank> findBankByLimitAndCodeAndTime(AsileBank record,
			Date tradeDate);

	/**
	 * 
	 * @Title: findAsileBankByAsileCode
	 * @Description: 根据AsileId查询AsileBank
	 * @param @param AsileCode
	 * @return AsileBank
	 * @throws
	 */
	public AsileBank findAsileBankByAsileCode(String asileCode, String bankCode,String payType);

	/**
	 * 
	 * @Title: findAsileBankPriorityMaxSum
	 * @Description: 查询当前最低优先级的值
	 * @return long
	 * @throws
	 */
	public long findAsileBankPriorityMaxSum(String bankCode);

	/**
	 * 
	 */
	public int update(AsileBank record);
	void updateList(List<AsileBank> records);
	public int updateNotNull(AsileBank record);
	
	public AsileBank getAsileBankBybankShort(String bankShort);

	/**
	 * 根据银行编码和银行限额查询支持某个银行的通道
	 * @param asileBank
	 * @return
     */
	List<AsileBank> getAsileBankByBankShortAndAmtLimit(AsileBank asileBank);

}
