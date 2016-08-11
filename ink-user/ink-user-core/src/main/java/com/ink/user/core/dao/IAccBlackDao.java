//package com.ink.user.core.dao;
//
//import com.ink.base.EntityDao;
//import AccBlack;
//
///**
// * 
// * @ClassName: IAccBlackService 
// * @Description: 黑白名单查询 
// * @author guojie.gao 
// * @date 2015年11月29日 下午2:16:41 
// *
// */
//public interface IAccBlackDao extends EntityDao<AccBlack, Integer>{
//	int deleteByPrimaryKey(Long id);
//
//    int insert(AccBlack record);
//
//    int insertSelective(AccBlack record);
//
//    AccBlack selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AccBlack record);
//
//    int updateByPrimaryKey(AccBlack record);
//    
//    int deleteByAccId(Long accId);
//    
//	public boolean selectByTxnCode(String txnCode, Long mchId, String custId, String accTypeId) throws Exception ;
//	
//	public int insertAccBlack(AccBlack accBlack);
//	
//	
//}
