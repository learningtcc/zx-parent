//package com.ink.user.core.dao.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.core.dao.IAccControlDao;
//import com.ink.user.core.po.AccControl;
//
//@Repository("accControlDao")
//public class AccControlDaoImpl extends BaseIbatisDao<AccControl, Integer>
//		implements IAccControlDao {
//
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccControl";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccControl entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().toString());
//		}
//	}
//
//	@Override
//	public AccControl selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("AccControl.selectByPrimaryKey", id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AccControl record) {
//		return getSqlSession().update("AccControl.updateByPrimaryKeySelective",
//				record);
//	}
//
//	@Override
//	public int updateAccControl(AccControl accControl) {
//		return getSqlSession().update("AccControl.updateByPrimaryKey",
//				accControl);
//	}
//
//	@Override
//	public int insertAccControl(AccControl accControl) {
//		return getSqlSession().insert("AccControl.insert", accControl);
//	}
//
//	@Override
//	public int insertAccControlSelective(AccControl accControl) {
//		return getSqlSession().insert("AccControl.insertSelective", accControl);
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("AccControl.deleteByPrimaryKey", id);
//	}
//
//	@Override
//	public boolean checkControl(String mchId, String txnCode) {
//
//		AccControl accControl = selectByMchIdAndTxnCode(mchId, txnCode);
//		if (accControl != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public int insert(AccControl record) {
//		return getSqlSession().insert("AccControl.insert", record);
//	}
//
//	@Override
//	public int insertSelective(AccControl record) {
//		return getSqlSession().insert("AccControl.insertSelective", record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccControl record) {
//		return getSqlSession().update("AccControl.updateByPrimaryKey", record);
//	}
//
//	@Override
//	public AccControl selectByMchIdAndTxnCode(String mchId, String txnCode) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("mchId", mchId);
//		map.put("txnCode", txnCode);
//		return getSqlSession().selectOne("AccControl.selectByMchIdAndTxnCode",
//				map);
//	}
//}
