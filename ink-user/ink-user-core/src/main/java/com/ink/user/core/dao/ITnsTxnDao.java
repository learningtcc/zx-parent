package com.ink.user.core.dao;

import java.util.List;

import com.ink.user.core.po.TnsTxn;
import com.ink.base.EntityDao;

/**
 * tns_txn交易信息表dao
 * @author yangchen
 * @date 2016年5月24日 下午5:43:40
 */
public interface ITnsTxnDao extends EntityDao<TnsTxn, Long> {
	int deleteByPrimaryKey(Long id);

	int insertSelective(TnsTxn record);

	TnsTxn selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnsTxn record);

	int updateByPrimaryKey(TnsTxn record);
	
	public List<TnsTxn> selectAll();

	/**
	 * 根据交易代码查询
	 * @param txnCode
	 * @return
	 */
	TnsTxn findByTxnCode(String txnCode);
	
	
}
