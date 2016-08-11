//package com.ink.user.core.dao.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.api.exception.AtpBusinessException;
//import com.ink.user.common.constant.RespCode;
//import com.ink.user.core.dao.ITnsAceGenDao;
//import TnsAceGen;
//@Repository("tnsAceGenDao")
//public class TnsAceGenDaoImpl extends BaseIbatisDao<TnsAceGen, Integer>
//		implements ITnsAceGenDao {
//	
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "TnsAceGen";
//	}
//
//	@Override
//	protected void prepareObjectForSave(TnsAceGen entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().toString());
//		}
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return getSqlSession().delete("TnsAceGen.deleteByPrimaryKey",id);
//	}
//
//	@Override
//	public int insert(TnsAceGen record) {
//		return getSqlSession().insert("TnsAceGen.insert",record);
//	}
//
//	@Override
//	public int insertSelective(TnsAceGen record) {
//		return getSqlSession().insert("TnsAceGen.insertSelective",record);
//	}
//
//	@Override
//	public TnsAceGen selectByPrimaryKey(Long id) {
//		return getSqlSession().selectOne("TnsAceGen.selectByPrimaryKey",id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(TnsAceGen record) {
//		return getSqlSession().update("TnsAceGen.updateByPrimaryKeySelective",record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(TnsAceGen record) {
//		return getSqlSession().update("TnsAceGen.updateByPrimaryKey",record);
//	}
//
//	@Override
//	public List<TnsAceGen> getTnsAceGenByTxnCodeAndAceGroup(String txnCode) {
//		return getSqlSession().selectList("TnsAceGen.getTnsAceGenByTxnCodeAndAceGroup",txnCode);
//	}
//
//	@Override
//	public List<TnsAceGen> getTnsAceDir(String txnCode) {
//		return getSqlSession().selectList("TnsAceGen.getTnsAceDir",txnCode);
//	}
//
//	@Override
//	public List<TnsAceGen> getTnsAceGenDir(String txnCode) {
//		 List<TnsAceGen> listAceGen = getTnsAceDir(txnCode);
//	        if (listAceGen == null) {
//	            throw new AtpBusinessException(RespCode.RespCode_000005, RespCode.RespCode_000005Desc);
//	        }
//	        return listAceGen;
//	}
//
//
//}
