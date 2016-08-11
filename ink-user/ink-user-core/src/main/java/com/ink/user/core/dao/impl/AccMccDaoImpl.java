//package com.ink.user.core.dao.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.core.dao.IAccMccDao;
//import com.ink.user.core.po.AccMcc;
//@Repository("accMccDao")
//public class AccMccDaoImpl extends BaseIbatisDao<AccMcc, Integer> implements
//		IAccMccDao {
//
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccMcc";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccMcc entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("AccMcc.deleteByPrimaryKey",id);
//	}
//
//	@Override
//	public int insert(AccMcc record) {
//		return getSqlSession().insert("AccMcc.insert",record);
//	}
//
//	@Override
//	public int insertSelective(AccMcc record) {
//		return getSqlSession().insert("AccMcc.insertSelective",record);
//	}
//
//	@Override
//	public AccMcc selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("AccMcc.selectByPrimaryKey",id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AccMcc record) {
//		return getSqlSession().update("AccMcc.updateByPrimaryKeySelective",record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccMcc record) {
//		return getSqlSession().update("AccMcc.updateByPrimaryKey",record);
//	}
//
//	@Override
//	public List<AccMcc> findAccMccAll() {
//		return getSqlSession().selectList("AccMcc.findAccMccAll");
//	}
//
//}
