//package com.ink.user.core.dao.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ink.base.IdGenerator;
//import com.ink.base.dao.BaseIbatisDao;
//import com.ink.user.api.exception.AtpBusinessException;
//import com.ink.user.core.dao.IAccOrgDao;
//import com.ink.user.core.po.AccOrg;
//import AccMchCacheService;
//import com.ink.user.core.service.redis.AccOrgCacheService;
//@Repository("accOrgDao")
//public class AccOrgDaoImpl extends BaseIbatisDao<AccOrg,Integer> implements IAccOrgDao{
//
//	@Autowired
//	private AccMchCacheService accMchCacheService;
//	@Autowired
//    private AccOrgCacheService accOrgCacheService;
//
//	@Override
//	public String getIbatisSqlMapNamespace() {
//		return "AccOrg";
//	}
//
//	@Override
//	protected void prepareObjectForSave(AccOrg entity) {
//		if (entity.getId() == null) {
//			entity.setId(IdGenerator.genUUIDStr().toString());
//		}
//	}
//	
//    @Override
//    public AccOrg checkAccOrg(Long orgId) throws AtpBusinessException {
//        AccOrg accOrg = accOrgCacheService.getAccOrgCacheByOrgId(orgId);
//        if (accOrg == null) {
//        	accOrg = getSqlSession().selectOne("AccOrg.findByOrgId",orgId);
//            if (accOrg != null) {
//                accOrgCacheService.setAccOrgCache(accOrg);
//            }
//        }
//        return accOrg;
//    }
//
//
//    @Override
//    public AccOrg selectByPrimaryKey(Long id) {
//    	return getSqlSession().selectOne("AccOrg.selectByPrimaryKey",id);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
//    @Override
//    public int insertSelective(AccOrg record) {
//    	return getSqlSession().insert("AccOrg.insertSelective",record);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
//    @Override
//    public int updateByPrimaryKeySelective(AccOrg record) {
//    	return getSqlSession().update("AccOrg.updateByPrimaryKeySelective",record);
//    }
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insert(AccOrg record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByPrimaryKey(AccOrg record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public AccOrg findByOrgId(Long orgId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int updateByOrgId(AccOrg accOrg) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int findListPageCount(AccOrg accOrg) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}
