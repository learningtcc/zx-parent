package com.ink.user.core.dao.impl;

import java.util.List;

import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.service.redis.AccMchCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.po.AccMch;

@Repository("accMchDao")
public class AccMchDaoImpl extends BaseIbatisDao<AccMch,Long> implements IAccMchDao {
	
	@Autowired
	private AccMchCacheService accMchCacheService;
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccMch";
	}

	@Override
	protected void prepareObjectForSave(AccMch entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}
	
	@Override
	public AccMch checkAccMch(Long mchId){
		AccMch accMch = accMchCacheService.getAccMchCacheByMchId(mchId);
		if(accMch == null){
			throw new AtpBusinessException(RespCodeConstant.RespCode_200014,
					RespCodeConstant.RespCode_200014Desc);
//			AccMch accMch = getSqlSessionSlave().selectOne("AccMch.findByMchId",mchId);
//			if(accMch != null){
//				accMchCacheService.setAccMchCache(accMch);
//			}
		}
		return accMch;
	}
    @Override
    public AccMch findByMchId(Long mchId) {
    	return getSqlSessionSlave().selectOne("AccMch.findByMchId",mchId);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class, timeout = 10)
    @Override
    public AccMch selectByPrimaryKey(Long id) {
    	return getSqlSessionSlave().selectOne("AccMch.selectByPrimaryKey",id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int insertSelective(AccMch record) {
    	return getSqlSession().insert("AccMch.insertSelective",record);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int updateByPrimaryKeySelective(AccMch record) {
    	return getSqlSession().update("AccMch.updateByPrimaryKeySelective",record);
    }

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("AccMch.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(AccMch record) {
		return getSqlSession().insert("AccMch.insert",record);
	}

	@Override
	public int updateByPrimaryKey(AccMch record) {
		return getSqlSession().update("AccMch.updateByPrimaryKey",record);
	}

	@Override
	public List<AccMch> selectAll() {
		return getSqlSession().selectList(getIbatisSqlMapNamespace()+".findAccMchAll");
	}
}
