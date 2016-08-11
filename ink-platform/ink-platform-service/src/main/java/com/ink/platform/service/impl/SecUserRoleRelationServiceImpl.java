package com.ink.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUserRoleRelation;
import com.ink.platform.api.service.ISecUserRoleRelationService;
import com.ink.platform.core.dao.SecRoleDao;
import com.ink.platform.core.dao.SecUserRoleRelationDao;

@Service("secUserRoleRelationService")
public class SecUserRoleRelationServiceImpl implements ISecUserRoleRelationService {

	@Autowired
	private SecUserRoleRelationDao secUserRoleRelationDao;
	
	@Autowired
	private SecRoleDao secRoleDao;


	@Override
	public boolean addOrgUserRole(SecUserRoleRelation secUserRoleRelation) {
		int result =secUserRoleRelationDao.addOrgUserRole(secUserRoleRelation);
		return result>0;
	}

	@Override
	public List<SecRole> selectNoOwnedRole(SecUserRoleRelation role) {
		
		return secRoleDao.selectNoOwnedRole(role);
	}

	@Override
	public boolean updateUserRole(SecUserRoleRelation secUserRoleRelation) {
		return secUserRoleRelationDao.updateUserRole(secUserRoleRelation);
	}


}
