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
//import com.ink.user.core.dao.IAccSacDao;
//import AccAcc;
//import AccCust;
//import com.ink.user.core.po.AccSac;
//import AccSacType;
//@Repository("accSacDao")
//public class AccSacDaoImpl extends BaseIbatisDao<AccSac, Integer> implements
//		IAccSacDao {
//	@Autowired
//	private IdCodeGenerator idCodeGenerator;
//	
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccSac";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccSac entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
//	}
//	
//	@Override
//	public AccSac createAccSac(AccCust accCust,AccAcc accAcc, AccSacType accSacType)
//			throws Exception {
//		Date initDate = new Date();
//		Integer initVersion = new Integer(1);
//		// 哎，就一个个写吧，不用bean properties copy了，熟悉一下业务
//		AccSac accSac = new AccSac();
//		accSac.setId(Long.valueOf(idCodeGenerator.getId()));
//		accSac.setCreateTime(initDate);
//		accSac.setLastUpdateTime(initDate);
//		accSac.setSacId(String.valueOf(accAcc.getSacId()));
//		accSac.setAccPacId(accAcc.getAccPacId());
//		accSac.setPacId(String.valueOf(accAcc.getPacId()));
////		accSac.setAccSacTypeId(accAcc.getAccSacTypeId());
//		accSac.setSacType(accAcc.getSacType());
////		accSac.setDayVarchargeAmt(dayVarchargeAmt);日充值金额累计
////		accSac.setDayVarchargeCnt(dayVarchargeCnt);//日充值笔数累计
////		accSac.setDayPayAmt(dayPayAmt);//日消费金额累计
////		accSac.setDayPayCnt(dayPayCnt);//日消费笔数累计
////		accSac.setDayInAmt(dayInAmt);//日转入金额累计
////		accSac.setDayInCnt(dayInCnt);//日转入笔数累计
////		accSac.setDayOutAmt(dayOutAmt);//日转出金额累计
////		accSac.setDayOutCnt(dayOutCnt);//日转出笔数累计
////		accSac.setDayCashAmt(dayOutAmt);//日提现金额累计
////		accSac.setDayCashCnt(dayOutCnt);//日提现笔数累计
//		
////		accSac.setMonVarchargeAmt(MonVarchargeAmt);月充值金额累计
////		accSac.setMonVarchargeCnt(MonVarchargeCnt);//月充值笔数累计
////		accSac.setMonPayAmt(MonPayAmt);//月消费金额累计
////		accSac.setMonPayCnt(MonPayCnt);//月消费笔数累计
////		accSac.setMonInAmt(MonInAmt);//月转入金额累计
////		accSac.setMonInCnt(MonInCnt);//月转入笔数累计
////		accSac.setMonOutAmt(MonOutAmt);//月转出金额累计
////		accSac.setMonOutCnt(MonOutCnt);//月转出笔数累计
////		accSac.setMonCashAmt(MonOutAmt);//月提现金额累计
////		accSac.setMonCashCnt(MonOutCnt);//月提现笔数累计
//		
//		if(accSacType.getDayVarchargelmtAmt() == null){
//			accSacType.setDayVarchargelmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setDayVarchargelmtAmt(accSacType.getDayVarchargelmtAmt());//日充值金额上限
//		if(accSacType.getDayVarchargelmtCnt() == null){
//			accSacType.setDayVarchargelmtCnt(0);
//		}
//		accSac.setDayVarchargelmtCnt(accSacType.getDayVarchargelmtCnt());//日充值笔数上限
//		if(accSacType.getDayPaylmtAmt() == null){
//			accSacType.setDayPaylmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setDayPaylmtAmt(accSacType.getDayPaylmtAmt());//日消费金额上限
//		if(accSacType.getDayPaylmtCnt() == null){
//			accSacType.setDayPaylmtCnt(0);
//		}
//		accSac.setDayPaylmtCnt(accSacType.getDayPaylmtCnt());//日消费笔数上限
//		if(accSacType.getDayInlmtAmt() == null){
//			accSacType.setDayInlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setDayInlmtAmt(accSacType.getDayInlmtAmt());//日转入金额上限
//		if(accSacType.getDayInlmtCnt() == null){
//			accSacType.setDayInlmtCnt(0);
//		}
//		accSac.setDayInlmtCnt(accSacType.getDayInlmtCnt());//日转入笔数上限
//		if(accSacType.getDayOutlmtAmt() == null){
//			accSacType.setDayOutlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setDayOutlmtAmt(accSacType.getDayOutlmtAmt());//日转出金额上限
//		if(accSacType.getDayOutlmtCnt() == null){
//			accSacType.setDayOutlmtCnt(0);
//		}
//		accSac.setDayOutlmtCnt(accSacType.getDayOutlmtCnt());//日转出笔数上限
//		if(accSacType.getDayCashlmtAmt() == null){
//			accSacType.setDayCashlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setDayCashlmtAmt(accSacType.getDayCashlmtAmt());//日提现金额上限
//		if(accSacType.getDayCashlmtCnt() == null){
//			accSacType.setDayCashlmtCnt(0);
//		}
//		accSac.setDayCashlmtCnt(accSacType.getDayCashlmtCnt());//日提现笔数上限
//		
//		if(accSacType.getMonVarchargelmtAmt() == null){
//			accSacType.setMonVarchargelmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setMonVarchargelmtAmt(accSacType.getMonVarchargelmtAmt());//月充值金额上限
//		if(accSacType.getMonVarchargelmtCnt() == null){
//			accSacType.setMonVarchargelmtCnt(0);
//		}
//		accSac.setMonVarchargelmtCnt(accSacType.getMonVarchargelmtCnt());//月充值笔数上限
//		if(accSacType.getMonPaylmtAmt() == null){
//			accSacType.setMonPaylmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setMonPaylmtAmt(accSacType.getMonPaylmtAmt());//月消费金额上限
//		if(accSacType.getMonPaylmtCnt() == null){
//			accSacType.setMonPaylmtCnt(0);
//		}
//		accSac.setMonPaylmtCnt(accSacType.getMonPaylmtCnt());//月消费笔数上限
//		if(accSacType.getMonInlmtAmt() == null){
//			accSacType.setMonInlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setMonInlmtAmt(accSacType.getMonInlmtAmt());//月转入金额上限
//		if(accSacType.getMonInlmtCnt() == null){
//			accSacType.setMonInlmtCnt(0);
//		}
//		accSac.setMonInlmtCnt(accSacType.getMonInlmtCnt());//月转入笔数上限
//		if(accSacType.getMonOutlmtAmt() == null){
//			accSacType.setMonOutlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setMonOutlmtAmt(accSacType.getMonOutlmtAmt());//月转出金额上限
//		if(accSacType.getMonOutlmtCnt() == null){
//			accSacType.setMonOutlmtCnt(0);
//		}
//		accSac.setMonOutlmtCnt(accSacType.getMonOutlmtCnt());//月转出笔数上限
//		if(accSacType.getMonCashlmtAmt() == null){
//			accSacType.setMonCashlmtAmt(BigDecimal.ZERO);
//		}
//		accSac.setMonCashlmtAmt(accSacType.getMonCashlmtAmt());//月提现金额上限
//		if(accSacType.getMonCashlmtCnt() == null){
//			accSacType.setMonCashlmtCnt(0);
//		}
//		accSac.setMonCashlmtCnt(accSacType.getMonCashlmtCnt());//月提现笔数上限
//		
//		accSac.setOpenDate(accAcc.getOpenDate());
//		accSac.setCloseDate(accAcc.getCloseDate());
//		accSac.setStatus(accAcc.getStatus());
//		accSac.setVersion(initVersion);
//		
//		insertAccSac(accSac);
//		return accSac;
//	}
//
//	@Override
//	public int insertAccSac(AccSac accSac) throws Exception {
//		return getSqlSession().insert("AccSac.insert",accSac);
//	}
//	
//	@Override
//	public int updateAccSac(AccSac accSac) throws Exception {
//		return getSqlSession().update("AccSac.updateByPrimaryKey",accSac);
//	}
//
//	@Override
//	public AccSac getAccSacBySacId(String sacId) throws Exception {
//		return getSqlSession().selectOne("AccSac.selectBySacId",sacId);
//	}
//
//	@Override
//	public int clearDayAmtAndCnt() throws Exception {
//		return getSqlSession().update("AccSac.clearDayAmtAndCnt");
//	}
//
//	@Override
//	public int clearDayAndMonAmtAndCnt() throws Exception {
//		return getSqlSession().update("AccSac.clearDayAndMonAmtAndCnt");
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("AccSac.deleteByPrimaryKey",id);
//	}
//
//	@Override
//	public int insert(AccSac record) {
//		return getSqlSession().insert("AccSac.insert",record);
//	}
//
//	@Override
//	public int insertSelective(AccSac record) {
//		return getSqlSession().insert("AccSac.insertSelective",record);
//	}
//
//	@Override
//	public AccSac selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("AccSac.selectByPrimaryKey",id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AccSac record) {
//		return getSqlSession().update("AccSac.updateByPrimaryKeySelective",record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccSac record) {
//		return getSqlSession().update("AccSac.updateByPrimaryKey",record);
//	}
//
//	@Override
//	public AccSac selectBySacId(String sacId) {
//		return getSqlSession().selectOne("AccSac.selectBySacId",sacId);
//	}
//}
