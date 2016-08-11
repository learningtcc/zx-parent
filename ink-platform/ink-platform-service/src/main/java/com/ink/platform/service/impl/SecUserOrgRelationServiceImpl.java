package com.ink.platform.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecUserOrgRelation;
import com.ink.platform.api.service.ISecUserOrgRelationService;
import com.ink.platform.core.dao.SecUserOrgRelationDao;

@Service("secUserOrgRelationService")
public class SecUserOrgRelationServiceImpl implements ISecUserOrgRelationService {

	@Autowired
	private SecUserOrgRelationDao  secUserOrgRelationDao;
	

	@Override
	public boolean insertSelective(SecUserOrgRelation secUserOrgRelation) {
		
		return  secUserOrgRelationDao.insertSelective(secUserOrgRelation);
	}
	@Override
	public boolean addOrgViceJob(SecUserOrgRelation secUserOrgRelation) {
		int result =secUserOrgRelationDao.addOrgViceJob(secUserOrgRelation);

		return result>0;
	}

	@Override
	public SecUserOrgRelation selectSecUserOrgRelation(SecUserOrgRelation secUserOrgRelation) {
		
		return secUserOrgRelationDao.selectSecUserOrgRelation( secUserOrgRelation);
	}

	@Override
	public boolean updateUserOrgRelation(Map<Object, Object> map) {
		int result =secUserOrgRelationDao.updateUserOrgRelation(map);
		return result>0;
	}

	@Override
	public boolean deleteUserOrgRelationByMap(Map<Object, Object> map) {
		int result =secUserOrgRelationDao.deleteUserOrgRelationByMap(map);
		return result>0;
	}

}
