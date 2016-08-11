//package com.ink.user.core.dao;
//
//import com.ink.base.EntityDao;
//import AccAcc;
//import AccCust;
//import AccPac;
//import AccSacType;
//
///**
// * 主账户控制表dao层
// * @author yangchen
// * @date 2016年5月24日 下午6:01:00
// */
//public interface IAccPacDao extends EntityDao<AccPac, Long>{
//	int deleteByPrimaryKey(Long id);
//
//    int insert(AccPac record);
//
//    int insertSelective(AccPac record);
//
//    AccPac selectByPrimaryKey(Long id);
//
//    AccPac selectByPacId(String pacId);
//
//    int updateByPrimaryKeySelective(AccPac record);
//
//    int updateByPrimaryKey(AccPac record);
//    
//	public AccPac createAccPac(AccCust accCust, AccAcc accAcc, AccSacType accSacType) throws Exception;
//	
//	public int insertAccPac(AccPac accPac) throws Exception;
//	
//	public int updateAccPac(AccPac accPac) throws Exception;
//	
//	public AccPac getAccPacByPacId(String pacId) throws Exception;
//	
//	public int clearDayAmtAndCnt() throws Exception;
//	
//	public int clearDayAndMonAmtAndCnt() throws Exception;
//}
