package com.ink.user.core.dao.impl;

import java.util.List;

import com.ink.user.core.dao.IAccSacTypeDao;
import com.ink.user.core.po.AccSacType;
import com.ink.user.core.service.redis.AccSacTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;

@Repository("accSacTypeDao")
public class AccSacTypeDaoImpl extends BaseIbatisDao<AccSacType, Long>
		implements IAccSacTypeDao {

	@Autowired
	private AccSacTypeCacheService accSacTypeCacheService;
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccSacType";
	}

	@Override
	protected void prepareObjectForSave(AccSacType entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int deleteByPrimaryKey(Long id) {
    	return getSqlSession().delete("AccSacType.deleteByPrimaryKey",id);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int updateByPrimaryKeySelective(AccSacType record) {
    	return getSqlSession().update("AccSacType.updateByPrimaryKeySelective",record);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class, timeout = 10)
    @Override
    public AccSacType selectByPrimaryKey(Long id) {
    	return getSqlSessionSlave().selectOne("AccSacType.selectByPrimaryKey",id);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int insertSelective(AccSacType record) {
    	
    	return getSqlSession().insert("AccSacType.insertSelective",record);
    }

	@Override
	public int insert(AccSacType record) {
		return getSqlSession().insert("AccSacType.insert",record);
	}

	@Override
	public int updateByPrimaryKey(AccSacType record) {
		return getSqlSession().update("AccSacType.updateByPrimaryKey",record);
	}

	@Override
	public AccSacType selectAccSacTypeBySacType(String sacType) {
		return getSqlSessionSlave().selectOne("AccSacType.selectAccSacTypeBySacType",sacType);
	}

	@Override
	public List<AccSacType> findAccSacTypeAll(AccSacType record) {
		return getSqlSessionSlave().selectList("AccSacType.findAccSacTypeAll",record);
	}

}
