//package com.ink.user.core.dao;
//
//import com.ink.base.EntityDao;
//import AccAcc;
//import AccCust;
//import com.ink.user.core.po.AccSac;
//import AccSacType;
//
///**
// * 子账户控制表相关信息
// * @author yangchen
// * @date 2016年5月24日 下午6:00:14
// */
//public interface IAccSacDao extends EntityDao<AccSac, Integer>{
//	
//	int deleteByPrimaryKey(Long id);
//
//    int insert(AccSac record);
//
//    int insertSelective(AccSac record);
//
//    AccSac selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AccSac record);
//
//    int updateByPrimaryKey(AccSac record);
//    
//    AccSac selectBySacId(String sacId);
//    
//	public AccSac createAccSac(AccCust accCust, AccAcc accAcc, AccSacType accSacType) throws Exception;
//	
//	public int insertAccSac(AccSac accSac) throws Exception;
//	
//	public int updateAccSac(AccSac accSac) throws Exception;
//	
//	public AccSac getAccSacBySacId(String sacId) throws Exception;
//	
//	public int clearDayAmtAndCnt() throws Exception;
//	
//	public int clearDayAndMonAmtAndCnt() throws Exception;
//	
//}
