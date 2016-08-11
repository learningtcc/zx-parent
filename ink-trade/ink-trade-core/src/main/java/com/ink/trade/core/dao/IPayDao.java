package com.ink.trade.core.dao;


import com.ink.base.EntityDao;
import com.ink.trade.core.po.Pay;

public interface IPayDao extends EntityDao<Pay, Long>{
	public Pay findPayOrder(Pay pay);

	/**
	 * 更新非终态订单状态
	 * @param pay
	 * @return
     */
	int updateStatus(Pay pay);
	
	public int updateNotNull(Pay pay);
}
