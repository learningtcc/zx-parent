package com.ink.user.core.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.ink.base.EntityDao;
import com.ink.user.core.po.TnsLog;

/**
 * tns_log交易流水表dao
 * @author yangchen
 * @date 2016年5月24日 下午5:45:11
 */
public interface ITnsLogDao extends EntityDao<TnsLog, Integer>{
	int deleteByPrimaryKey(Long id);

	int insertSelective(TnsLog record);

	TnsLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnsLog record);

	int updateByPrimaryKey(TnsLog record);

	/**
	 * 
	 * @Title: findTnsLogbyOriOrdId
	 * @Description: 根据原订单号查询订单信息
	 * @param oriOrdId
	 * @param mchId
	 * @param oldOrdDate
	 * @return TnsLog
	 * @throws
	 *
	 */
	public TnsLog findTnsLogbyOriOrdId(@Param("oriOrdId") String oriOrdId,
			@Param("mchId") Long mchId, @Param("oriOrdDate") Date oriOrdDate,
			@Param("oriTxnCode") String oriTxnCode);
	
	
	/**
	 * 
	 * @FunctionName findTnsLogbyOrdId
	 * @Description 根据订单编号信息查询
	 * @author guojie.gao
	 * @date 2015年10月31日 下午3:41:34
	 * @version 1.0
	 * @history guojie.gao, 2015年10月31日 下午3:41:34 create
	 * 
	 * @param ordId
	 * @param mchId
	 * @param ordDate
	 * @param txnCode
	 * @return
	 */
	public TnsLog findTnsLogbyOrdId(@Param("ordId") String ordId,
			@Param("mchId") Long mchId, @Param("ordDate") Date ordDate,
			@Param("txnCode") String txnCode);
	
	
	public TnsLog selectTnsLog(@Param("mchId") Long mchId,
			@Param("ordId") String ordId, @Param("ordDate") Date ordDate,
			@Param("id") String id,@Param("depositType") String depositType);
	
	/**
	 * 
	 * @FunctionName selectTnsLogByOrdId
	 * @Description 根据订单号查询交易是否重复
	 * @author guojie.gao
	 * @date 2015年12月9日 上午11:30:00
	 * @version 1.0
	 * @history guojie.gao, 2015年12月9日 上午11:30:00 create
	 * 
	 * @param ordId
	 * @return
	 */
	public TnsLog selectTnsLogByOrdId(@Param("ordId")String ordId,@Param("txnCode")String txnCode,@Param("mchId")String mchId);

	/**
	 * 解冻时，查询未解冻流水
	 * @param ordId
	 * @param amt
	 * @param custFee
	 * @param ordDate
	 * @return
	 */
	public TnsLog findTnsLogByAccUnfrozen(@Param("ordId")String ordId,
			@Param("amt")BigDecimal amt, @Param("custFee")BigDecimal custFee, @Param("ordDate")Date ordDate);

	TnsLog getTnsLogbyId(String id);
}