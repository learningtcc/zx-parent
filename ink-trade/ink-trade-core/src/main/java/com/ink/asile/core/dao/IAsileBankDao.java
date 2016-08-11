package com.ink.asile.core.dao;

import java.util.Date;
import java.util.List;

import com.ink.asile.core.po.AsileBank;
import com.ink.base.EntityDao;

public interface IAsileBankDao extends EntityDao<AsileBank, Long> {

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
	 * @Description: 根据通道ID查询通道银行
	 * @param @param asileCode
	 * @param @return
	 * @return AsileBank
	 * @throws
	 */
	public AsileBank findAsileBankByAsileCode(String asileCode, String bankCode,String payType);

	/**
	 * 
	 * @Title: findAsileBankPriorityMaxSum
	 * @Description: 查询当前优先级最低的MAX值
	 * @param @return
	 * @return long
	 * @throws
	 */
	public long findAsileBankPriorityMaxSum(String bankCode);

	public int update(AsileBank record);

	/**
	 * @date 2016年4月26日 下午4:12:14
	 * @Description: 根据bankShort获得bankCode
	 * @author wanghao
	 * @param bankShort
	 * @return
	 */
	public List<AsileBank> getAsileBankBybankShort(String bankShort);

	/**
	 * 根据银行编码和银行限额查询支持某个银行的通道
	 * @param asileBank
	 * @return
	 */
	List<AsileBank> getAsileBankByBankShortAndAmtLimit(AsileBank asileBank);

	/**
	 * 查询所有数据（此表数据不多，不需要分页）
	 * @return
     */
	List<AsileBank> getAll();
	
	public int updateNotNull(AsileBank asileBank);
}
