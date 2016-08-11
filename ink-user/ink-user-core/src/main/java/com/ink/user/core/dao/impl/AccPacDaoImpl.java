//package com.ink.user.core.dao.impl;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.base.utils.IdCodeGenerator;
//import com.ink.user.core.dao.IAccPacDao;
//import AccAcc;
//import AccCust;
//import AccPac;
//import AccSacType;
//@Repository("accPacDao")
//public class AccPacDaoImpl extends BaseIbatisDao<AccPac,Long> implements IAccPacDao{
//	@Autowired
//	private IdCodeGenerator idCodeGenerator;
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccPac";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccPac entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
//	}
//	
//	@Override
//	public AccPac createAccPac(AccCust accCust, AccAcc accAcc, AccSacType accSacType)
//			throws Exception {
//		Date initDate = new Date();
//		Integer initVersion = new Integer(1);
//		// 哎，就一个个写吧，不用bean properties copy了，熟悉一下业务
//		AccPac accPac = new AccPac();
//		accPac.setId(Long.valueOf(idCodeGenerator.getId()));
//		accPac.setPacId(String.valueOf(accAcc.getPacId()));
//		accPac.setCreateTime(initDate);
//		accPac.setLastUpdateTime(initDate);
//		accPac.setCustType(accCust.getCustType());
////		accPac.setDayInAmt(dayInAmt);//日收入金额累计
////		accPac.setDayInCnt(dayInCnt);//日收入笔数累计
////		accPac.setDayPayAmt(dayPayAmt);//日支出金额累计
////		accPac.setDayPayCnt(dayPayCnt);//日支出笔数累计
////		accPac.setMonInAmt(monInAmt);//月收入金额累计
////		accPac.setMonInCnt(monInCnt);//月收入笔数累计
////		accPac.setMonPayAmt(monPayAmt);//月支出金额累计
////		accPac.setMonPayCnt(monPayCnt);//月支出笔数累计
////		accPac.setDayNopswdAmt(dayNopswdAmt);//日免签金额累计
////		accPac.setDayNopswdCnt(dayNopswdCnt);//日免签笔数累计
//		if(accSacType.getDayInlmtAmt() == null){
//			accSacType.setDayInlmtAmt(BigDecimal.ZERO);
//		}
//		accPac.setDayInlmtAmt(accSacType.getDayInlmtAmt());//日收入金额上限
//		if(accSacType.getDayInlmtCnt() == null){
//			accSacType.setDayInlmtCnt(0);
//		}
//		accPac.setDayInlmtCnt(accSacType.getDayInlmtCnt());//日收入笔数上限
//		if(accSacType.getDayPaylmtAmt() == null){
//			accSacType.setDayPaylmtAmt(BigDecimal.ZERO);
//		}
//		accPac.setDayPaylmtAmt(accSacType.getDayPaylmtAmt());//日支出金额上限
//		if(accSacType.getDayPaylmtCnt() == null){
//			accSacType.setDayPaylmtCnt(0);
//		}
//		accPac.setDayPaylmtCnt(accSacType.getDayPaylmtCnt());//日支出笔数上限
//		if(accSacType.getMonInlmtAmt() == null){
//			accSacType.setMonInlmtAmt(BigDecimal.ZERO);
//		}
//		accPac.setMonInlmtAmt(accSacType.getMonInlmtAmt());//月收入金额上限
//		if(accSacType.getMonInlmtCnt() == null){
//			accSacType.setMonInlmtCnt(0);
//		}
//		accPac.setMonInlmtCnt(accSacType.getMonInlmtCnt());//月收入笔数上限
//		if(accSacType.getMonPaylmtAmt() == null){
//			accSacType.setMonPaylmtAmt(BigDecimal.ZERO);
//		}
//		accPac.setMonPaylmtAmt(accSacType.getMonPaylmtAmt());//月支出金额上限
//		if(accSacType.getMonPaylmtCnt() == null){
//			accSacType.setMonPaylmtCnt(0);
//		}
//		accPac.setMonPaylmtCnt(accSacType.getMonPaylmtCnt());//月支出笔数上限
//		accPac.setDayNopswdlmtAmt(BigDecimal.ZERO);//日免签支付金额上限
//		accPac.setDayNopswdlmtCnt(0);//日免签支付笔数上限
//		accPac.setOpenDate(accAcc.getOpenDate());
//		accPac.setCloseDate(accAcc.getCloseDate());
//		accPac.setStatus(accAcc.getStatus());
//		accPac.setVersion(initVersion);
//		insertAccPac(accPac);
//		return accPac;
//	}
//
//	@Override
//	public int insertAccPac(AccPac accPac) throws Exception {
//		return getSqlSession().insert("AccPac.insert",accPac);
//	}
//
//	@Override
//	public int updateAccPac(AccPac accPac) throws Exception {
//		return getSqlSession().update("AccPac.updateByPrimaryKey",accPac);
//	}
//
//	@Override
//	public AccPac getAccPacByPacId(String pacId) throws Exception {
//		return getSqlSession().selectOne("AccPac.selectByPacId",pacId);
//	}
//
//	@Override
//	public int clearDayAmtAndCnt() {
//		return getSqlSession().update("AccPac.clearDayAmtAndCnt");
//	}
//
//	@Override
//	public int clearDayAndMonAmtAndCnt() {
//		return getSqlSession().update("AccPac.clearDayAndMonAmtAndCnt");
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("AccPac.deleteByPrimaryKey",id);
//	}
//
//	@Override
//	public int insert(AccPac record) {
//		return getSqlSession().insert("AccPac.insert",record);
//	}
//
//	@Override
//	public int insertSelective(AccPac record) {
//		return getSqlSession().insert("AccPac.insertSelective",record);
//	}
//
//	@Override
//	public AccPac selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("AccPac.selectByPrimaryKey");
//	}
//
//	@Override
//	public AccPac selectByPacId(String pacId) {
//		return getSqlSession().selectOne("AccPac.selectByPacId",pacId);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AccPac record) {
//		return getSqlSession().update("AccPac.updateByPrimaryKeySelective",record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccPac record) {
//		return getSqlSession().update("AccPac.updateByPrimaryKey",record);
//	}
//
//}
