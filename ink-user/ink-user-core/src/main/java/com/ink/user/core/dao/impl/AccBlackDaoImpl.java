//package com.ink.user.core.dao.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.core.dao.IAccBlackDao;
//import AccBlack;
//import AccCust;
//import DateUtils;
//
//@Repository("accBlackDao")
//public class AccBlackDaoImpl extends BaseIbatisDao<AccBlack,Integer> implements IAccBlackDao{
//
//
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccBlack";
//	}
//	
//	@Override
//	protected void prepareObjectForSave(AccBlack entity) {
//        if( entity.getId()==null) {
//            entity.setId(IdGenerator.genUUIDStr().toString());
//        }
//	}
//	
//    @Override
//    public boolean selectByTxnCode(String txnCode, Long mchId, String custId, String accTypeId) throws Exception {
//
//    	AccCust accCust = getSqlSession().selectOne("AccCust.selectAccCustByCustIdAndMchId",custId);
//        if (accCust == null) {
//            return true;
//        }
//		Map<String, Object> map = new HashMap<String, Object>();
//        map.put("txnCode", txnCode);
//        map.put("pacId", accCust.getPacId());
//        map.put("accTypeId", accTypeId);
//        AccBlack accBlack = getSqlSession().selectOne("AccBlack.selectByTxnCode",map);
//        if (null != accBlack) {
//            long time = Long.parseLong(DateUtils.getTime());
//            String date = DateUtils.getDate(new Date());
//            String startDate = DateUtils.getDate(accBlack.getBlackStartDate());
//            String endDate = DateUtils.getDate(accBlack.getBlackEndDate());
//            int start = DateUtils.getBetweenDays(startDate, date);
//            int end = DateUtils.getBetweenDays(date, endDate);
//            long startTime = Long.parseLong(DateUtils.getTime(accBlack.getBlackStartTime()));
//            long endTime = Long.parseLong(DateUtils.getTime(accBlack.getBlackEndTime()));
//            if (start >= 0 && end >= 0) {// startDate小于date
//                long i = time - startTime;
//                long j = time - endTime;
//                if (i >= 0 && j <= 0) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        return false;
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(AccBlack record) {
//    	return getSqlSession().update("AccAcc.updateByPrimaryKeySelective",record);
//    }
//    @Override
//    public AccBlack selectByPrimaryKey(Long id) {
//    	
//    	return getSqlSession().selectOne("AccBlack.selectByPrimaryKey",id);
//    }
//    @Override
//    public int insertAccBlack(AccBlack accBlack) {
//    	return getSqlSession().insert("AccBlack.insertAccBlack",accBlack);
//    }
//
//    @Override
//    public int deleteByPrimaryKey(Long id) {
//    	return getSqlSession().delete("AccBlack.deleteByPrimaryKey",id);
//    }
//
//	@Override
//	public int insert(AccBlack record) {
//		return getSqlSession().insert("AccBlack.insert",record);
//	}
//
//	@Override
//	public int insertSelective(AccBlack record) {
//		return getSqlSession().insert("AccBlack.insertSelective",record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccBlack record) {
//		return getSqlSession().update("AccBlack.updateByPrimaryKey",record);
//	}
//
//	@Override
//	public int deleteByAccId(Long accId) {
//		return getSqlSession().delete("AccBlack.deleteByAccId",accId);
//	}
//}
