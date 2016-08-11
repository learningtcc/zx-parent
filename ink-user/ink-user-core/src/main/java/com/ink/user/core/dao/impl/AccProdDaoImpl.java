//package com.ink.user.core.dao.impl;
//
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.core.dao.IAccProdDao;
//import AccProd;
//@Repository("accProdDao")
//public class AccProdDaoImpl extends BaseIbatisDao<AccProd,Integer> implements IAccProdDao{
//
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccProd";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccProd entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("AccProd.deleteByPrimaryKey",id);
//	}
//
//	@Override
//	public int insert(AccProd record) {
//		return getSqlSession().insert("AccProd.insert",record);
//	}
//
//	@Override
//	public int insertSelective(AccProd record) {
//		return getSqlSession().insert("AccProd.insertSelective",record);
//	}
//
//	@Override
//	public AccProd selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("AccProd.selectByPrimaryKey",id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AccProd record) {
//		return getSqlSession().update("AccProd.updateByPrimaryKeySelective");
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccProd record) {
//		return getSqlSession().update("AccProd.updateByPrimaryKey");
//	}
//}
